package edu.ucalgary.oop;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class ToDoList implements IToDoList {

    private List<Task> tasks;
    private Stack<List<Task>> history;

    public ToDoList() {
        tasks = new ArrayList<>();
        history = new Stack<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void completeTask(String id) {
        history.add(List.copyOf(tasks));
        for (int i =0; i < tasks.size(); i++){
            if (tasks.get(i).getId().equals(id))
            {
                tasks.get(i).setCompleted(true);
            }
        }

    }

    public void deleteTask(String id) {
        history.add(List.copyOf(tasks));
        for (int i =0; i < tasks.size(); i++){
            if (tasks.get(i).getId().equals(id))
            {
                tasks.remove(i);
            }
        }
    }

    public void editTask(String id, String title, boolean isCompleted) {
        for (int i =0; i < tasks.size(); i++){
            if (tasks.get(i).getId().equals(id)){
                tasks.get(i).setTitle(title);
                tasks.get(i).setCompleted(isCompleted);
            }
        }
    }

    public void undo() {
        tasks = history.firstElement();
        history.pop();
    }
    public List<Task> listTasks() {
        return this.tasks;
    }
}


