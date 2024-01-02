#include <stdio.h>
#include <errno.h>
#include <unistd.h>
#include <stdlib.h>

#include <glib.h>
#include <gio/gio.h>
#include <gio/gnetworking.h>
#include "bluez_gdbus.h"
GDBusConnection *con;

GVariant *result;
GList *devices = NULL;
GMainLoop *scan_loop = NULL;
GMainLoop *main_loop = NULL;
GDBusConnection *conn = NULL;
gboolean loop_exit = FALSE;


void bluez_property_value(const gchar *key, GVariant *value)
{
	const gchar *type = g_variant_get_type_string(value);

	g_print("\t%s : ", key);
	switch(*type) {
		case 'o':
		case 's':
			g_print("%s\n", g_variant_get_string(value, NULL));
			break;
		case 'b':
			g_print("%d\n", g_variant_get_boolean(value));
			break;
		case 'u':
			g_print("%d\n", g_variant_get_uint32(value));
			break;
		case 'a':
		/* TODO Handling only 'as', but not array of dicts */
			if(g_strcmp0(type, "as"))
				break;
			g_print("\n");
			const gchar *uuid;
			GVariantIter i;
			g_variant_iter_init(&i, value);
			while(g_variant_iter_next(&i, "s", &uuid))
				g_print("\t\t%s\n", uuid);
			break;
		default:
			g_print("Other\n");
			break;
	}
}

void bluez_device_appeared(GDBusConnection *sig,
				const gchar *sender_name,
				const gchar *object_path,
				const gchar *interface,
				const gchar *signal_name,
				GVariant *parameters,
				gpointer user_data)
{
	(void)sig;
	(void)sender_name;
	(void)object_path;
	(void)interface;
	(void)signal_name;
	(void)user_data;

	GVariantIter *interfaces;
	const char *object;
	const gchar *interface_name;
	GVariant *properties;

	g_variant_get(parameters, "(&oa{sa{sv}})", &object, &interfaces);
	while(g_variant_iter_next(interfaces, "{&s@a{sv}}", &interface_name, &properties)) {
		if(g_strstr_len(g_ascii_strdown(interface_name, -1), -1, "device")) {
			g_print("[ %s ]\n", object);
			const gchar *property_name;
			GVariantIter i;
			GVariant *prop_val;
			g_variant_iter_init(&i, properties);
			while(g_variant_iter_next(&i, "{&sv}", &property_name, &prop_val))
				bluez_property_value(property_name, prop_val);
			g_variant_unref(prop_val);
		}
		g_variant_unref(properties);
	}
	return;
}

void bluez_device_disappeared(GDBusConnection *sig,
				const gchar *sender_name,
				const gchar *object_path,
				const gchar *interface,
				const gchar *signal_name,
				GVariant *parameters,
				gpointer user_data)
{
	(void)sig;
	(void)sender_name;
	(void)object_path;
	(void)interface;
	(void)signal_name;

	GVariantIter *interfaces;
	const char *object;
	const gchar *interface_name;
	char address[BT_ADDRESS_STRING_SIZE] = {'\0'};

	g_variant_get(parameters, "(&oas)", &object, &interfaces);
	while(g_variant_iter_next(interfaces, "s", &interface_name)) {
		if(g_strstr_len(g_ascii_strdown(interface_name, -1), -1, "device")) {
			int i;
			char *tmp = g_strstr_len(object, -1, "dev_") + 4;

			for(i = 0; *tmp != '\0'; i++, tmp++) {
				if(*tmp == '_') {
					address[i] = ':';
					continue;
				}
				address[i] = *tmp;
			}
			g_print("\nDevice %s removed\n", address);
			g_main_loop_quit((GMainLoop *)user_data);
		}
	}
	return;
}

