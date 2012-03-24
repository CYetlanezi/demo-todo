package turksheadsw.todos;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * API for CRUD-management of a collection of Todo objects.
 */
public interface TodoManager
{
    /**
     * Retrieve all todos.
     */
    public List<Todo> getAll();

    /**
     * Get an individual todo.
     */
    public Todo getOne(int id);

    /**
     * Create a new Todo, returning back the new object with the id set.
     */
    public Todo create(Todo todo);

    /**
     * Update an existing todo. Ignores id of the todo object and uses the
     * id parameter.
     */
    public void update(int id, Todo todo);

    /**
     * Deletes the specified todo.
     */
    public void delete(int id);
}
