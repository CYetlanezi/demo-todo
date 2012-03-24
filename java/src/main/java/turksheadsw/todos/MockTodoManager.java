package turksheadsw.todos;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * In-memory TodoManager used for testing.
 *
 * NB: Only one of this class and MockTodoManager should have their
 * @Service annotation uncommented.
 */
//@Service
public class MockTodoManager implements TodoManager
{
    /**
     * Indexed storage of our todos.
     */
    Map<Integer, Todo> todos = new HashMap<Integer, Todo>();

    int lastId = 0;

    public List<Todo> getAll() {
        return new ArrayList<Todo>(this.todos.values());
    }

    public Todo getOne(int id) {
        return this.todos.get(id);
    }

    public Todo create(Todo todo) {
        todo.setId(++this.lastId);
        this.todos.put(todo.getId(), todo);
        return todo;
    }

    public void update(int id, Todo todo) {
        Todo current = this.todos.get(id);

        if (current != null) {
            current.setContent(todo.getContent());
            current.setDone(todo.getDone());
            current.setOrder(todo.getOrder());
        }
    }

    public void delete(int id) {
        this.todos.remove(id);
    }
}
