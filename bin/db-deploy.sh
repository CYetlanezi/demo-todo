#!/bin/bash

# Recreate our DB schema

eval $(dotcloud var list demo)

TMPFILE=$(mktemp /tmp/mysql.cmd.XXX)
echo $TMPFILE
cat > $TMPFILE <<EOF
DROP DATABASE IF EXISTS demo;
CREATE DATABASE demo;
SHOW DATABASES;
use demo;
SHOW TABLES;
EOF

dotcloud run demo.db -- mysql -u$DOTCLOUD_DB_MYSQL_LOGIN -p$DOTCLOUD_DB_MYSQL_PASSWORD < $TMPFILE
