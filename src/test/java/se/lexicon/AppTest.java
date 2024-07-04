package se.lexicon;

import org.junit.jupiter.api.Test;
import se.lexicon.Model.*;

public class AppTest {

    @Test
    public void testAppUser() {
        // Create a AppUser object
        AppUser credentials = new AppUser("sami.alabed", "password123", AppRole.ROLE_APP_ADMIN);
        assert credentials.getUsername().equals("sami.alabed");
        assert credentials.getPassword().equals("password123");
        assert credentials.getRole().equals(AppRole.ROLE_APP_ADMIN);
        // Create a non-valid AppUser object
        // Check for a null username;
        try {
            new AppUser(null, "password123", AppRole.ROLE_APP_ADMIN);
            throw new AssertionError();
        } catch (IllegalArgumentException e) {
            assert e.getMessage().contains("username must not be null or empty");
        }
        // Check for an empty password;
        try {
            new AppUser("sami.alabed", " ", AppRole.ROLE_APP_ADMIN);
            throw new AssertionError();
        } catch (IllegalArgumentException e) {
            assert e.getMessage().contains("password must not be null or empty");
        }
        // Check for a null role;
        try {
            new AppUser("sami.alabed", "password123", null);
            throw new AssertionError();
        } catch (IllegalArgumentException e) {
            assert e.getMessage().contains("role must not be null");
        }

        // Create another AppUser object with the same username, password, and role.
        AppUser credentials2 = new AppUser("sami.alabed", "password123", AppRole.ROLE_APP_ADMIN);
        // Assert that the two AppUsers are equal.
        assert credentials.equals(credentials2);
        // Assert the hashcode of the two AppUsers are equal.
        assert credentials.hashCode() == credentials2.hashCode();

        // Create another AppUser object with different username, password, and role.
        AppUser credentials3 = new AppUser("julian.assange", "password123", AppRole.ROLE_APP_USER);
        // Assert that the two AppUsers are not equal.
        assert !credentials.equals(credentials3);
        // Assert the hashcode of the two AppUsers are not equal.
        assert credentials.hashCode() != credentials3.hashCode();
    }

    @Test
    public void testPerson() {
        // Create a Person object
        Person sami = new Person("Sami", "Alabed", "sami.alabed.94@gmail.com");
        // Create credentials for sami.
        AppUser credentials = new AppUser("sami.alabed", "password123", AppRole.ROLE_APP_ADMIN);
        // Set the credentials for the person
        sami.setCredentials(credentials);
        assert sami.getId() > 0;
        assert sami.getFirstName().equals("Sami");
        assert sami.getLastName().equals("Alabed");
        assert sami.getEmail().equals("sami.alabed.94@gmail.com");
        assert sami.getFullName().equals("Sami Alabed");
        // Assert that the credentials are the same as the one we created.
        assert sami.getCredentials().equals(credentials);
        // Assert that the credentials.toString() does not contain the password.
        assert !sami.getCredentials().toString().contains("password123");

        // Create a non-valid person object
        try {
            new Person(null, "Alabed", " ");
            throw new AssertionError();
        } catch (IllegalArgumentException e) {
            assert e.getMessage().contains("firstName must not be null or empty");
        }
        try {
            new Person("Sami", " ", " ");
            throw new AssertionError();
        } catch (IllegalArgumentException e) {
            assert e.getMessage().contains("lastName must not be null or empty");
        }
        try {
            new Person("Sami", "Alabed", "sami.alabed");
            throw new AssertionError();
        } catch (IllegalArgumentException e) {
            assert e.getMessage().contains("email must contain @");
        }

        // Create another person with the same firstName, lastName, and email.
        Person sami2 = new Person("Sami", "Alabed", "sami.alabed.94@gmail.com");
        // Assert that the two persons are equal.
        assert sami.equals(sami2);
        // Assert the hashcode of the two persons are equal.
        assert sami.hashCode() == sami2.hashCode();

        // Create another person with different firstName, lastName, and email.
        Person julian = new Person("Julian", "Assange", "julian@wikileaks.com");
        // Assert that the two persons are not equal.
        assert !sami.equals(julian);
        // Assert the hashcode of the two persons are not equal.
        assert sami.hashCode() != julian.hashCode();
    }

    @Test
    public void testTodoItem() {
        // Create a Person object
        Person sami = new Person("Sami", "Alabed", "sami@gmail.com");
        // Create a TodoItem object
        TodoItem todoItem = new TodoItem("Assignment pt 1", "Todo-IT workshop",sami);
        // Create another person object.
        Person julian = new Person("Julian", "Assange", "julian@wikileaks.com");
        // Set the assignee for the todoItem
        todoItem.setAssignee(julian);
        assert todoItem.getTitle().equals("Assignment pt 1");
        assert todoItem.getDescription().equals("Todo-IT workshop");
        assert todoItem.getAssignee().equals(julian);
        // Check if the todoItem is not done when created.
        if (todoItem.isDone()) throw new AssertionError();
        // Assign due date to the todoItem.
        todoItem.setDeadLine("2024-10-01");
        assert todoItem.getDeadLine().toString().equals("2024-10-01");
        // Check if the todoItem is overdue.
        if (todoItem.isOverDue()) throw new AssertionError();
        // Set the todoItem to done.
        todoItem.setDone(true);
        // Check if the todoItem is done.
        if (!todoItem.isDone()) throw new AssertionError();

        // Try to set a null assignee.
        try {
            todoItem.setAssignee(null);
            throw new AssertionError();
        } catch (NullPointerException e) {
            assert e.getMessage().contains("creator must not be null");
        }
        // Try to set a null deadLine.
        try {
            todoItem.setDeadLine(null);
            throw new AssertionError();
        } catch (IllegalArgumentException e) {
            assert e.getMessage().contains("deadLine must not be null or empty");
        }
        // Try to set a past deadLine.
        try {
            todoItem.setDeadLine("2020-10-01");
            throw new AssertionError();
        } catch (IllegalArgumentException e) {
            assert e.getMessage().contains("deadLine must not be before current date.");
        }
    }

    @Test
    public void testTodoItemTask() {
        // Create a Person object
        Person sami = new Person("Sami", "Alabed", "sami@gmail.com");
        // Create a TodoItem object
        TodoItem todoItem = new TodoItem("Assignment pt 1", "Todo-IT workshop",sami);
        // Create a TodoItemTask object
        TodoItemTask todoItemTask = new TodoItemTask(todoItem);
        assert todoItemTask.getTodoItem().equals(todoItem);
        assert todoItemTask.getAssignee() == null;
        if (todoItemTask.isAssigned()) throw new AssertionError();

        // Create another person object.
        Person julian = new Person("Julian", "Assange", "julian@wikileaks.com");
        // Set the assignee for the todoItemTask
        todoItemTask.setAssignee(julian);
        assert todoItemTask.getAssignee().equals(julian);
        if (!todoItemTask.isAssigned()) throw new AssertionError();
    }
}
