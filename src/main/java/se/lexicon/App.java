package se.lexicon;
import se.lexicon.DAO.Collections.AppUserDAOCollection;
import se.lexicon.Model.*;

import java.util.ArrayList;


/**
 * TODO: Add a Test class to test the classes in this package.
 */
public class App 
{
    public static void main( String[] args )
    {
        AppRole user = AppRole.ROLE_APP_USER;
        AppRole admin = AppRole.ROLE_APP_ADMIN;

        // List of AppUser objects
        // Create an AppUser object
        AppUser credentials = new AppUser("sami.alabed", "password123", admin);
        // Create another AppUser object
        AppUser credentials2 = new AppUser("julian", "password123", user);

        // Use the AppUserDAOCollection class to persist the AppUser objects
        AppUserDAOCollection appUserDAOCollection = new AppUserDAOCollection();
        appUserDAOCollection.persist(credentials);
        appUserDAOCollection.persist(credentials2);
        System.out.println("AppUserDAOCollection:\n" + appUserDAOCollection.findAll());

        // Find an AppUser by username
        System.out.println("\nFind an AppUser by username:");
        System.out.println(appUserDAOCollection.findByUserName("sami.alabed"));
        // Remove an AppUser by username
        appUserDAOCollection.remove("sami.alabed");
        System.out.println("\nAppUserDAOCollection after removing an AppUser:\n" + appUserDAOCollection.findAll());


        // Create a Person object
        Person sami = new Person("Sami", "Alabed", "sami.alabed@gmail.com");
        // Set the credentials for the person
        sami.setCredentials(credentials);

        // print the person object using the overridden toString method
        System.out.println("Person:");
        System.out.println(sami);

        // Print the credentials of the person
        System.out.println("\nCredentials:");
        System.out.println(sami.getCredentials());

        // Create a TodoItem object
        TodoItem todoItem = new TodoItem("Assignment pt 1", "Todo-IT workshop", sami);
        System.out.println("\nTodoItem:");
        System.out.println(todoItem);
        // Create a TodoItemTask object
        TodoItemTask todoItemTask = new TodoItemTask(todoItem, sami);
        System.out.println("\nTodoItemTask:");
        System.out.println(todoItemTask);

        // Create another person object
        Person julian = new Person("Julian", "Assange", "Julian.assange@wikileaks.com");
        // Assign credentials to the new person
        credentials = new AppUser("julian.assange", "password123", user);
        julian.setCredentials(credentials);

        // Create another todoItem object and assign it to the same person
        TodoItem todoItem2 = new TodoItem("Assignment pt 2", "Todo-IT workshop", sami);
        // Compare todoItem and todoItem2
        System.out.println("\nComparing todoItem and todoItem2:");
        System.out.println(todoItem.equals(todoItem2)); // Should return false
    }
}
