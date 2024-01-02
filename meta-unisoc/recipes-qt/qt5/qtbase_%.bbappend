PACKAGECONFIG_append = " ${@bb.utils.contains('MACHINE_FEATURES','qt5-sql-sqlite',' sql-sqlite','',d)}"
