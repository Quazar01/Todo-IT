package se.lexicon.DAO.Implementations;

import se.lexicon.DAO.IdSequencer.TodoItemSequencer;
import se.lexicon.DAO.TodoItems;
import se.lexicon.Model.Person;
import se.lexicon.Model.TodoItem;
import se.lexicon.db.SQLConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class TodoItemsImpl implements TodoItems {

    Connection connection = SQLConnection.getConnection();
    PeopleImpl people = new PeopleImpl();

    @Override
    public TodoItem create(TodoItem todoItem) {
        String CREATE_TODO_ITEM = "INSERT INTO todo_item (title, description, deadline, done, assignee_id) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TODO_ITEM, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, todoItem.getTitle());
            preparedStatement.setString(2, todoItem.getDescription());
            preparedStatement.setDate(3, Date.valueOf(todoItem.getDeadLine()));
            preparedStatement.setBoolean(4, todoItem.isDone());
            preparedStatement.setInt(5, todoItem.getAssignee().getPerson_id());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                todoItem.setTodo_id(resultSet.getInt(1));
            }

            return todoItem;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<TodoItem> findAll() {
        List<TodoItem> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM todo_item");

            while (resultSet.next()) {
                // Get the assignee
                int assigneeId = resultSet.getInt("assignee_id");
                // Get the assignee from the database
                Person assignee = people.findById(assigneeId);

                TodoItem todoItem = new TodoItem(
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        assignee
                );
                todoItem.setTodo_id(resultSet.getInt("todo_id"));
                todoItem.setDone(resultSet.getBoolean("done"));
                // Get the deadline from the database
                LocalDate deadline = LocalDate.parse(resultSet.getString("deadline"));
                todoItem.setDeadLine(String.valueOf(deadline));
                result.add(todoItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public TodoItem findById(int todoItemId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM todo_item WHERE todo_id = ?");
            preparedStatement.setInt(1, todoItemId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Get the assignee
                int assigneeId = resultSet.getInt("assignee_id");
                // Get the assignee from the database
                Person assignee = people.findById(assigneeId);

                TodoItem todoItem = new TodoItem(
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        assignee
                );
                todoItem.setTodo_id(resultSet.getInt("todo_id"));
                todoItem.setDone(resultSet.getBoolean("done"));
                // Get the deadline from the database
                LocalDate deadline = LocalDate.parse(resultSet.getString("deadline"));
                todoItem.setDeadLine(String.valueOf(deadline));
                return todoItem;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Collection<TodoItem> findByDoneStatus(boolean done) {
        List<TodoItem> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM todo_item WHERE done = ?");
            preparedStatement.setBoolean(1, done);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Get the assignee
                int assigneeId = resultSet.getInt("assignee_id");
                // Get the assignee from the database
                Person assignee = people.findById(assigneeId);

                TodoItem todoItem = new TodoItem(
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        assignee
                );
                todoItem.setTodo_id(resultSet.getInt("todo_id"));
                todoItem.setDone(resultSet.getBoolean("done"));
                // Get the deadline from the database
                LocalDate deadline = LocalDate.parse(resultSet.getString("deadline"));
                todoItem.setDeadLine(String.valueOf(deadline));
                result.add(todoItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Collection<TodoItem> findByAssignee(int personId) {
        List<TodoItem> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM todo_item WHERE assignee_id = ?");
            preparedStatement.setInt(1, personId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Get the assignee
                int assigneeId = resultSet.getInt("assignee_id");
                // Get the assignee from the database
                Person assignee = people.findById(assigneeId);

                TodoItem todoItem = new TodoItem(
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        assignee
                );
                todoItem.setTodo_id(resultSet.getInt("todo_id"));
                todoItem.setDone(resultSet.getBoolean("done"));
                // Get the deadline from the database
                LocalDate deadline = LocalDate.parse(resultSet.getString("deadline"));
                todoItem.setDeadLine(String.valueOf(deadline));
                result.add(todoItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Collection<TodoItem> findByAssignee(Person person) {
        return findByAssignee(person.getPerson_id());
    }

    @Override
    public Collection<TodoItem> findByUnassignedTodoItems() {
        List<TodoItem> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM todo_item WHERE assignee_id IS NULL");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                TodoItem todoItem = new TodoItem(
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        null
                );
                todoItem.setTodo_id(resultSet.getInt("todo_id"));
                todoItem.setDone(resultSet.getBoolean("done"));
                // Get the deadline from the database
                LocalDate deadline = LocalDate.parse(resultSet.getString("deadline"));
                todoItem.setDeadLine(String.valueOf(deadline));
                result.add(todoItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public TodoItem update(TodoItem todoItem) {
        String UPDATE_TODO_ITEM = "UPDATE todo_item SET title = ?, description = ?, deadline = ?, done = ?, assignee_id = ? WHERE todo_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TODO_ITEM);
            preparedStatement.setString(1, todoItem.getTitle());
            preparedStatement.setString(2, todoItem.getDescription());
            preparedStatement.setDate(3, Date.valueOf(todoItem.getDeadLine()));
            preparedStatement.setBoolean(4, todoItem.isDone());
            preparedStatement.setInt(5, todoItem.getAssignee().getPerson_id());
            preparedStatement.setInt(6, todoItem.getTodo_id());

            preparedStatement.executeUpdate();

            return todoItem;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int todoItemId) {
        String DELETE_TODO_ITEM = "DELETE FROM todo_item WHERE todo_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TODO_ITEM);
            preparedStatement.setInt(1, todoItemId);

            int result = preparedStatement.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
