package turksheadsw.todos;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/entities/todos")
public class TodoController
{
    private Map<Integer, Todo> todos = new ConcurrentHashMap<Integer, Todo>();
    private TodoManager manager;

    @Autowired
    public void setManager(TodoManager manager) {
        this.manager = manager;
    }

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody List<Todo> getAllTodos() {
        return manager.getAll();
    }

    @RequestMapping(
        value="/{id}",
        method=RequestMethod.GET)
    public @ResponseBody Todo getOne(@PathVariable int id) {
        return manager.getOne(id);
    }

    @RequestMapping(method=RequestMethod.POST)
    public @ResponseBody Todo create(@RequestBody Todo todo) {
        return manager.create(todo);
    }

    @RequestMapping(
        value="/{id}",
        method=RequestMethod.POST,
        headers="X-HTTP-Method-Override=PUT")
    public @ResponseBody void update(@PathVariable int id, @RequestBody Todo todo) {
        manager.update(id, todo);
    }

    @RequestMapping(
        value="/{id}",
        method=RequestMethod.POST,
        headers="X-HTTP-Method-Override=DELETE")
    public @ResponseBody void delete(@PathVariable int id) {
        manager.delete(id);
    }
}
