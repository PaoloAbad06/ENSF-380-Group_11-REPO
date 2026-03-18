package edu.ucalgary.oop;

public class SmartThermostat extends SmartDevice<Integer> {

    public SmartThermostat() {
        super(20);
    }

    @Override
    public void update(String message) {
        if (message.equalsIgnoreCase("Sleep")) {
            adjustTemperature(18); 
        } else if (message.equalsIgnoreCase("Vacation")) {
            adjustTemperature(20); 
        }
    }

    @Override
    public void performAction() {
        System.out.println("Thermostat is maintaining temperature at " + getState() + "°C.");
    }

    public void adjustTemperature(int desiredTemp) {
        int currentTemp = getState();
        int difference = desiredTemp - currentTemp;

        if (difference > 0) {
            System.out.println("Increasing temperature by " + difference + "°C.");
        } else if (difference < 0) {
            System.out.println("Decreasing temperature by " + Math.abs(difference) + "°C.");
        }
        setState(desiredTemp);
    }
}
