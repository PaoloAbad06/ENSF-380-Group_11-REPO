package edu.ucalgary.oop;

public class SmartLock extends SmartDevice<Boolean> {

    public SmartLock() {
        super(true); // Default to LOCKED
    }

    public SmartLock(String name) {
        super(true);
    }

    @Override
    public void update(String message) {
        if (message.equalsIgnoreCase("Sleep") || message.equalsIgnoreCase("Vacation")) {
            setState(true); 
        }
        performAction();
    }

    @Override
    public void performAction() {
        String status = getState() ? "LOCKED" : "UNLOCKED";
        System.out.println("SmartLock is now " + status + ".");
    }
}