void bluez_signal_adapter_changed(GDBusConnection *conn,
					const gchar *sender,
					const gchar *path,
					const gchar *interface,
					const gchar *signal,
					GVariant *params,
					void *userdata)
{
	(void)conn;
	(void)sender;
	(void)path;
	(void)interface;
	(void)userdata;

	GVariantIter *properties = NULL;
	GVariantIter *unknown = NULL;
	const char *iface;
	const char *key;
	GVariant *value = NULL;
	const gchar *signature = g_variant_get_type_string(params);

	if(g_strcmp0(signature, "(sa{sv}as)") != 0) {
		g_print("Invalid signature for %s: %s != %s", signal, signature, "(sa{sv}as)");
		goto done;
	}

	g_variant_get(params, "(&sa{sv}as)", &iface, &properties, &unknown);
	while(g_variant_iter_next(properties, "{&sv}", &key, &value)) {
		if(!g_strcmp0(key, "Powered")) {
			if(!g_variant_is_of_type(value, G_VARIANT_TYPE_BOOLEAN)) {
				g_print("Invalid argument type for %s: %s != %s", key,
						g_variant_get_type_string(value), "b");
				goto done;
			}
			g_print("Adapter is Powered \"%s\"\n", g_variant_get_boolean(value) ? "on" : "off");
		}
		if(!g_strcmp0(key, "Discovering")) {
			if(!g_variant_is_of_type(value, G_VARIANT_TYPE_BOOLEAN)) {
				g_print("Invalid argument type for %s: %s != %s", key,
						g_variant_get_type_string(value), "b");
				goto done;
			}
			g_print("Adapter scan \"%s\"\n", g_variant_get_boolean(value) ? "on" : "off");
		}
	}
done:
	g_variant_iter_free(properties);
	g_variant_unref(value);
}

int bluez_adapter_call_method_sync(const char *method)
{
	GVariant *result;
	GError *error = NULL;

	result = g_dbus_connection_call_sync(con,
					     "org.bluez",
					/* TODO Find the adapter path runtime */
					     "/org/bluez/hci0",
					     "org.bluez.Adapter1",
					     method,
					     NULL,
					     NULL,
					     G_DBUS_CALL_FLAGS_NONE,
					     -1,
					     NULL,
					     &error);
	if(error != NULL){
		//printf("erro:%s\n",error->message);
		return 1;
	}

	g_variant_unref(result);
	return 0;
}

int bluez_adapter_set_property(const char *prop, GVariant *value)
{
	GVariant *result;
	GError *error = NULL;

	result = g_dbus_connection_call_sync(con,
					     "org.bluez",
					     "/org/bluez/hci0",
					     "org.freedesktop.DBus.Properties",
					     "Set",
					     g_variant_new("(ssv)", "org.bluez.Adapter1", prop, value),
					     NULL,
					     G_DBUS_CALL_FLAGS_NONE,
					     -1,
					     NULL,
					     &error);
	if(error != NULL) {
		printf("erro:%s\n",error->message);
		return 1;
	}
	g_variant_unref(result);
	return 0;
}


int bluez_adapter_call_method(const char *method, GVariant *param, method_cb_t method_cb)
{
	GError *error = NULL;

	g_dbus_connection_call(con,
			     "org.bluez",
			     "/org/bluez/hci0",
			     "org.bluez.Adapter1",
			     method,
			     param,
			     NULL,
			     G_DBUS_CALL_FLAGS_NONE,
			     -1,
			     NULL,
			     method_cb,
			     &error);
	if(error != NULL) {
		printf("erro:%s\n",error->message);
		return 1;
	}
	return 0;
}

int bluez_set_discovery_filter(const char *TransPort, const char *RSSI, const char *UUIDs)
{
	int rc;
	GVariantBuilder *b = g_variant_builder_new(G_VARIANT_TYPE_VARDICT);
	g_variant_builder_add(b, "{sv}", "Transport", g_variant_new_string(TransPort));
	g_variant_builder_add(b, "{sv}", "RSSI", g_variant_new_int16(-g_ascii_strtod(RSSI, NULL)));
	g_variant_builder_add(b, "{sv}", "DuplicateData", g_variant_new_boolean(FALSE));

	GVariantBuilder *u = g_variant_builder_new(G_VARIANT_TYPE_STRING_ARRAY);
	g_variant_builder_add(u, "s", UUIDs);
	g_variant_builder_add(b, "{sv}", "UUIDs", g_variant_builder_end(u));

	GVariant *device_dict = g_variant_builder_end(b);
	g_variant_builder_unref(u);
	g_variant_builder_unref(b);
	rc = bluez_adapter_call_method("SetDiscoveryFilter", g_variant_new_tuple(&device_dict, 1), NULL);
	if(rc) {
		g_print("Not able to set discovery filter\n");
		return 1;
	}
	return 0;
}

