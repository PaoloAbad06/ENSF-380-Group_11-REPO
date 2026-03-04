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
        Task[]
    }
    }

    public void completeTask(String id) {
        if task.id == id;

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



