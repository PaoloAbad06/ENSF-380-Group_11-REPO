package edu.ucalgary.oop;
import java.util.ArrayList;
import java.util.List;

public class SmartHome {
    private List<SmartDevice<?>> devices;
    
    SmartHome(List<SmartDevice<?>> devices) {
        this.devices = new ArrayList<>(devices);
    }
    
    public <T> void setDeviceState(SmartDevice<T> device, T state) {
        if (!devices.contains(device)) {
            throw new IllegalArgumentException("Device is not registered in this SmartHome.");
        }
        device.setState(state);
    }
    
    public void sendMessage(String message) {
        System.out.println("SmartHome: Sending message - " + message);
    
        for (SmartDevice<?> device : devices) {
            device.update(message);
        }
    }
}
