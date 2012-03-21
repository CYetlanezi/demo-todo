<?php


/**
 * Provides basic database access templates using the PHP PDO library.
 *
 * Current implementation is very naive and doesn't do any kind of connection
 * reuse or caching.
 */
class PDOTemplate
{
    /**
     * Creates a PDOTemplate, saving the DB dsn and credentials for use on
     * each call.
     */
    public function PDOTemplate($dsn, $username, $password) {
        $this->dsn = $dsn;
        $this->username = $username;
        $this->password = $password;
    }

    /**
     * Opens a database connection using the credentials and DSN supplied to
     * the constructor.
     */
    public function get_connection() {
        $DBH = new PDO($this->dsn, $this->username, $this->password);
        $DBH->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        return $DBH;
    }

    /**
     * Executes a query as a prepared statement with the provided parameters
     * and returns all results as an array.
     */
    public function query_all($sql, $params) {
        $dbh = $this->get_connection();

        $sth = $dbh->prepare($sql);
        $sth->setFetchMode(PDO::FETCH_ASSOC);
        $sth->execute($params);

        $results = array();
        while ($obj = $sth->fetch()) {
            $results[] = $obj;
        } 

        $dbh = null;

        return $results;
    }

    /**
     * Executes a query as a prepared statement with the provided parameters
     * and only returns the first result.
     */
    public function query_one($sql, $params) {
        $dbh = $this->get_connection();

        $sth = $dbh->prepare($sql);
        $sth->setFetchMode(PDO::FETCH_ASSOC);
        $sth->execute($params);

        $result = $sth->fetch();

        $dbh = null;

        return $result;
    }

    /**
     * Executes a sql statement with the given parameters.
     *
     * The return value is PDO::lastInsertId.
     */
    public function execute($sql, $params) {
        $dbh = $this->get_connection();
        $sth = $dbh->prepare($sql);
        $sth->execute($params);
        $id = $dbh->lastInsertId();
        $dbh = null;
    }
}

?>
