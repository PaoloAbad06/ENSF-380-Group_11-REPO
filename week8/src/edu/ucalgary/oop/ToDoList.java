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
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getId() == id) {
                task.setCompleted();
            }
        }
    }

    public void deleteTask(String id) {
        for (int i =0; i < tasks.size(); i++){
            if (tasks.get(i).getId().equals(id))
            {
                tasks.remove(i);
            }
        }
    }

    public void editTask() {

    }

    public void undo() {

    }

    public List<Task> listTasks() {
        return this.tasks;
    }
}


