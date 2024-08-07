package se.lexicon;

import org.junit.jupiter.api.Test;
import se.lexicon.Model.*;
import se.lexicon.DAO.Collections.*;

import java.time.LocalDate;

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
    public void testAppUserDAOCollection() {
        // Create an AppUser object
        AppUser credentials = new AppUser("sami.alabed", "password123", AppRole.ROLE_APP_ADMIN);
        // Create another AppUser object
        AppUser credentials2 = new AppUser("julian", "password123", AppRole.ROLE_APP_USER);
        // Create an AppUserDAOCollection object
        AppUserDAOCollection appUserDAOCollection = new AppUserDAOCollection();
        // Persist the AppUser objects
        appUserDAOCollection.persist(credentials);
        // Test the persist method
        assert appUserDAOCollection.findAll().size() == 1;
        assert appUserDAOCollection.findAll().contains(credentials);
        // Test the findByUserName method
        assert appUserDAOCollection.findByUserName("sami.alabed").equals(credentials);
        // Persist another AppUser object
        appUserDAOCollection.persist(credentials2);
        // Test the remove method
        appUserDAOCollection.remove("sami.alabed");
        assert appUserDAOCollection.findAll().size() == 1;
        assert !appUserDAOCollection.findAll().contains(credentials);
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
    public void testPersonDAOCollection() {
        // Create a Person object
        Person sami = new Person("Sami", "Alabed", "sami.alabed@gmail.com");
        // Create another Person object
        Person julian = new Person("Julian", "Assange", "julian@wikileaks.com");
        // Create a PersonDAOCollection object
        PersonDAOCollection personDAOCollection = new PersonDAOCollection();
        // Persist the Person objects
        personDAOCollection.persist(sami);
        // Test the persist method
        assert personDAOCollection.findAll().size() == 1;
        assert personDAOCollection.findAll().contains(sami);
        // Test the findById method
        assert personDAOCollection.findById(sami.getId()).equals(sami);
        // Test the findByEmail method
        assert personDAOCollection.findByEmail("sami.alabed@gg") == null;
        assert personDAOCollection.findByEmail("sami.alabed@gmail.com").equals(sami);
        // Persist another Person object
        personDAOCollection.persist(julian);
        // Test the remove method
        personDAOCollection.remove(sami.getId());
        assert personDAOCollection.findAll().size() == 1;
        assert !personDAOCollection.findAll().contains(sami);
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
    public void testTodoItemDAOCollection() {
        // Create a Person object
        Person sami = new Person("Sami", "Alabed", "sami.alabed@gmail.com");
        // Create a TodoItem object
        TodoItem todoItem = new TodoItem("Assignment pt 1", "Todo-IT workshop", sami);
        // Create another TodoItem object
        TodoItem todoItem2 = new TodoItem("Assignment pt 2", "Todo-IT workshop", sami);
        // Create a TodoItemDAOCollection object
        TodoItemDAOCollection todoItemDAOCollection = new TodoItemDAOCollection();
        // Persist the TodoItem objects
        todoItemDAOCollection.persist(todoItem);
        // Test the persist method
        assert todoItemDAOCollection.findByTitleContains("Assignment").size() == 1;
        assert todoItemDAOCollection.findByTitleContains("Assignment").contains(todoItem);
        // Test the findById method
        assert todoItemDAOCollection.findById(todoItem.getId()).equals(todoItem);
        // Test the findByPersonId method
        assert todoItemDAOCollection.findByPersonId(sami.getId()).size() == 1;
        assert todoItemDAOCollection.findByPersonId(sami.getId()).contains(todoItem);
        // Test the findByDeadlineBefore method
        assert todoItemDAOCollection.findByDeadlineBefore(LocalDate.parse("2024-10-01")).size() == 1;
        assert todoItemDAOCollection.findByDeadlineBefore(LocalDate.parse("2024-10-01")).contains(todoItem);
        // Test the findByDeadlineAfter method
        assert todoItemDAOCollection.findByDeadlineAfter(LocalDate.parse("2024-10-01")).isEmpty();
        // Persist another TodoItem object
        todoItemDAOCollection.persist(todoItem2);
        // Test the remove method
        todoItemDAOCollection.remove(todoItem.getId());
        assert todoItemDAOCollection.findAllByDoneStatus(false).size() == 1;
        assert !todoItemDAOCollection.findAllByDoneStatus(false).contains(todoItem);

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

    @Test
    public void testTodoItemTaskDAOCollection() {
        // Create a Person object
        Person sami = new Person("Sami", "Alabed", "sami.alabed@gmail.com");
        // Create a TodoItem object
        TodoItem todoItem = new TodoItem("Assignment pt 1", "Todo-IT workshop", sami);
        // Create a TodoItemTask object
        TodoItemTask todoItemTask = new TodoItemTask(todoItem);
        // Assign the TodoItemTask to a person
        todoItemTask.setAssignee(sami);
        // Create another TodoItem object
        TodoItem todoItem2 = new TodoItem("Assignment pt 2", "Todo-IT workshop", sami);
        // Create another TodoItemTask object
        TodoItemTask todoItemTask2 = new TodoItemTask(todoItem2);
        // Create a TodoItemTaskDAOCollection object
        TodoItemTaskDAOCollection todoItemTaskDAOCollection = new TodoItemTaskDAOCollection();
        // Persist the TodoItemTask objects
        todoItemTaskDAOCollection.persist(todoItemTask);
        // Test the persist method
        assert todoItemTaskDAOCollection.findAll().size() == 1;
        assert todoItemTaskDAOCollection.findAll().contains(todoItemTask);
        // Test the findById method
        assert todoItemTaskDAOCollection.findById(todoItemTask.getId()).equals(todoItemTask);
        // Test the findByAssignedStatus method
        assert todoItemTaskDAOCollection.findByAssignedStatus(true).size() == 1;

        // Test the findByPersonId method
        assert todoItemTaskDAOCollection.findByPersonId(sami.getId()).size() == 1;
        assert todoItemTaskDAOCollection.findByPersonId(sami.getId()).contains(todoItemTask);

        // Persist another TodoItemTask object
        todoItemTaskDAOCollection.persist(todoItemTask2);

        // Test the remove method
        todoItemTaskDAOCollection.remove(todoItemTask.getId());
        assert todoItemTaskDAOCollection.findAll().size() == 1;




    }

}
