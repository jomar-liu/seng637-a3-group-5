package org.jfree.data;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.Before;
import org.junit.Test;

public class RangeTest {

    private Range range;

    @Before
    public void setUp() {
        range = new Range(1.0, 5.0);
    }

    // -------------------------
    // FUNCTION 1: contains()
    // -------------------------


    @Test
    public void testContainsBelowRange() {
        // Performing BVT just below the lower boundary (1.0)
        assertFalse("Value 0.0 should be outside the range (1.0 to 5.0)", range.contains(0.0));
    }

    @Test
    public void testContainsInsideRange() {
        // Testing a value inside the valid range (between 1.0 and 5.0)
        assertTrue("Value 3.0 should be inside the range (1.0 to 5.0)", range.contains(3.0));
    }

    @Test
    public void testContainsLowerBoundary() {
        // Performing BVT at the lower boundary value (1.0)
        assertTrue("Value 1.0 should be within the range (1.0 to 5.0)", range.contains(1.0));
    }

    @Test
    public void testContainsUpperBoundary() {
        // Performing BVT at the upper boundary value (5.0)
        assertTrue("Value 5.0 should be within the range (1.0 to 5.0)", range.contains(5.0));
    }

    @Test
    public void testContainsAboveRange() {
        // Performing BVT just above the upper boundary (5.0)
        assertFalse("Value 6.0 should be outside the range (1.0 to 5.0)", range.contains(6.0));
    }

    // -------------------------
    // FUNCTION 2: getLength()
    // -------------------------

    @Test
    public void testGetLength() {
        // Verifying that the length of the range (from 1.0 to 5.0) is correctly calculated
        assertEquals("Length of range (1.0 to 5.0) should be 4.0", 4.0, range.getLength(), 0.0001);
    }

    @Test
    public void testGetLengthSmallRange() {
        // Test with ranges that are close to boundaries
        Range smallRange = new Range(1.0, 1.1);
        assertEquals("Length of range (1.0 to 1.1) should be 0.1", 0.1, smallRange.getLength(), 0.0001);
    }

    @Test
    public void testGetLengthLargeRange() {
        // Test with ranges that are wide apart from the boundaries
        Range largeRange = new Range(1.0, 1000000.0);
        assertEquals("Length of range (1.0 to 1000000.0) should be 999999.0", 999999.0, largeRange.getLength(), 0.0001);
    }

    @Test
    public void testGetLengthZeroRange() {
        // Zero-length range (same bounds)
        Range zeroRange = new Range(5.0, 5.0);
        assertEquals("Length of range (5.0 to 5.0) should be 0.0", 0.0, zeroRange.getLength(), 0.0001);
    }

    @Test
    public void testGetLengthNegativeToPositive() {
        // Negative and positive range values
        Range negativeToPositive = new Range(-5.0, 5.0);
        assertEquals("Length of range (-5.0 to 5.0) should be 10.0", 10.0, negativeToPositive.getLength(), 0.0001);
    }

    // -----------------------------
    // FUNCTION 3: getCentralValue()
    // -----------------------------

    @Test
    public void testGetCentralValue() {
        // Testing function based on the range (between 1.0 and 5.0)
        assertEquals("Central value of range (1.0 to 5.0) should be 3.0", 3.0, range.getCentralValue(), 0.0001);
    }

    @Test
    public void testGetCentralValueSmallRange() {
        // Small range (1.0 to 1.1)
        Range smallRange = new Range(1.0, 1.1);
        assertEquals("Central value of range (1.0 to 1.1) should be 1.05", 1.05, smallRange.getCentralValue(), 0.0001);
    }

    @Test
    public void testGetCentralValueZeroRange() {
        // Zero-length range (5.0 to 5.0)
        Range zeroRange = new Range(5.0, 5.0);
        assertEquals("Central value of range (5.0 to 5.0) should be 5.0", 5.0, zeroRange.getCentralValue(), 0.0001);
    }

    @Test
    public void testGetCentralValueNegativeToPositive() {
        // Negative to positive range (-5.0 to 5.0)
        Range negativeToPositive = new Range(-5.0, 5.0);
        assertEquals("Central value of range (-5.0 to 5.0) should be 0.0", 0.0, negativeToPositive.getCentralValue(), 0.0001);
    }