void bluez_get_discovery_filter_cb(GObject *con,
					  GAsyncResult *res,
					  gpointer data)
{
	(void)data;
	GVariant *result = NULL;
	result = g_dbus_connection_call_finish((GDBusConnection *)con, res, NULL);
	if(result == NULL)
		g_print("Unable to get result for GetDiscoveryFilter\n");

	if(result) {
		result = g_variant_get_child_value(result, 0);
		//bluez_property_value("GetDiscoveryFilter", result);
	}
	g_variant_unref(result);
}

void on_sigint_received(int signo)
{
  if (signo == SIGINT) loop_exit = TRUE;
}

void free_device_string(gpointer data)
{
  free((gchar *)data);
}

void on_adapter_changed(GDBusConnection *conn,
                        const gchar *sender_name,
                        const gchar *object_path,
                        const gchar *interface_name,
                        const gchar *signal_name,
                        GVariant *parameters,
                        gpointer user_data)
{
  const char *adapter_path = adapter_info.object_path;

  if (g_strcmp0(object_path, adapter_path) == 0)
    return;

  if (g_list_length(devices) > 30)
    return;

  if (object_path && strstr(object_path, adapter_path) != NULL)
  {
    GList *l;
    gboolean found = FALSE;

    for (l = devices; l != NULL; l = l->next)
    {
      if (g_strcmp0(l->data, object_path) == 0) {
        found = TRUE;
	break;
      }
    }

    if (found == FALSE)
    {
      devices = g_list_prepend(devices, g_strdup(object_path));
    }
  }
}

gboolean on_scan_timeout(gpointer user_data)
{
  g_assert(scan_loop != NULL);
  g_main_loop_quit(scan_loop);
  return G_SOURCE_REMOVE;
}

gboolean on_loop_idle(gpointer user_data)
{
  if (loop_exit == TRUE)
  {
    g_main_loop_quit(main_loop);
    return G_SOURCE_REMOVE;
  }

  return G_SOURCE_CONTINUE;
}

int enable_device_discovery(GDBusConnection *conn, gboolean enable)
{
  g_assert(conn != NULL && "Expected a valid D-Bus connection!");

  if (enable == TRUE)
  {
    adapter_info.scan_subscription_id =
      g_dbus_connection_signal_subscribe(conn,
                                         "org.bluez",
                                         "org.freedesktop.DBus.Properties",
                                         "PropertiesChanged",
                                         NULL,
                                         NULL,
                                         G_DBUS_SIGNAL_FLAGS_NONE,
                                         on_adapter_changed,
                                         NULL,
                                         NULL);
  }
  else
  {
    g_dbus_connection_signal_unsubscribe(conn,
                                         adapter_info.scan_subscription_id);
  }

  char *method_call = enable ? "StartDiscovery" : "StopDiscovery";

  GError *error = NULL;
  GVariant *res = NULL;
  res =
    g_dbus_connection_call_sync(conn,
                                "org.bluez",
                                "/org/bluez/hci0",
                                "org.bluez.Adapter1",
                                method_call,
                                NULL,
                                NULL,
                                G_DBUS_CALL_FLAGS_NONE,
                                -1,
                                NULL,
                                &error);

  g_variant_unref(res);

  if (error != NULL) return -1;
  else return 0;
}


