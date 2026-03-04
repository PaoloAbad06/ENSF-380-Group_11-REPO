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

    // methods will go here
}
