package edu.ucalgary.oop;
import java.util.ArrayList;
import java.util.List;

public class SmartHome {
    private List<SmartDevice<?>> devices;
    
    // TODO: Implement package-private constructor
    // Constructor should NOT be public - only SmartHomeBuilder should create instances
    // Should initialize the devices field with the provided list
    // Consider making a defensive copy of the list to prevent external modification
    SmartHome(List<SmartDevice<?>> devices) {
        // Student to implement
        // Initialize this.devices with the provided devices list
        // Consider: this.devices = new ArrayList<>(devices);
    }
    
    // TODO: Implement setDeviceState functionality
    // Should throw IllegalArgumentException if the device is not in the devices list
    // Should call device.setState(state) on the provided device
    // The method uses generics <T> to handle different device state types
    public <T> void setDeviceState(SmartDevice<T> device, T state) {
        // Student to implement
        // Check if devices contains the provided device
        // If not, throw new IllegalArgumentException("Device is not registered in this SmartHome.")
        // Otherwise, call device.setState(state)
    }
    
    // TODO: Implement sendMessage functionality
    // Should print "SmartHome: Sending message - " + message
    // Should call device.update(message) for each device in the devices list
    public void sendMessage(String message) {
        // Student to implement
        // Print: System.out.println("SmartHome: Sending message - " + message);
        // Loop through devices and call device.update(message) for each one
    }
}
