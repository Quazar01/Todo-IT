package se.lexicon.DAO.Collections;


import se.lexicon.DAO.IdSequencer.TodoItemTaskSequencer;
import se.lexicon.DAO.TodoItemTaskDAO;
import se.lexicon.Model.TodoItemTask;

import java.util.ArrayList;

public class TodoItemTaskDAOCollection implements TodoItemTaskDAO {

    ArrayList<TodoItemTask> todoItemTasks;

    // Constructor
    public TodoItemTaskDAOCollection() {
        todoItemTasks = new ArrayList<>();
    }

    @Override
    public TodoItemTask persist(TodoItemTask todoItemTask) {
        if (todoItemTask == null) {
            throw new IllegalArgumentException("TodoItemTask todoItemTask was null");
        }
        todoItemTask.setId(TodoItemTaskSequencer.nextId());
        todoItemTasks.add(todoItemTask);
        return todoItemTask;
    }

    @Override
    public TodoItemTask findById(int todoItemTaskId) {
        if (todoItemTaskId > 0) {
            for (TodoItemTask todoItemTask : todoItemTasks) {
                if (todoItemTask.getId() == todoItemTaskId) {
                    return todoItemTask;
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<TodoItemTask> findAll() {
        return todoItemTasks;
    }

    @Override
    public ArrayList<TodoItemTask> findByAssignedStatus(boolean isAssigned) {
        ArrayList<TodoItemTask> assignedStatus = new ArrayList<>();
        for (TodoItemTask todoItemTask : todoItemTasks) {
            if (todoItemTask.isAssigned() == isAssigned) {
                assignedStatus.add(todoItemTask);
            }
        }
        return assignedStatus;
    }

    @Override
    public ArrayList<TodoItemTask> findByPersonId(int personId) {
        if (personId < 0) {
            throw new IllegalArgumentException("PersonId must not be less than 0");
        }
        ArrayList<TodoItemTask> tasksAssignedToThePerson = new ArrayList<>();
        for (TodoItemTask todoItemTask : todoItemTasks) {
            if (todoItemTask.getAssignee().getId() == personId) {
                tasksAssignedToThePerson.add(todoItemTask);
            }
        }
        return tasksAssignedToThePerson;
    }

    @Override
    public void remove(int todoItemTaskId) {
        if (todoItemTaskId < 0) {
            throw new IllegalArgumentException("TodoItemTaskId must not be less than 0");
        }
        for (TodoItemTask todoItemTask : todoItemTasks) {
            if (todoItemTask.getId() == todoItemTaskId) {
                todoItemTasks.remove(todoItemTask);
                break;
            }
        }
    }
}
