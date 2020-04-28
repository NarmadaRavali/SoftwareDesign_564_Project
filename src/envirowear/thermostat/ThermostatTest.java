package src.envirowear.thermostat;

import static org.junit.Assert.*;
import static src.envirowear.gui.UiUtils.getAlertForType;

import org.junit.Test;
import src.envirowear.thermostat.sensors.*;
import src.envirowear.thermostat.receivers.*;
import src.envirowear.thermostat.dto.*;
import src.envirowear.gui.UiUtils;
import src.envirowear.publishersubscriber.Event;

import java.util.*;

import javax.swing.JOptionPane;

public class ThermostatTest {

	@Test
	public void testInitialization() {
		
		Thermostat t = new Thermostat();
		
		List<Sensor> bodySensors = t.getBodySensors();
		List<Sensor> suitSensors = t.getSuitSensors();
		List<Receiver> receivers = t.getReceivers();
		
		// Correct number of body sensors set in list
		assertEquals(5, bodySensors.size());
		
		// Correct number of suit sensors set in list
		assertEquals(5, suitSensors.size());
		
		// Correct number of receivers set in list
		assertEquals(3, receivers.size());
		
		// Default desired temperature set to 72
		assertEquals(72, t.getUserDesiredSuitTemperature(), 0);
	}
	
	@Test
	public void testDesiredTempValid() {
		// Default desired temperature will be 72, which should be a valid value
		Thermostat t = new Thermostat();

		ThermostatMetaData tmd = t.onClockSignal(new Signal());
		List<Event> events = tmd.getEvents();
        List<Alert> validationAlerts = getAlertForType(events, AlertType.VALIDATION);
        
        // No validation alerts should occur
        assertTrue(validationAlerts.isEmpty());

	}
	
	@Test
	public void testDesiredTempEdgeLow() {
		Thermostat t = new Thermostat();
		
		// Setting desired temp to lower limit of 60; should be valid
		t.getDesiredSuitTemperature().getTextField().setText("60");
		
		ThermostatMetaData tmd = t.onClockSignal(new Signal());
		List<Event> events = tmd.getEvents();
        List<Alert> validationAlerts = getAlertForType(events, AlertType.VALIDATION);
        
        // No validation alerts should occur
        assertTrue(validationAlerts.isEmpty());
	}
	
	@Test
	public void testDesiredTempEdgeHigh() {
		Thermostat t = new Thermostat();
		
		// Setting desired temp to upper limit of 78; should be valid
		t.getDesiredSuitTemperature().getTextField().setText("78");
		
		ThermostatMetaData tmd = t.onClockSignal(new Signal());
		List<Event> events = tmd.getEvents();
        List<Alert> validationAlerts = getAlertForType(events, AlertType.VALIDATION);
        
        // No validation alerts should occur
        assertTrue(validationAlerts.isEmpty());
	}

	
	@Test
	public void testDesiredTempTooLow() {
		Thermostat t = new Thermostat();
		
		// Setting desired temp < 60; should cause validation alert
		t.getDesiredSuitTemperature().getTextField().setText("59");
		
		ThermostatMetaData tmd = t.onClockSignal(new Signal());
		List<Event> events = tmd.getEvents();
        List<Alert> validationAlerts = getAlertForType(events, AlertType.VALIDATION);
        
        // Validation alert should be returned
        assertFalse(validationAlerts.isEmpty());
	}
	
	@Test
	public void testDesiredTempTooHigh() {
		Thermostat t = new Thermostat();
		
		// Setting desired temp > 78; should cause validation alert
		t.getDesiredSuitTemperature().getTextField().setText("79");
		
		ThermostatMetaData tmd = t.onClockSignal(new Signal());
		List<Event> events = tmd.getEvents();
        List<Alert> validationAlerts = getAlertForType(events, AlertType.VALIDATION);
        
        // Validation alert should be returned
        assertFalse(validationAlerts.isEmpty());
	}

}
