package src.envirowear;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class EnvirowearUtilsTest {

	@Test
	public void testToTwoDecimalPlaces() {		
		// Input that already has two decimal places
		Double d = EnvirowearUtils.toTwoDecimalPlaces(98.26);
		assertEquals(Double.valueOf(98.26), d);
		
		// Input with more than two decimal places -- round up
		d = EnvirowearUtils.toTwoDecimalPlaces(98.26942);
		assertEquals(Double.valueOf(98.27), d);
		
		// Input with more than two decimal places -- round down
		d = EnvirowearUtils.toTwoDecimalPlaces(98.26492);
		assertEquals(Double.valueOf(98.26), d);
	}
	
	@Test
	public void testToTwoDecimalPlacesStringFormat() {
		// Input that already has two decimal places
		String s = EnvirowearUtils.toTwoDecimalPlacesInStringFormat(98.26);
		assertEquals("98.26", s);
		
		// Input with more than two decimal places -- round up
		s = EnvirowearUtils.toTwoDecimalPlacesInStringFormat(98.26942);
		assertEquals("98.27", s);
		
		// Input with more than two decimal places -- round down
		s = EnvirowearUtils.toTwoDecimalPlacesInStringFormat(98.26492);
		assertEquals("98.26", s);



	}
	
	@Test
	public void testGenerateRandomDouble() {
		// Between two positive ints
		Double d = EnvirowearUtils.generateRandomDouble(0, 1);
		assertTrue(d >= 0);
		assertTrue(d <= 1);
		
		// Between a negative int and a positive int
		d = EnvirowearUtils.generateRandomDouble(-5, 10);
		assertTrue(d >= -5);
		assertTrue(d <= 10);

	}

}
