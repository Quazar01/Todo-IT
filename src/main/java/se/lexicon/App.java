package se.lexicon;
import se.lexicon.DAO.Implementations.PeopleImpl;
import se.lexicon.DAO.Implementations.TodoItemsImpl;
import se.lexicon.Model.*;



/**
 * TODO: Add a Test class to test the classes in this package.
 */
public class App 
{
    public static void main( String[] args )
    {
        AppRole user = AppRole.ROLE_APP_USER;
        AppRole admin = AppRole.ROLE_APP_ADMIN;

        // Create a person
        PeopleImpl peopleImpl = new PeopleImpl();
        TodoItemsImpl todoItemsImpl = new TodoItemsImpl();

        // Create the persons in the database.
//        Person samiDB = peopleImpl.create(new Person("Sami", "Alabed"));
//        Person julianDB = peopleImpl.create(new Person ("Julian", "Assange"));
//
          Person sami = peopleImpl.findById(13);
          Person julian = peopleImpl.findById(14);

        System.out.println("PeopleImpl findAll: ");
        peopleImpl.findAll().forEach(System.out::println);
        System.out.println("PeopleImpl findById: ");
        System.out.println(peopleImpl.findById(julian.getPerson_id()));
        System.out.println("PeopleImpl findByName: ");
        peopleImpl.findByName("Sami").forEach(System.out::println);

//        System.out.println("PeopleImpl Update: ");
//        Person person2 = new Person("Test", "Testesson");
        // Set the new person's id to Sami's id.
//        person2.setPerson_id(sami.getPerson_id());
//        System.out.println(peopleImpl.Update(person2));

//        System.out.println("Delete Sami: ");
//        System.out.println(peopleImpl.delete(11));
//        System.out.println("Delete Julian: ");
//        System.out.println(peopleImpl.delete(12));

        // Create a TodoItem object
        TodoItem todoItem = new TodoItem("Test", "Test", sami);
        todoItem.setDeadLine("2024-12-31");
        TodoItem todoItem2 = new TodoItem("Test2", "Test2", julian);
        todoItem2.setDeadLine("2024-12-30");
        System.out.println(todoItem);

        // Create the todoItems in the database.
        System.out.println("TodoItemsImpl create: ");
//        System.out.println(todoItemsImpl.create(todoItem));
        System.out.println(todoItemsImpl.create(todoItem2));




        // List of AppUser objects
//        // Create an AppUser object
//        AppUser credentials = new AppUser("sami.alabed", "password123", admin);
//        // Create another AppUser object
//        AppUser credentials2 = new AppUser("julian", "password123", user);
//
//        // Use the AppUserDAOCollection class to persist the AppUser objects
//        AppUserDAOCollection appUserDAOCollection = new AppUserDAOCollection();
//        appUserDAOCollection.persist(credentials);
//        appUserDAOCollection.persist(credentials2);
//        System.out.println("AppUserDAOCollection:\n" + appUserDAOCollection.findAll());
//
//        // Use the AppUserDAOCollection class to find an AppUser object by username
//        System.out.println("AppUserDAOCollection:\n" + appUserDAOCollection.findByUserName("sami.alabed"));
//
//        // Use the AppUserDAOCollection class to remove an AppUser object by username
//        appUserDAOCollection.remove("sami.alabed");
//        System.out.println("AppUserDAOCollection:\n" + appUserDAOCollection.findAll());
    }
}
