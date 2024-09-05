package se.lexicon;
import se.lexicon.DAO.Implementations.PeopleImpl;
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
        Person sami = new Person("Sami", "Alabed");
        Person julian = new Person("Julian", "Assange");

        // Create the persons in the database.
        peopleImpl.create(sami);
        peopleImpl.create(julian);
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
//        System.out.println(peopleImpl.delete(sami.getPerson_id()));
//        System.out.println("Delete Julian: ");
//        System.out.println(peopleImpl.delete(julian.getPerson_id()));









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
