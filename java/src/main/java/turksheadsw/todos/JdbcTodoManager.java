package turksheadsw.todos;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

/**
 * Database-driven TodoManager
 *
 * NB: Only one of this class and MockTodoManager should have their
 * @Service annotation uncommented.
 */
@Service
public class JdbcTodoManager implements TodoManager
{
    private static final String SQL_ALL =
        "SELECT `id`, `content`, `done`, `order` FROM `todo`";
    private static final String SQL_ONE =
        "SELECT `id`, `content`, `done`, `order` FROM `todo` WHERE `id` = ?";
    private static final String SQL_CREATE =
        "INSERT INTO `todo` (`content`, `done`, `order`) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE =
        "UPDATE `todo` SET `content` = ?, `done` = ?, `order` = ? WHERE `id` = ?";
    private static final String SQL_DELETE =
        "DELETE FROM `todo` WHERE `id` = ?";

    private JdbcTemplate jdbc;

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Maps jdbc result sets to Todo objects in spring callbacks.
     */
    class TodoRowMapper implements RowMapper<Todo> {
        public Todo mapRow(ResultSet rs, int rowNum)
            throws SQLException
        {
            Todo todo = new Todo();
            todo.setId(rs.getInt("id"));
            todo.setContent(rs.getString("content"));
            todo.setDone(rs.getInt("done"));
            todo.setOrder(rs.getInt("order"));

            return todo;
        }
    }

    public List<Todo> getAll() {
        return jdbc.query(SQL_ALL, new TodoRowMapper());
    }

    public Todo getOne(int id) {
        return jdbc.queryForObject(
            SQL_ONE,
            new Object[] { id },
            new TodoRowMapper()
            );
    }

    /**
     * We have to use a PreparedStatementCreator in order to access the
     * auto-increment from the database.
     */
    class InsertPreparedStatementCreator implements PreparedStatementCreator {
        private Todo todo;


        public InsertPreparedStatementCreator(Todo todo) {
            this.todo = todo;
        }

        public PreparedStatement createPreparedStatement(Connection connection)
            throws SQLException
        {
            PreparedStatement ps =
                connection.prepareStatement(SQL_CREATE, new String[] {"id"});

            ps.setString(1, this.todo.getContent());
            ps.setInt(2, this.todo.getDone());
            ps.setInt(3, this.todo.getOrder());

            return ps;
        }
    }

    public Todo create(Todo todo) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(new InsertPreparedStatementCreator(todo), keyHolder);
        todo.setId(keyHolder.getKey().intValue());
        return todo;
    }

    public void update(int id, Todo todo) {
        jdbc.update(SQL_UPDATE, new Object[] {
            todo.getContent(), todo.getDone(), todo.getOrder(), id
        });
    }

    public void delete(int id) {
        jdbc.update(SQL_DELETE, new Object[] { id });
    }
}
