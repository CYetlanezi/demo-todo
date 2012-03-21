<?php


/**
 * Provides DB access for Todo entities.
 */
class TodoEntityManager extends AbstractEntityManager
{
    const SQL_INDEX = "SELECT * FROM `todo`";
    const SQL_GET = "SELECT * FROM `todo` WHERE `id` = :id";
    const SQL_CREATE = "INSERT INTO `todo` (`content`, `done`, `order`) VALUES (:content, :done, :order)";
    const SQL_UPDATE = "UPDATE `todo` SET `content` = :content, `done` = :done, `order` = :order WHERE `id` = :id";
    const SQL_DELETE = "DELETE FROM `todo` WHERE `id` = :id";

    public function index() {
        return $this->pdo->query_all(self::SQL_INDEX, array());
    }

    public function get($id) {
        return $this->pdo->query_one(self::SQL_GET, array("id"=>$id));
    }

    public function create($dict) {
        return $this->pdo->execute(self::SQL_CREATE, $dict);
    }

    public function update($id, $dict) {
        // make a copy of the dict and enforce the ID match
        $values = $dict;
        $values['id'] = $id;

        $this->pdo->execute(self::SQL_UPDATE, $values);
    }

    public function delete($id) {
        $this->pdo->execute(self::SQL_DELETE, array("id"=>$id));
    }
}

?>
