package src.envirowear.thermostat.receivers;

import static org.junit.Assert.*;

import org.junit.Test;

import src.envirowear.thermostat.dto.Alert;
import src.envirowear.thermostat.dto.AlertType;
import src.envirowear.thermostat.dto.ReceiverReading;
import src.envirowear.thermostat.dto.SensorReading;
import src.envirowear.thermostat.dto.SensorType;
import src.envirowear.publishersubscriber.*;
public class EnvirowearReceiverTest {

	EnvirowearReceiver rec = new EnvirowearReceiver("1");

	@Test
	public void testInitialization() {
		
		// Body sensors should not be designated as working before any values have been received
		Event e = rec.validateBodySensor();
		Alert a = (Alert) e.getEvent();
		assertEquals("Body sensors are not working", a.getMessage());
		
		// Suit sensors should not be designated as working before any values have been received
		e = rec.validateSuitSensor();
		a = (Alert) e.getEvent();
		assertEquals(AlertType.SEVERE, a.getAlertType());
	}
		
	// Testing body sensor validation
	@Test
	public void testBodyValidationAllWorking() {
		// All five sensors are valid -- should get Working Great alert
		SensorReading bs1 = new SensorReading(SensorType.BODY_SENSOR, 98.4, "1");
		SensorReading bs2 = new SensorReading(SensorType.BODY_SENSOR, 98.5, "2");
		SensorReading bs3 = new SensorReading(SensorType.BODY_SENSOR, 98.5, "3");
		SensorReading bs4 = new SensorReading(SensorType.BODY_SENSOR, 98.4, "4");
		SensorReading bs5 = new SensorReading(SensorType.BODY_SENSOR, 98.5, "5");
		
		rec.react(new Event(bs1));
		rec.react(new Event(bs2));
		rec.react(new Event(bs3));
		rec.react(new Event(bs4));
		rec.react(new Event(bs5));
		
		
		Event e = rec.validateBodySensor();
		Alert a = (Alert) e.getEvent();
		
		// Should get "Working Great"
		assertEquals(AlertType.WORKING_GREAT, a.getAlertType());
		rec.clearReadings();
	}
	
	@Test
	public void testBodyValidationMostWorking() {
		// Three out of five sensors working -- get Warning alert
		SensorReading bs1 = new SensorReading(SensorType.BODY_SENSOR, 98.6, "1");
		SensorReading bs2 = new SensorReading(SensorType.BODY_SENSOR, 98.5, "2");
		SensorReading bs3 = new SensorReading(SensorType.BODY_SENSOR, 98.6, "3");
		SensorReading bs4 = new SensorReading(SensorType.BODY_SENSOR, 104, "4");
		SensorReading bs5 = new SensorReading(SensorType.BODY_SENSOR, 107, "5");
		
		rec.react(new Event(bs1));
		rec.react(new Event(bs2));
		rec.react(new Event(bs3));
		rec.react(new Event(bs4));
		rec.react(new Event(bs5));
		
		
		Event e = rec.validateBodySensor();
		Alert a = (Alert) e.getEvent();
		
		// Should get a warning
		assertEquals(AlertType.WARNING, a.getAlertType());
		rec.clearReadings();
	}
	
	@Test
	public void testBodyValidationSomeWorking() {
		// Two out of five sensors working -- should get Severe alert
		SensorReading bs1 = new SensorReading(SensorType.BODY_SENSOR, 90, "1");
		SensorReading bs2 = new SensorReading(SensorType.BODY_SENSOR, 90, "2");
		SensorReading bs3 = new SensorReading(SensorType.BODY_SENSOR, 97.8, "3");
		SensorReading bs4 = new SensorReading(SensorType.BODY_SENSOR, 104, "4");
		SensorReading bs5 = new SensorReading(SensorType.BODY_SENSOR, 97.7, "5");
		
		rec.react(new Event(bs1));
		rec.react(new Event(bs2));
		rec.react(new Event(bs3));
		rec.react(new Event(bs4));
		rec.react(new Event(bs5));
		
		
		Event e = rec.validateBodySensor();
		Alert a = (Alert) e.getEvent();
		
		assertEquals(AlertType.SEVERE, a.getAlertType());
		rec.clearReadings();
	}
	
