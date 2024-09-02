package se.lexicon;
import se.lexicon.DAO.Collections.AppUserDAOCollection;
import se.lexicon.DAO.Collections.PeopleCollection;
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
        PeopleCollection peopleCollection = new PeopleCollection();
        Person person = new Person("Sami", "Alabed");
        Person person1 = new Person("Julian", "Alabed");

        System.out.println("Person:\n" + person);
        System.out.println("Person1:\n" + person1);

        peopleCollection.create(person);
        peopleCollection.create(person1);

        System.out.println("PeopleCollection:\n" + peopleCollection.findAll());
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
