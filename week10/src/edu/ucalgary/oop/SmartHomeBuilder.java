package edu.ucalgary.oop;
import java.util.ArrayList;
import java.util.List;

public class SmartHomeBuilder {
    private List<SmartDevice<?>> devices = new ArrayList<>();
    
    public SmartHomeBuilder addDevice(SmartDevice<?> device) {
        devices.add(device);
        return this; // Return this for method chaining
    }
    
    public SmartHome build() {
        if (devices.isEmpty()) {
            throw new IllegalStateException("No devices registered. Add at least one device before building.");
        }
        return new SmartHome(devices);
    }
}
