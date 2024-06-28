package se.lexicon;

import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void test_main() {
        // Create a Person object
        Person person = new Person("Sami", "Alabed", "sami.alabed.94@gmail.com");

        assert person.getFirstName().equals("Sami");
        assert person.getLastName().equals("Alabed");
        assert person.getEmail().equals("sami.alabed.94@gmail.com");

        // Create a TodoItem object
        TodoItem todoItem = new TodoItem("Assignment pt 1", "Todo-IT workshop", person);

        assert todoItem.getTitle().equals("Assignment pt 1");
        assert todoItem.getDescription().equals("Todo-IT workshop");
        assert todoItem.getAssignee().equals(person);
        if (todoItem.isDone()) throw new AssertionError();

        // Create a TodoItemTask object
        TodoItemTask todoItemTask = new TodoItemTask(todoItem, person);

        assert todoItemTask.getTodoItem().equals(todoItem);
        assert todoItemTask.getAssignee().equals(person);
        assert todoItemTask.isAssigned();


    }
}
