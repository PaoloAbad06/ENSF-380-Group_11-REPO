package edu.ucalgary.oop;

public abstract class SmartDevice<T> implements Observer {
    private T state;

    public SmartDevice(T initialState) {
        this.state = initialState;
    }

    public T getState() {
        return this.state;
    }

    public void setState(T state) {
        this.state = state;
    }

    @Override
    public abstract void update(String message);
    
    public abstract void performAction(); 
}
