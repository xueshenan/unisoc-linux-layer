#!/bin/bash
	chown system system /sys/bus/iio/devices/trigger0/name
	chmod 660 /sys/bus/iio/devices/trigger0/name

# device0
	chown system system /sys/bus/iio/devices/iio:device0/buffer
	chown system system /sys/bus/iio/devices/iio:device0/buffer/length
	chown system system /sys/bus/iio/devices/iio:device0/buffer/enable
	chown system system /sys/bus/iio/devices/iio:device0/trigger/current_trigger
	chmod 660 /sys/bus/iio/devices/iio:device0/buffer/length
	chmod 660 /sys/bus/iio/devices/iio:device0/buffer/enable
	chmod 660 /sys/bus/iio/devices/iio:device0/trigger/current_trigger

#sensorhub driver node
	chown system system /dev/iio:device0
	chown system system /dev/iio:device1
	chown system system /sys/class/sprd_sensorhub/sensor_hub/iio/trigger/current_trigger
	chown system system /sys/class/sprd_sensorhub/sensor_hub/batch
	chown system system /sys/class/sprd_sensorhub/sensor_hub/logctl
	chown system system /sys/class/sprd_sensorhub/sensor_hub/calibrator_cmd
	chown system system /sys/class/sprd_sensorhub/sensor_hub/calibrator_data
	chown system system /sys/class/sprd_sensorhub/sensor_hub/enable
	chown system system /sys/class/sprd_sensorhub/sensor_hub/flush
	chown system system /sys/class/sprd_sensorhub/sensor_hub/iio
	chown system system /sys/class/sprd_sensorhub/sensor_hub/version
	chown system system /sys/class/sprd_sensorhub/sensor_hub/op_download
	chown system system /sys/class/sprd_sensorhub/sensor_hub/mag_cali_flag
	chown system system /sys/class/sprd_sensorhub/sensor_hub/sensorhub
	chown system system /sys/class/sprd_sensorhub/sensor_hub/raw_data_als
	chown system system /sys/class/sprd_sensorhub/sensor_hub/raw_data_ps
	chown system system /sys/class/sprd_sensorhub/sensor_hub/light_sensor_calibrator
	chmod 660 /sys/class/sprd_sensorhub/sensor_hub/iio/trigger/current_trigger
	chmod 660 /sys/class/sprd_sensorhub/sensor_hub/batch
	chmod 660 /sys/class/sprd_sensorhub/sensor_hub/logctl
	chmod 660 /sys/class/sprd_sensorhub/sensor_hub/calibrator_cmd
	chmod 660 /sys/class/sprd_sensorhub/sensor_hub/calibrator_data
	chmod 660 /sys/class/sprd_sensorhub/sensor_hub/enable
	chmod 660 /sys/class/sprd_sensorhub/sensor_hub/flush
	chmod 660 /sys/class/sprd_sensorhub/sensor_hub/iio
	chmod 660 /sys/class/sprd_sensorhub/sensor_hub/version
	chmod 660 /sys/class/sprd_sensorhub/sensor_hub/op_download
	chmod 660 /sys/class/sprd_sensorhub/sensor_hub/mag_cali_flag
	chmod 660 /sys/class/sprd_sensorhub/sensor_hub/sensorhub
	chmod 666 /sys/class/sprd_sensorhub/sensor_hub/raw_data_als
	chmod 666 /sys/class/sprd_sensorhub/sensor_hub/raw_data_ps
	chmod 666 /sys/class/sprd_sensorhub/sensor_hub/light_sensor_calibrator

#opcode
	mkdir /mnt/vendor/sensorhub 0777 system root
	chmod 666 /sys/module/shub_core/parameters/gryo_firms
	chmod 666 /sys/module/shub_core/parameters/acc_firms
	chmod 666 /sys/module/shub_core/parameters/mag_firms
	chmod 666 /sys/module/shub_core/parameters/pressure_firms
	chmod 666 /sys/module/shub_core/parameters/light_firms
	chmod 666 /sys/module/shub_core/parameters/prox_firms
	chmod 666 /sys/module/shub_core/parameters/color_temp_firms
	chmod 666 /sys/module/firmware_class/parameters/path
	chmod 666 /sys/module/shub_core/parameters/sensor_fusion_mode
	mkdir /mnt/vendor/productinfo/sensor_calibration_data 0770 system root
