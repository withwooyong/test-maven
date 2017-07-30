#! /bin/sh
# SMD Access Log Delete, 2015.06.26(KSI)
# Apache - /usr/local/apache2/logs/

/usr/bin/find /usr/local/apache2/logs/access_log.* -type f -mtime +7 -exec /bin/rm {} \;

sleep 5

/usr/bin/find /usr/local/apache2/logs/error_log.* -type f -mtime +7 -exec /bin/rm {} \;

