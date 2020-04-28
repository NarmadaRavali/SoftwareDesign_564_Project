package src.envirowear.thermostat.regulator;

import static org.junit.Assert.*;

import org.junit.Test;

import src.envirowear.thermostat.sensors.*;
import src.envirowear.thermostat.dto.RegulatorReading;
import src.envirowear.thermostat.dto.RegulatorState;
import src.envirowear.thermostat.dto.SensorType;
import src.envirowear.thermostat.receivers.*;
import java.util.*;

public class RegulatorTest {

	Regulator reg = new Regulator();
	
	List<Sensor> sensors = new ArrayList<Sensor>();
	List<Receiver> receivers = new ArrayList<Receiver>();

	public void setSensorReceiverLists() {		
		for(int i = 0; i < 5; i++) {
			EnvirowearSensor bs = new EnvirowearSensor(SensorType.BODY_SENSOR, String.valueOf(i));
			EnvirowearSensor ss = new EnvirowearSensor(SensorType.SUIT_SENSOR, String.valueOf(i));
			sensors.add(bs);
			sensors.add(ss);
			
			EnvirowearReceiver rec = new EnvirowearReceiver(String.valueOf(i));
			receivers.add(rec);
		}
	}
	
	@Test
	public void testBodyTempNormalHeat() {
		// Body temperature is normal and suit temp < desired temp => regulator should start heating
		setSensorReceiverLists();
		RegulatorReading regRead = reg.getRegulatorReading(98.6, 72, 70, sensors, receivers);
		assertEquals(RegulatorState.HEATING, regRead.getRegulatorState());
	}
	
	@Test
	public void testBodyTempNormalCool() {
		// Body temperature is normal and suit temp > desired temp => regulator should start cooling
		setSensorReceiverLists();
		RegulatorReading regRead = reg.getRegulatorReading(98.6, 72, 74, sensors, receivers);
		assertEquals(RegulatorState.COOLING, regRead.getRegulatorState());
	}
	
	@Test
	public void testBodyTempNormalIdle() {
		// Body temperature is normal and suit temp = desired temp => regulator should take no action
		setSensorReceiverLists();
		RegulatorReading regRead = reg.getRegulatorReading(98.6, 72, 72, sensors, receivers);
		assertEquals(RegulatorState.IDLE, regRead.getRegulatorState());

	}
	
	@Test
	public void testBodyTempTooLow() {
		// Body temperature is below the lower limit of 97.7 => regulator should heat no matter what the desired temp is
		setSensorReceiverLists();
		RegulatorReading regRead = reg.getRegulatorReading(97.6, 72, 72, sensors, receivers);
		assertEquals(RegulatorState.HEATING, regRead.getRegulatorState());

	}
	
	@Test
	public void testBodyTempTooHigh() {
		// Body temperature is above the upper limit of 99.5 => regulator should cool no matter what the desired temp is
		setSensorReceiverLists();
		RegulatorReading regRead = reg.getRegulatorReading(99.6, 72, 72, sensors, receivers);
		assertEquals(RegulatorState.COOLING, regRead.getRegulatorState());

	}



}
