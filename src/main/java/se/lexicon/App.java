package se.lexicon;

/**
 * TODO: Add a Test class to test the classes in this package.
 */
public class App 
{
    public static void main( String[] args )
    {
        // Create a Person object
        Person person = new Person("Sami", "Alabed", "sami.alabed@gmail.com");
        System.out.println("Person:");
        System.out.println(person.getSummary());
        // Create a TodoItem object
        TodoItem todoItem = new TodoItem("Assignment pt 1", "Todo-IT workshop", person);
        System.out.println("\nTodoItem:");
        System.out.println(todoItem.getSummary());
        // Create a TodoItemTask object
        TodoItemTask todoItemTask = new TodoItemTask(todoItem, person);
        System.out.println("\nTodoItemTask:");
        System.out.println(todoItemTask.getSummary());
    }
}
