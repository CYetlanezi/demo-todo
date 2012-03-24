<?php
### LOAD CONFIGURATION
# Import environment settings from DotCloud
$CONFIG_CLOUD = json_decode(file_get_contents(
    "/home/dotcloud/environment.json"),true);

# Database configuration
$DB_DSN_PARTS = array(
    "host" => $CONFIG_CLOUD['DOTCLOUD_DB_MYSQL_HOST'],
    "port" => $CONFIG_CLOUD['DOTCLOUD_DB_MYSQL_PORT'],
    "dbname" => str_replace("php", "", $CONFIG_CLOUD['DOTCLOUD_PROJECT']),
);
$DB_DSN = "mysql:";
foreach ($DB_DSN_PARTS as $key=>$value) {
    $DB_DSN = $DB_DSN . $key . "=" . $value . ";";
}

$DB_USER = $CONFIG_CLOUD['DOTCLOUD_DB_MYSQL_LOGIN'];
$DB_PASSWORD = $CONFIG_CLOUD['DOTCLOUD_DB_MYSQL_PASSWORD'];
?>
