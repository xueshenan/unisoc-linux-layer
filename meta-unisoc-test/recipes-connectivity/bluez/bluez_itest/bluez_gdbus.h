
#ifndef _BLUEZ_GDBUS_H  
#define _BLUEZ_GDBUS_H

#define BT_ADDRESS_STRING_SIZE 18
#define SCAN_TIMEOUT_SECONDS 2
#define MAIN_TIMEOUT_SECONDS 5

struct
{
  gint hci_id;
  guint scan_subscription_id;

  char *bus_name;
  char *interface_name;
  char *object_path;
} adapter_info = {
  -1,
  0,
  "org.bluez",
  NULL,
  "/org/bluez/hci0"
};


typedef void (*method_cb_t)(GObject *, GAsyncResult *, gpointer);

void bluez_property_value(const gchar *key, GVariant *value);

void bluez_device_appeared(GDBusConnection *sig,
                const gchar *sender_name,
                const gchar *object_path,
                const gchar *interface,
                const gchar *signal_name,
                GVariant *parameters,
                gpointer user_data);

void bluez_device_disappeared(GDBusConnection *sig,
                const gchar *sender_name,
                const gchar *object_path,
                const gchar *interface,
                const gchar *signal_name,
                GVariant *parameters,
                gpointer user_data);

void bluez_signal_adapter_changed(GDBusConnection *conn,
					const gchar *sender,
					const gchar *path,
					const gchar *interface,
					const gchar *signal,
					GVariant *params,
					void *userdata);

int bluez_adapter_call_method_sync(const char *method);

int bluez_adapter_set_property(const char *prop, GVariant *value);

int bluez_set_discovery_filter(const char *TransPort, const char *RSSI, const char *UUIDs);

int bluez_adapter_call_method(const char *method, GVariant *param, method_cb_t method_cb);

void bluez_get_discovery_filter_cb(GObject *con,GAsyncResult *res,gpointer data);

void on_sigint_received(int signo);


void on_adapter_changed(GDBusConnection *conn,
                               const gchar *sender_name,
                               const gchar *object_path,
                               const gchar *interface_name,
                               const gchar *signal_name,
                               GVariant *parameters,
                               gpointer user_data);

gboolean on_scan_timeout(gpointer user_data);

gboolean on_loop_idle(gpointer user_data);

int enable_device_discovery(GDBusConnection *conn, gboolean enable);

gchar *select_device(void);

int connect_device(GDBusConnection *conn,
                          gchar *device_path,
                          gboolean connect);

void free_device_string(gpointer data);

void on_sigint_received(int signo);

int shell_call(const char *command);
#endif
