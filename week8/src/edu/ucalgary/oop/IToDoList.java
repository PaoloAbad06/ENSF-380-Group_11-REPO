package edu.ucalgary.oop;
import java.util.List;


public interface IToDoList {
    void addTask(Task task);
    void completeTask(String id);
    void deleteTask(String id);
    void editTask(String id, String newTitle, boolean isCompleted);
    void undo();
    List<Task> listTasks();
    
}
