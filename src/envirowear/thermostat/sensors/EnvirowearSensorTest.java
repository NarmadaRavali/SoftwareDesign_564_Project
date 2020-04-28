package src.envirowear.thermostat.sensors;

import static org.junit.Assert.*;

import org.junit.Test;

import src.envirowear.thermostat.dto.SensorType;

public class EnvirowearSensorTest {

	@Test
	public void testBodySensorInitialization() {
		// Body sensor should be initialized to a value between 97.7 and 97.8
		EnvirowearSensor bodySensor = new EnvirowearSensor(SensorType.BODY_SENSOR, "1");
		double initBodyTempVal = Double.parseDouble(bodySensor.getSensorReading().getTextField().getText());
		assertTrue(97.7 <= initBodyTempVal);
		assertTrue(97.8 >= initBodyTempVal);
	}
	
	@Test
	public void testSuitSensorInitialization() {
		// Suit sensor should be initialized to a value between 73.1 and 73.2
		EnvirowearSensor suitSensor = new EnvirowearSensor(SensorType.SUIT_SENSOR, "1");
		double initSuitTempVal = Double.parseDouble(suitSensor.getSensorReading().getTextField().getText());
		assertTrue(73.1 <= initSuitTempVal);
		assertTrue(73.2 >= initSuitTempVal);
	}


}