	@Test
	public void testBodyValidationNoneWorking() {
		// No sensors working -- should get Severe alert
		SensorReading bs1 = new SensorReading(SensorType.BODY_SENSOR, 90, "1");
		SensorReading bs2 = new SensorReading(SensorType.BODY_SENSOR, 90, "2");
		SensorReading bs3 = new SensorReading(SensorType.BODY_SENSOR, 101.5, "3");
		SensorReading bs4 = new SensorReading(SensorType.BODY_SENSOR, 104, "4");
		SensorReading bs5 = new SensorReading(SensorType.BODY_SENSOR, 977, "5");
		
		rec.react(new Event(bs1));
		rec.react(new Event(bs2));
		rec.react(new Event(bs3));
		rec.react(new Event(bs4));
		rec.react(new Event(bs5));
		
		
		Event e = rec.validateBodySensor();
		Alert a = (Alert) e.getEvent();
		
		assertEquals(AlertType.SEVERE, a.getAlertType());
		rec.clearReadings();
	}
	
	// Testing suit sensor validation
	@Test
	public void testSuitValidationAllWorking() {
		// All five sensors are valid -- should get Working Great alert
		SensorReading ss1 = new SensorReading(SensorType.SUIT_SENSOR, 72.4, "1");
		SensorReading ss2 = new SensorReading(SensorType.SUIT_SENSOR, 72.5, "2");
		SensorReading ss3 = new SensorReading(SensorType.SUIT_SENSOR, 72.5, "3");
		SensorReading ss4 = new SensorReading(SensorType.SUIT_SENSOR, 72.4, "4");
		SensorReading ss5 = new SensorReading(SensorType.SUIT_SENSOR, 72.5, "5");
		
		rec.react(new Event(ss1));
		rec.react(new Event(ss2));
		rec.react(new Event(ss3));
		rec.react(new Event(ss4));
		rec.react(new Event(ss5));
		
		
		Event e = rec.validateSuitSensor();
		Alert a = (Alert) e.getEvent();
		
		// Should get "Working Great"
		assertEquals(AlertType.WORKING_GREAT, a.getAlertType());
		rec.clearReadings();
	}
	
	@Test
	public void testSuitValidationMostWorking() {
		// Three out of five sensors working -- get Warning alert
		SensorReading ss1 = new SensorReading(SensorType.SUIT_SENSOR, 72.6, "1");
		SensorReading ss2 = new SensorReading(SensorType.SUIT_SENSOR, 72.5, "2");
		SensorReading ss3 = new SensorReading(SensorType.SUIT_SENSOR, 72.6, "3");
		SensorReading ss4 = new SensorReading(SensorType.SUIT_SENSOR, 104, "4");
		SensorReading ss5 = new SensorReading(SensorType.SUIT_SENSOR, 107, "5");
		
		rec.react(new Event(ss1));
		rec.react(new Event(ss2));
		rec.react(new Event(ss3));
		rec.react(new Event(ss4));
		rec.react(new Event(ss5));
		
		
		Event e = rec.validateSuitSensor();
		Alert a = (Alert) e.getEvent();
		
		// Should get a warning
		assertEquals(AlertType.WARNING, a.getAlertType());
		rec.clearReadings();
	}
	
	@Test
	public void testSuitValidationSomeWorking() {
		// Two out of five sensors working -- should get Severe alert
		SensorReading ss1 = new SensorReading(SensorType.SUIT_SENSOR, 90, "1");
		SensorReading ss2 = new SensorReading(SensorType.SUIT_SENSOR, 90, "2");
		SensorReading ss3 = new SensorReading(SensorType.SUIT_SENSOR, 71.8, "3");
		SensorReading ss4 = new SensorReading(SensorType.SUIT_SENSOR, 104, "4");
		SensorReading ss5 = new SensorReading(SensorType.SUIT_SENSOR, 71.7, "5");
		
		rec.react(new Event(ss1));
		rec.react(new Event(ss2));
		rec.react(new Event(ss3));
		rec.react(new Event(ss4));
		rec.react(new Event(ss5));
		
		
		Event e = rec.validateSuitSensor();
		Alert a = (Alert) e.getEvent();
		
		assertEquals(AlertType.SEVERE, a.getAlertType());
		rec.clearReadings();
	}
	
