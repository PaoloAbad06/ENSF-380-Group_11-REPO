package edu.ucalgary.oop;

public interface Observer<T> {
    void update(T state);
}
