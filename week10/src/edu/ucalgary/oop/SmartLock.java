// TODO: package, any includes, class declaration, constructor
//       and the methods performAction and update.

package edu.ucalgary.oop;

import java.util.Observer;

public class SmartLock extends SmartDevice<Boolean> implements Observer {

    // Constructor
    public SmartLock(String name) {
        super(name);
        this.setState(true); 
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

        String status;

        if (getState() == true) {
            status = "LOCKED";
            } 
        
        else {
           
            status = "UNLOCKED";
        }

        System.out.println("SmartLock " + getName() + " is now " + status + ".");

    }

    
    public void autoLock(int delayInSeconds) {
       
        System.out.println("Auto-lock enabled. Door will lock in " + delayInSeconds + " seconds.");
        new Thread(() -> {
            try {
                Thread.sleep(delayInSeconds * 1000L);
                setState(true);
                System.out.println("Door auto-locked.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