gchar *select_device(void)
{
  guint num_devices = 0;
  GList *l = NULL;

  //g_print("Detected devices:\n");

  for (l = devices; l != NULL; l = l->next)
  {
	(gchar*) l->data;
	num_devices++;
    //g_print("  %d: %s\n", num_devices++, (gchar*) l->data);
  }

  if (num_devices == 0)
  {
    g_print("  none\n");
    return NULL;
  }

  //g_print("Select device number: ");

  //const char id = getchar();
  guint n = 0;
  //scanf("%d",&n);
  const guint key = 1;

  if (key < 0 || key > 29 || key >= g_list_length(devices))
  {
    g_print("invalid selection\n");
    return NULL;
  }

  gchar *device = g_list_nth_data(devices, key);
  return device;
}

int connect_device(GDBusConnection *conn,
                          gchar *device_path,
                          gboolean connect)
{
  //g_assert(conn != NULL && "Expected a valid D-Bus connection!");
  //g_assert(device_path != NULL && "Expected a valid device path!");

  GError *error = NULL;

  if (connect)
  {
    GVariant *params =
      g_variant_new("(ss)", "org.bluez.Device1", "Paired");

    GVariant *q_res =
      g_dbus_connection_call_sync(conn,
                                  "org.bluez",
                                  device_path,
                                  "org.freedesktop.DBus.Properties",
                                  "Get",
                                  params,
                                  NULL,
                                  G_DBUS_CALL_FLAGS_NONE,
                                  -1,
                                  NULL,
                                  &error);

    if (error != NULL)
    {
      g_variant_unref(q_res);
      g_print(
        "Error reading remote properties: %s\n", error->message);

      return -1;
    }

    GVariant *value = NULL;
    g_variant_get(q_res, "(v)", &value);
    gboolean paired = g_variant_get_boolean(value);

    g_variant_unref(q_res);
    g_variant_unref(value);

    if (paired == TRUE)
      ;//g_print("  device already paired\n");
    else
    {
      //g_print("  device not yet paired, pairing...");

      GVariant *p_res =
        g_dbus_connection_call_sync(conn,
                                    "org.bluez",
                                    device_path,
                                    "org.bluez.Device1",
                                    "Pair",
                                    NULL,
                                    NULL,
                                    G_DBUS_CALL_FLAGS_NONE,
                                    -1,
                                    NULL,
                                    &error);

      if (p_res != NULL) g_variant_unref(p_res);

      if (error != NULL)
      {
        //g_print("failed\n");
        return -1;
      } else g_print("ok\n");
    }
  }

  char *method_call = connect ? "Connect" : "Disconnect";
  GVariant *res = NULL;
  res = g_dbus_connection_call_sync(conn,
                                "org.bluez",
                                device_path,
                                "org.bluez.Device1",
                                method_call,
                                NULL,
                                NULL,
                                G_DBUS_CALL_FLAGS_NONE,
                                -1,
                                NULL,
                                &error);

  if (res != NULL)
    g_variant_unref(res);

  if (error != NULL || res == NULL)
  {
    if (connect)
      g_print("  connection failed\n");
    else g_print("  disconnection failed\n");

    return -1;
  }
  else
  {
    if (connect)
      g_print("  connection established\n");
    else g_print("  connection terminated\n");

    return 0;
  }
}

#define BUFFER_SIZE 1024*1024
int shell_call (const char *command)  
{   
    FILE *fpipe;
    char line[BUFFER_SIZE];

    if ( !(fpipe = (FILE*)popen(command, "r")) )
    {   // If fpipe is NULL
        perror("Problems with pipe");
        exit(1);
    }


	fgets( line, sizeof(char) * BUFFER_SIZE, fpipe);
    // while ( fgets( line, sizeof(char) * BUFFER_SIZE, fpipe))
    // {
    //      //Inconsistent (happens sometimes) 
    //      printf("READING LINE");
    //      printf("%s", line);
    // }

    int status = pclose(fpipe);

    if (status != 0)
    {
        //Never happens
        printf("Strange error code: %d\n", status);
    }
	printf("\n");
    return 0;
}

