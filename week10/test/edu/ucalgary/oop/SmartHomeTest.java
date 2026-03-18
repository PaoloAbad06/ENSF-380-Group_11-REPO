package edu.ucalgary.oop;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SmartHomeTest {
    private SmartHomeBuilder builder;
    private SmartLight light;
    private SmartThermostat thermostat;
    private SmartLock lock;

    @Before
    public void setUp() {
        builder = new SmartHomeBuilder();
        light = new SmartLight();
        thermostat = new SmartThermostat();
        lock = new SmartLock();
    }

    // 1) INHERITANCE: SmartLock, SmartLight, and SmartThermostat inherit from SmartDevice
    @Test
    public void testInheritance() {
        assertTrue("SmartLight should inherit from SmartDevice.", light instanceof SmartDevice);
        assertTrue("SmartThermostat should inherit from SmartDevice.", thermostat instanceof SmartDevice);
        assertTrue("SmartLock should inherit from SmartDevice.", lock instanceof SmartDevice);
    }

    // 2) GENERICS: getState() in each child comes from SmartDevice and is not overridden
    @Test
    public void testGenericsGetState() {
        light.setState(true);
        assertEquals("SmartLight's getState() should return a Boolean.", true, light.getState());

        thermostat.setState(22);
        assertEquals("SmartThermostat's getState() should return an Integer.", 22, (int) thermostat.getState());

        lock.setState(false);
        assertEquals("SmartLock's getState() should return a Boolean.", false, lock.getState());
    }

    // 3) GENERICS: setState() in each child comes from SmartDevice and is not overridden
    @Test
    public void testGenericsSetState() {
        light.setState(true);
        assertEquals("SmartLight's setState() should set a Boolean.", true, light.getState());

        thermostat.setState(22);
        assertEquals("SmartThermostat's setState() should set an Integer.", 22, (int) thermostat.getState());

        lock.setState(false);
        assertEquals("SmartLock's setState() should set a Boolean.", false, lock.getState());
    }

    // 4) INTERFACES: SmartDevice (or child) is realizing Observer
    @Test
    public void testInterfaces() {
        assertTrue("SmartLight should implement Observer.", light instanceof Observer);
        assertTrue("SmartThermostat should implement Observer.", thermostat instanceof Observer);
        assertTrue("SmartLock should implement Observer.", lock instanceof Observer);
    }

    // 5) BUILDER PATTERN: Must call build() to get SmartHome instance
    @Test
    public void testBuilderPattern_MustCallBuildBeforeUse() {
        // Add devices to builder
        builder.addDevice(light).addDevice(thermostat).addDevice(lock);
        
        // Build should return a new SmartHome instance
        SmartHome smartHome = builder.build();
        assertNotNull("build() should return a SmartHome instance", smartHome);
        
        // All methods should work immediately after build()
        smartHome.setDeviceState(light, true);
        assertTrue("SmartLight should be ON after setDeviceState.", light.getState());
        
        // Send the Sleep message and verify
        smartHome.sendMessage("Sleep");
        assertFalse("SmartLight should be OFF after Sleep message.", light.getState());
        assertEquals("SmartThermostat should be set to 18°C after Sleep message.", 18, (int) thermostat.getState());
        assertTrue("SmartLock should be LOCKED after Sleep message.", lock.getState());
    }

    // 6) OBSERVER PATTERN: Observers can be added through builder
    @Test
    public void testObserverPattern_AddAndRemoveObservers() {
        SmartHome smartHome = builder
            .addDevice(light)
            .addDevice(thermostat)
            .addDevice(lock)
            .build();
        
        smartHome.sendMessage("Sleep");
        // No exception means observers were added successfully
    }

    // 7) OBSERVER PATTERN: Observers respond to state "Sleep" appropriately
    @Test
    public void testObserverPattern_SleepState() {
        SmartHome smartHome = builder
            .addDevice(light)
            .addDevice(thermostat)
            .addDevice(lock)
            .build();
        
        smartHome.sendMessage("Sleep");

        assertFalse("SmartLight should be OFF in Sleep state.", light.getState());
        assertEquals("SmartThermostat should be set to 18°C in Sleep state.", 18, (int) thermostat.getState());
        assertTrue("SmartLock should be LOCKED in Sleep state.", lock.getState());
    }

    // 8) OBSERVER PATTERN: Observers respond to state "Vacation" appropriately
    @Test
    public void testObserverPattern_VacationState() {
        SmartHome smartHome = builder
            .addDevice(light)
            .addDevice(thermostat)
            .addDevice(lock)
            .build();
        
        smartHome.sendMessage("Vacation");

        assertNotNull("SmartLight should be ON or OFF randomly in Vacation state.", light.getState());
        assertEquals("SmartThermostat should be set to 20°C in Vacation state.", 20, (int) thermostat.getState());
        assertTrue("SmartLock should be LOCKED in Vacation state.", lock.getState());
    }

    // 9) OBSERVER PATTERN: Observers respond to setDeviceState
    @Test
    public void testObserverPattern_SetDeviceState() {
        SmartHome smartHome = builder
            .addDevice(light)
            .addDevice(thermostat)
            .addDevice(lock)
            .build();
        
        smartHome.setDeviceState(light, true);
        smartHome.setDeviceState(thermostat, 25);
        smartHome.setDeviceState(lock, false);

        assertTrue("SmartLight should be ON after setDeviceState.", light.getState());
        assertEquals("SmartThermostat should be set to 25°C after setDeviceState.", 25, (int) thermostat.getState());
        assertFalse("SmartLock should be UNLOCKED after setDeviceState.", lock.getState());
    }

    // 10) BUILDER PATTERN: Builder methods return builder for chaining
    @Test
    public void testBuilderPattern_MethodChaining() {
        SmartHomeBuilder returnedBuilder = builder.addDevice(light);
        assertSame("addDevice should return the builder for chaining", builder, returnedBuilder);
        
        returnedBuilder = builder.addDevice(thermostat);
        assertSame("Multiple addDevice calls should return builder", builder, returnedBuilder);
    }

    // 11) BUILDER PATTERN: Cannot build without adding at least one device
    @Test
    public void testBuilderPattern_MustHaveDeviceBeforeBuild() {
        try {
            builder.build();
            fail("Expected IllegalStateException when building with no devices.");
        } catch (IllegalStateException e) {
            assertEquals("No devices registered. Add at least one device before building.", e.getMessage());
        }
    }

    // 12) BUILDER PATTERN: Cannot set state for unregistered device
    @Test
    public void testBuilderPattern_UnregisteredDevice() {
        SmartHome smartHome = builder
            .addDevice(light)
            .build();
        
        try {
            smartHome.setDeviceState(thermostat, 22);
            fail("Expected IllegalArgumentException when setting state for unregistered device.");
        } catch (IllegalArgumentException e) {
            assertEquals("Device is not registered in this SmartHome.", e.getMessage());
        }
    }
}