	@Test
	public void testSuitValidationNoneWorking() {
		// No sensors working -- should get Severe alert
		SensorReading ss1 = new SensorReading(SensorType.SUIT_SENSOR, 90, "1");
		SensorReading ss2 = new SensorReading(SensorType.SUIT_SENSOR, 90, "2");
		SensorReading ss3 = new SensorReading(SensorType.SUIT_SENSOR, 101.5, "3");
		SensorReading ss4 = new SensorReading(SensorType.SUIT_SENSOR, 104, "4");
		SensorReading ss5 = new SensorReading(SensorType.SUIT_SENSOR, 977, "5");
		
		rec.react(new Event(ss1));
		rec.react(new Event(ss2));
		rec.react(new Event(ss3));
		rec.react(new Event(ss4));
		rec.react(new Event(ss5));
		
		
		Event e = rec.validateSuitSensor();
		Alert a = (Alert) e.getEvent();
		
		assertEquals(AlertType.SEVERE, a.getAlertType());
		rec.clearReadings();
	}
	
	@Test
	public void testGetReadingAllWorking() {
		// Check that average readings are accurate when all sensors are valid and receiver ID is correct
		SensorReading ss1 = new SensorReading(SensorType.SUIT_SENSOR, 72.4, "1");
		SensorReading ss2 = new SensorReading(SensorType.SUIT_SENSOR, 72.5, "2");
		SensorReading ss3 = new SensorReading(SensorType.SUIT_SENSOR, 72.5, "3");
		SensorReading ss4 = new SensorReading(SensorType.SUIT_SENSOR, 72.4, "4");
		SensorReading ss5 = new SensorReading(SensorType.SUIT_SENSOR, 72.5, "5");

		SensorReading bs1 = new SensorReading(SensorType.BODY_SENSOR, 98.4, "1");
		SensorReading bs2 = new SensorReading(SensorType.BODY_SENSOR, 98.5, "2");
		SensorReading bs3 = new SensorReading(SensorType.BODY_SENSOR, 98.5, "3");
		SensorReading bs4 = new SensorReading(SensorType.BODY_SENSOR, 98.4, "4");
		SensorReading bs5 = new SensorReading(SensorType.BODY_SENSOR, 98.5, "5");
		
		rec.react(new Event(ss1));
		rec.react(new Event(ss2));
		rec.react(new Event(ss3));
		rec.react(new Event(ss4));
		rec.react(new Event(ss5));

		rec.react(new Event(bs1));
		rec.react(new Event(bs2));
		rec.react(new Event(bs3));
		rec.react(new Event(bs4));
		rec.react(new Event(bs5));
		
		ReceiverReading recRead = rec.getReading();

		assertEquals(98.46, recRead.getAvgBodySensorReading(), 0.02);
		assertEquals(72.46, recRead.getAvgSuitSensorReading(), 0.02);
		assertEquals("1", recRead.getReceiverId());

	}
	
	@Test
	public void testGetReadingMostWorking() {
		// Check that average readings are accurate when some sensors are not working
		SensorReading ss1 = new SensorReading(SensorType.SUIT_SENSOR, 72.4, "1");
		SensorReading ss2 = new SensorReading(SensorType.SUIT_SENSOR, 72.5, "2");
		SensorReading ss3 = new SensorReading(SensorType.SUIT_SENSOR, 72.5, "3");
		SensorReading ss4 = new SensorReading(SensorType.SUIT_SENSOR, 44.4, "4");
		SensorReading ss5 = new SensorReading(SensorType.SUIT_SENSOR, 32.5, "5");

		SensorReading bs1 = new SensorReading(SensorType.BODY_SENSOR, 98.4, "1");
		SensorReading bs2 = new SensorReading(SensorType.BODY_SENSOR, 198.5, "2");
		SensorReading bs3 = new SensorReading(SensorType.BODY_SENSOR, 98.5, "3");
		SensorReading bs4 = new SensorReading(SensorType.BODY_SENSOR, 98.4, "4");
		SensorReading bs5 = new SensorReading(SensorType.BODY_SENSOR, 398.5, "5");
		
		rec.react(new Event(ss1));
		rec.react(new Event(ss2));
		rec.react(new Event(ss3));
		rec.react(new Event(ss4));
		rec.react(new Event(ss5));

		rec.react(new Event(bs1));
		rec.react(new Event(bs2));
		rec.react(new Event(bs3));
		rec.react(new Event(bs4));
		rec.react(new Event(bs5));
		
		ReceiverReading recRead = rec.getReading();

		assertEquals(98.43, recRead.getAvgBodySensorReading(), 0.02);
		assertEquals(72.47, recRead.getAvgSuitSensorReading(), 0.02);
	}
	
