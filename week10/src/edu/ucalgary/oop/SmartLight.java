package edu.ucalgary.oop;

public class SmartLight extends SmartDevice<Boolean> {

    public SmartLight() {
        super(false); // Default to OFF
    }

    @Override
    public void update(String message) {
        if (message.equalsIgnoreCase("Sleep")) {
            setState(false);
        } else if (message.equalsIgnoreCase("Vacation")) {
            setState(Math.random() < 0.5); 
        }
        performAction();
    }

    @Override
    public void performAction() {
        System.out.println("Light is " + (getState() ? "ON" : "OFF"));
    }
}
