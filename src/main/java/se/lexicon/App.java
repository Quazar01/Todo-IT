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

        // Create a person
        PeopleImpl peopleImpl = new PeopleImpl();
        TodoItemsImpl todoItemsImpl = new TodoItemsImpl();

        // Create the persons in the database.
        Person sami = peopleImpl.create(new Person("Sami", "Alabed"));
        Person julian = peopleImpl.create(new Person ("Julian", "Assange"));


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



        // Create a TodoItem object
        TodoItem todoItem = new TodoItem("Test", "Test", sami);
        todoItem.setDeadLine("2024-12-31");
        TodoItem todoItem2 = new TodoItem("Test2", "Test2", julian);
        todoItem2.setDeadLine("2024-12-30");

        // Create the todoItems in the database.
        System.out.println("TodoItemsImpl create: ");
        todoItem = todoItemsImpl.create(todoItem);
        todoItem2 = todoItemsImpl.create(todoItem2);
        System.out.println(todoItem);
        System.out.println(todoItem2);

        // Find all the todoItems in the database.
        System.out.println("TodoItemsImpl findAll: ");
        todoItemsImpl.findAll().forEach(System.out::println);

//        // Find the todoItem by id.
        System.out.println("TodoItemsImpl findById: ");
        System.out.println(todoItemsImpl.findById(todoItem.getTodo_id()));
        System.out.println(todoItemsImpl.findById(todoItem2.getTodo_id()));

        // Find the todoItem by done status.
        System.out.println("TodoItemsImpl findByDoneStatus: ");
        todoItemsImpl.findByDoneStatus(false).forEach(System.out::println);

        // Find the todoItem by assignee id.
        System.out.println("TodoItemsImpl findByAssignee: ");
        todoItemsImpl.findByAssignee(sami.getPerson_id()).forEach(System.out::println);

        // Update the todoItem.
        // Assign the second todoItem to sami and set it to done.
        System.out.println("TodoItemsImpl Update: ");
        todoItem2.setDone(true);
        todoItem2.setAssignee(sami);
        System.out.println(todoItemsImpl.update(todoItem2));

        // Find the todoItem by assignee object. Returns empty collection.
        System.out.println("TodoItemsImpl findByAssignee: ");
        todoItemsImpl.findByAssignee(julian).forEach(System.out::println);

//        // Delete the todoItems.
//        System.out.println("Delete Test: ");
//        System.out.println(todoItemsImpl.delete(todoItem.getTodo_id()));
//        System.out.println("Delete Test2: ");
//        System.out.println(todoItemsImpl.delete(todoItem2.getTodo_id()));
//
//        System.out.println("Delete Sami: ");
//        System.out.println(peopleImpl.delete(sami.getPerson_id()));
//        System.out.println("Delete Julian: ");
//        System.out.println(peopleImpl.delete(julian.getPerson_id()));

    }
}