	@Test
	public void testGetReadingSomeWorking() {
		// Check that average readings are NaN when most sensors are not valid
		SensorReading ss1 = new SensorReading(SensorType.SUIT_SENSOR, 72.4, "1");
		SensorReading ss2 = new SensorReading(SensorType.SUIT_SENSOR, 172.5, "2");
		SensorReading ss3 = new SensorReading(SensorType.SUIT_SENSOR, 72.5, "3");
		SensorReading ss4 = new SensorReading(SensorType.SUIT_SENSOR, 44.4, "4");
		SensorReading ss5 = new SensorReading(SensorType.SUIT_SENSOR, 32.5, "5");

		SensorReading bs1 = new SensorReading(SensorType.BODY_SENSOR, 98.4, "1");
		SensorReading bs2 = new SensorReading(SensorType.BODY_SENSOR, 198.5, "2");
		SensorReading bs3 = new SensorReading(SensorType.BODY_SENSOR, 98.5, "3");
		SensorReading bs4 = new SensorReading(SensorType.BODY_SENSOR, 298.4, "4");
		SensorReading bs5 = new SensorReading(SensorType.BODY_SENSOR, 398.5, "5");
		
		rec.react(new Event(ss1));
		rec.react(new Event(ss2));
		rec.react(new Event(ss3));
		rec.react(new Event(ss4));
		rec.react(new Event(ss5));

		rec.react(new Event(bs1));
		rec.react(new Event(bs2));
		rec.react(new Event(bs3));
		rec.react(new Event(bs4));
		rec.react(new Event(bs5));
		
		ReceiverReading recRead = rec.getReading();

		assertTrue(Double.isNaN(recRead.getAvgBodySensorReading()));
		assertTrue(Double.isNaN(recRead.getAvgSuitSensorReading()));
	}
	
	@Test
	public void testGetReadingNoneWorking() {
		// Check that average readings are NaN when most sensors are not valid
		SensorReading ss1 = new SensorReading(SensorType.SUIT_SENSOR, -72.4, "1");
		SensorReading ss2 = new SensorReading(SensorType.SUIT_SENSOR, 172.5, "2");
		SensorReading ss3 = new SensorReading(SensorType.SUIT_SENSOR, 2.5, "3");
		SensorReading ss4 = new SensorReading(SensorType.SUIT_SENSOR, 44.4, "4");
		SensorReading ss5 = new SensorReading(SensorType.SUIT_SENSOR, 32.5, "5");

		SensorReading bs1 = new SensorReading(SensorType.BODY_SENSOR, 12.4, "1");
		SensorReading bs2 = new SensorReading(SensorType.BODY_SENSOR, 198.5, "2");
		SensorReading bs3 = new SensorReading(SensorType.BODY_SENSOR, -98.5, "3");
		SensorReading bs4 = new SensorReading(SensorType.BODY_SENSOR, 298.4, "4");
		SensorReading bs5 = new SensorReading(SensorType.BODY_SENSOR, 398.5, "5");
		
		rec.react(new Event(ss1));
		rec.react(new Event(ss2));
		rec.react(new Event(ss3));
		rec.react(new Event(ss4));
		rec.react(new Event(ss5));

		rec.react(new Event(bs1));
		rec.react(new Event(bs2));
		rec.react(new Event(bs3));
		rec.react(new Event(bs4));
		rec.react(new Event(bs5));
		
		ReceiverReading recRead = rec.getReading();

		assertTrue(Double.isNaN(recRead.getAvgBodySensorReading()));
		assertTrue(Double.isNaN(recRead.getAvgSuitSensorReading()));
	}


}
