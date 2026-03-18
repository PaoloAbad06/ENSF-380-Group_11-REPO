package edu.ucalgary.oop;

public class SmartphoneApp {
    public static void main(String[] args) {
        // Create devices
        SmartLight light = new SmartLight();
        SmartThermostat thermostat = new SmartThermostat();
        SmartLock lock = new SmartLock();

        // Create and build SmartHome using Builder
        SmartHome smartHome = new SmartHomeBuilder()
                .addDevice(light)
                .addDevice(thermostat)
                .addDevice(lock)
                .build();

        // ==========================================
        // Show initial states
        // ==========================================
        System.out.println("\n=== Initial States ===");
        System.out.println("Light: " + (light.getState() ? "ON" : "OFF"));
        System.out.println("Thermostat: " + thermostat.getState() + "°C");
        System.out.println("Lock: " + (lock.getState() ? "LOCKED" : "UNLOCKED"));

        // ==========================================
        // Simulate explicit state changes
        // ==========================================
        System.out.println("=== Setting Explicit States ===");
        smartHome.setDeviceState(light, true); // Turn on the light
        smartHome.setDeviceState(thermostat, 22); // Set thermostat to 22°C
        smartHome.setDeviceState(lock, false); // Unlock the door

        // Print states after explicit changes
        System.out.println("=== States After Explicit Changes ===");
        System.out.println("Light: " + (light.getState() ? "ON" : "OFF"));
        System.out.println("Thermostat: " + thermostat.getState() + "°C");
        System.out.println("Lock: " + (lock.getState() ? "LOCKED" : "UNLOCKED"));

        // ==========================================
        // Simulate overarching messages
        // ==========================================
        System.out.println("\n=== Sending 'Sleep' Message ===");
        smartHome.sendMessage("Sleep");

        // Print states after "Sleep" message
        System.out.println("=== States After 'Sleep' Message ===");
        System.out.println("Light: " + (light.getState() ? "ON" : "OFF"));
        System.out.println("Thermostat: " + thermostat.getState() + "°C");
        System.out.println("Lock: " + (lock.getState() ? "LOCKED" : "UNLOCKED"));

        System.out.println("\n=== Sending 'Vacation' Message ===");
        smartHome.sendMessage("Vacation");

        // Print states after "Vacation" message
        System.out.println("=== States After 'Vacation' Message ===");
        System.out.println("Light: " + (light.getState() ? "ON" : "OFF"));
        System.out.println("Thermostat: " + thermostat.getState() + "°C");
        System.out.println("Lock: " + (lock.getState() ? "LOCKED" : "UNLOCKED"));
    }
}
