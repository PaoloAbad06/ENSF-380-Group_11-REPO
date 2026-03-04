package edu.ucalgary.oop;

import java.util.Objects;

public class Task {
    private int id;
    private String title;
    private boolean isCompleted;
    public Task(int id, String title, boolean isCompleted)
    {
        this.id = id;
        this.isCompleted = isCompleted;
        this.title = String.valueOf(title);

    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return Objects.equals(id, task.id) &&
               Objects.equals(title, task.title) &&
               isCompleted == task.isCompleted; 
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, isCompleted);
    }
    
    public Task copy(){
           return  new Task(this.id, this.title, this.isCompleted);     
    }
}