    @Test
    public void testGetCentralValueLargeRange() {
        // Large range (1.0 to 1000000.0)
        Range largeRange = new Range(1.0, 1000000.0);
        assertEquals("Central value of range (1.0 to 1000000.0) should be 500000.5", 500000.5, largeRange.getCentralValue(), 0.0001);
    }


	@Test
	public void testGetCentralValueReverseRange() { // Reverse boundaries(5.0 to 1.0)
	     Range reverseRange = new Range(5.0, 1.0);
	     assertEquals(3.0,reverseRange.getCentralValue(), 0.0001); // Central value should still be calculated as the average
	 }



    // -----------------------------
    // FUNCTION 4: getUpperBound()
    // -----------------------------


    @Test
    public void testUpperBound() {
        // Verifying that the upper bound of the range is correctly set to 5 for the range (1 to 5)
        assertEquals("The upper bound of the range should be 5", 5.0, range.getUpperBound(), .000000001d);
    }

    @Test
    public void testUpperBoundZeroLengthRange() {
        // Verifying that the upper bound of a zero-length range (5.0 to 5.0) is correctly set to 5.0
        Range zeroLengthRange = new Range(5.0, 5.0);
        assertEquals("The upper bound of the range should be 5", 5.0, zeroLengthRange.getUpperBound(), .000000001d);
    }

    @Test
    public void testUpperBoundNegativeToPositiveRange() {
        // Verifying the upper bound of a range from -5.0 to 5.0
        Range negativeToPositiveRange = new Range(-5.0, 5.0);
        assertEquals("The upper bound of the range should be 5", 5.0, negativeToPositiveRange.getUpperBound(), .000000001d);
    }

    @Test
    public void testUpperBoundLargeRange() {
        // Verifying the upper bound of a very large range (from 1.0 to 1000000.0)
        Range largeRange = new Range(1.0, 1000000.0);
        assertEquals("The upper bound of the range should be 1000000", 1000000.0, largeRange.getUpperBound(), .000000001d);
    }


    @Test
    public void testUpperBoundReverseRange() { // Verifying the upper bound when the range is reversed (from 5.0 to 1.0)
        Range reverseRange = new Range(5.0, 1.0);
	    assertEquals("The upper bound of the range should still be 5", 5.0,reverseRange.getUpperBound(), .000000001d);
	 }




    // -----------------------------
    // FUNCTION 5: getLowerBound()
    // -----------------------------


    @Test
    public void testLowerBound() {
        // Verifying that the lower bound of the range is correctly set to 1 for the range (1 to 5)
        assertEquals("The lower bound of the range should be 1", 1, range.getLowerBound(), .000000001d);
    }

    @Test
    public void testLowerBoundZeroLengthRange() {
        // Verifying that the lower bound of a zero-length range (5.0 to 5.0) is correctly set to 5.0
        Range zeroLengthRange = new Range(5.0, 5.0);
        assertEquals("The lower bound of the range should be 5", 5.0, zeroLengthRange.getLowerBound(), .000000001d);
    }

    @Test
    public void testLowerBoundNegativeToPositiveRange() {
        // Verifying the lower bound of a range from -5.0 to 5.0
        Range negativeToPositiveRange = new Range(-5.0, 5.0);
        assertEquals("The lower bound of the range should be -5", -5.0, negativeToPositiveRange.getLowerBound(), .000000001d);
    }

    @Test
    public void testLowerBoundLargeRange() {
        // TC4: Verifying the lower bound of a very large range (from 1.0 to 1000000.0)
        Range largeRange = new Range(1.0, 1000000.0);
        assertEquals("The lower bound of the range should be 1", 1.0, largeRange.getLowerBound(), .000000001d);
    }


	@Test
	public void testLowerBoundReverseRange() {
	    // TC5: Verifying the lower bound when the range is reversed (from 5.0 to 1.0)
	    Range reverseRange = new Range(5.0, 1.0);
	    assertEquals("The lower bound of the range should be 1",1.0, reverseRange.getLowerBound(), .000000001d);
	 }


}




