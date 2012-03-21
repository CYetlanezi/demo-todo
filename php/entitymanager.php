<?php


/*
 * EntityManager - API for DB access on business objects.
 *
 * Business objects are exchanged merely as associative arrays.
 */
interface EntityManager 
{
    /** Retrieves all instances of the entity in a simple array. */
    public function index();

    /** Retrieves an individual instance of the entity by PK. */
    public function get($id);

    /**
     * Creates a new instance of the entity.
     *
     * The return value is the ID of the new instance.
     */
    public function create($dict);

    /** 
     * Updates an existing instance of the entity.
     *
     * NB: For now, assumes all fields are given each time, partial updates
     * are not implemented.
     */
    public function update($id, $dict);

    /** Delete an individual instance of the entity. */
    public function delete($id);
}


/**
 * Base class for real entity managers using a PDOTemplate that must be
 * provided by calling set_pdo() before the EntityManager methods are
 * called.
 */
abstract class AbstractEntityManager implements EntityManager
{
    public function set_pdo($pdo) {
        $this->pdo = $pdo;
    }
}

?>
