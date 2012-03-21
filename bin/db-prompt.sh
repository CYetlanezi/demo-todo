#!/bin/bash

# Quick script launches you into the running MySQL instance as root

eval $(dotcloud var list demo)

dotcloud run demo.db -- mysql -u$DOTCLOUD_DB_MYSQL_LOGIN -p$DOTCLOUD_DB_MYSQL_PASSWORD
