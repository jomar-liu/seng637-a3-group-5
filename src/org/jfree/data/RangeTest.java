package org.jfree.data;
 
import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;
    public class RangeTest {
     
        private Range range;
        private Range positiveRange;   // [1.0,  5.0]
        private Range negativeRange;   // [-5.0, -1.0]
        private Range mixedRange;      // [-3.0,  3.0]
     
        @Before
        public void setUp() {
            range = new Range(1.0, 5.0);
            positiveRange   = new Range(1.0,  5.0);
            negativeRange   = new Range(-5.0, -1.0);
            mixedRange      = new Range(-3.0,  3.0);
        }
     
        // =========================================================
        // FUNCTION 1: contains(double value)
        // =========================================================
     
        @Test
        public void testContainsBelowLowerBound() {
            assertFalse("Value just below lower bound (0.0) should be outside range [1.0, 5.0]",
                    range.contains(0.0));
        }
     
        @Test
        public void testContainsAtLowerBoundary() {
            assertTrue("Value at lower boundary (1.0) should be inside range [1.0, 5.0]",
                    range.contains(1.0));
        }
     
        @Test
        public void testContainsMidRange() {
            assertTrue("Value inside range (3.0) should be inside range [1.0, 5.0]",
                    range.contains(3.0));
        }
     
        @Test
        public void testContainsAtUpperBoundary() {
            assertTrue("Value at upper boundary (5.0) should be inside range [1.0, 5.0]",
                    range.contains(5.0));
        }
     
        @Test
        public void testContainsAboveUpperBound() {
            assertFalse("Value above upper bound (6.0) should be outside range [1.0, 5.0]",
                    range.contains(6.0));
        }
     
        @Test
        public void testContainsJustAboveLowerBound() {
            assertTrue("Value just above lower bound (1.0001) should be inside range [1.0, 5.0]",
                    range.contains(1.0001));
        }
     
        @Test
        public void testContainsJustBelowUpperBound() {
            assertTrue("Value just below upper bound (4.9999) should be inside range [1.0, 5.0]",
                    range.contains(4.9999));
        }
     
        @Test
        public void testContainsNegativeValueInNegativeRange() {
            Range negRange = new Range(-10.0, -1.0);
            assertTrue("Value -5.0 should be inside range [-10.0, -1.0]",
                    negRange.contains(-5.0));
        }
     
        @Test
        public void testContainsNaNValue() {
            assertFalse("NaN value should not be inside range [1.0, 5.0]",
                    range.contains(Double.NaN));
        }
     
        @Test
        public void testContainsPositiveInfinity() {
            assertFalse("Positive infinity should be outside range [1.0, 5.0]",
                    range.contains(Double.POSITIVE_INFINITY));
        }
     
        @Test
        public void testContainsNegativeInfinity() {
            assertFalse("Negative infinity should be outside range [1.0, 5.0]",
                    range.contains(Double.NEGATIVE_INFINITY));
        }
     
        // =========================================================
        // FUNCTION 2: getLength()
        // =========================================================
     
        @Test
        public void testGetLengthNormalRange() {
            assertEquals("Length of [1.0, 5.0] should be 4.0",
                    4.0, range.getLength(), 0.0001);
        }
     
        @Test
        public void testGetLengthSmallRange() {
            Range smallRange = new Range(1.0, 1.1);
            assertEquals("Length of [1.0, 1.1] should be 0.1",
                    0.1, smallRange.getLength(), 0.0001);
        }
     
        @Test
        public void testGetLengthLargeRange() {
            Range largeRange = new Range(1.0, 1000000.0);
            assertEquals("Length of [1.0, 1000000.0] should be 999999.0",
                    999999.0, largeRange.getLength(), 0.0001);
        }
     
        @Test
        public void testGetLengthZeroRange() {
            Range zeroRange = new Range(5.0, 5.0);
            assertEquals("Length of [5.0, 5.0] should be 0.0",
                    0.0, zeroRange.getLength(), 0.0001);
        }
     
        @Test
        public void testGetLengthNegativeToPositive() {
            Range negToPos = new Range(-5.0, 5.0);
            assertEquals("Length of [-5.0, 5.0] should be 10.0",
                    10.0, negToPos.getLength(), 0.0001);
        }
     
        @Test
        public void testGetLengthBothNegative() {
            Range negRange = new Range(-10.0, -2.0);
            assertEquals("Length of [-10.0, -2.0] should be 8.0",
                    8.0, negRange.getLength(), 0.0001);
        }
     
        // =========================================================
        // FUNCTION 3: getCentralValue()
        // =========================================================
     
        @Test
        public void testGetCentralValueNormalRange() {
            assertEquals("Central value of [1.0, 5.0] should be 3.0",
                    3.0, range.getCentralValue(), 0.0001);
        }
     
        @Test
        public void testGetCentralValueSmallRange() {
            Range smallRange = new Range(1.0, 1.1);
            assertEquals("Central value of [1.0, 1.1] should be 1.05",
                    1.05, smallRange.getCentralValue(), 0.0001);
        }
     
        @Test
        public void testGetCentralValueZeroRange() {
            Range zeroRange = new Range(5.0, 5.0);
            assertEquals("Central value of [5.0, 5.0] should be 5.0",
                    5.0, zeroRange.getCentralValue(), 0.0001);
        }
     
        @Test
        public void testGetCentralValueNegativeToPositive() {
            Range negToPos = new Range(-5.0, 5.0);
            assertEquals("Central value of [-5.0, 5.0] should be 0.0",
                    0.0, negToPos.getCentralValue(), 0.0001);
        }
     
        @Test
        public void testGetCentralValueLargeRange() {
            Range largeRange = new Range(1.0, 1000000.0);
            assertEquals("Central value of [1.0, 1000000.0] should be 500000.5",
                    500000.5, largeRange.getCentralValue(), 0.0001);
        }
     
        @Test
        public void testGetCentralValueBothNegative() {
            Range negRange = new Range(-8.0, -2.0);
            assertEquals("Central value of [-8.0, -2.0] should be -5.0",
                    -5.0, negRange.getCentralValue(), 0.0001);
        }
     
        @Test
        public void testGetCentralValueSymmetric() {
            Range symRange = new Range(-3.0, 3.0);
            assertEquals("Central value of [-3.0, 3.0] should be 0.0",
                    0.0, symRange.getCentralValue(), 0.0001);
        }
     
        // =========================================================
        // FUNCTION 4: getUpperBound()
        // =========================================================
     
        @Test
        public void testGetUpperBoundNormalRange() {
            assertEquals("Upper bound of [1.0, 5.0] should be 5.0",
                    5.0, range.getUpperBound(), 0.000000001d);
        }
     
        @Test
        public void testGetUpperBoundZeroLengthRange() {
            Range zeroRange = new Range(5.0, 5.0);
            assertEquals("Upper bound of [5.0, 5.0] should be 5.0",
                    5.0, zeroRange.getUpperBound(), 0.000000001d);
        }
     
        @Test
        public void testGetUpperBoundNegativeToPositive() {
            Range negToPos = new Range(-5.0, 5.0);
            assertEquals("Upper bound of [-5.0, 5.0] should be 5.0",
                    5.0, negToPos.getUpperBound(), 0.000000001d);
        }
     
        @Test
        public void testGetUpperBoundLargeRange() {
            Range largeRange = new Range(1.0, 1000000.0);
            assertEquals("Upper bound of [1.0, 1000000.0] should be 1000000.0",
                    1000000.0, largeRange.getUpperBound(), 0.000000001d);
        }
     
        @Test
        public void testGetUpperBoundBothNegative() {
            Range negRange = new Range(-10.0, -2.0);
            assertEquals("Upper bound of [-10.0, -2.0] should be -2.0",
                    -2.0, negRange.getUpperBound(), 0.000000001d);
        }
     
        @Test
        public void testGetUpperBoundNegativeUpperBound() {
            Range negRange = new Range(-100.0, -0.001);
            assertEquals("Upper bound of [-100.0, -0.001] should be -0.001",
                    -0.001, negRange.getUpperBound(), 0.000000001d);
        }
     
        // =========================================================
        // FUNCTION 5: getLowerBound()
        // =========================================================
     
        @Test
        public void testGetLowerBoundNormalRange() {
            assertEquals("Lower bound of [1.0, 5.0] should be 1.0",
                    1.0, range.getLowerBound(), 0.000000001d);
        }
     
        @Test
        public void testGetLowerBoundZeroLengthRange() {
            Range zeroRange = new Range(5.0, 5.0);
            assertEquals("Lower bound of [5.0, 5.0] should be 5.0",
                    5.0, zeroRange.getLowerBound(), 0.000000001d);
        }
     
        @Test
        public void testGetLowerBoundNegativeToPositive() {
            Range negToPos = new Range(-5.0, 5.0);
            assertEquals("Lower bound of [-5.0, 5.0] should be -5.0",
                    -5.0, negToPos.getLowerBound(), 0.000000001d);
        }
     
        @Test
        public void testGetLowerBoundLargeRange() {
            Range largeRange = new Range(1.0, 1000000.0);
            assertEquals("Lower bound of [1.0, 1000000.0] should be 1.0",
                    1.0, largeRange.getLowerBound(), 0.000000001d);
        }
     
        @Test
        public void testGetLowerBoundBothNegative() {
            Range negRange = new Range(-10.0, -2.0);
            assertEquals("Lower bound of [-10.0, -2.0] should be -10.0",
                    -10.0, negRange.getLowerBound(), 0.000000001d);
        }
     
        @Test
        public void testGetLowerBoundNegativeLowerBound() {
            Range negRange = new Range(-0.001, 5.0);
            assertEquals("Lower bound of [-0.001, 5.0] should be -0.001",
                    -0.001, negRange.getLowerBound(), 0.000000001d);
        }
     
        // =========================================================
        // FUNCTION 6: intersects(double lower, double upper)
        // =========================================================
     
        @Test
        public void testIntersectsCompleteOverlap() {
            assertTrue("Range [2.0, 4.0] should intersect [1.0, 5.0]",
                    range.intersects(2.0, 4.0));
        }
     
        @Test
        public void testIntersectsPartialOverlapLeft() {
            assertTrue("Range [0.0, 2.0] should intersect [1.0, 5.0]",
                    range.intersects(0.0, 2.0));
        }
     
        @Test
        public void testIntersectsPartialOverlapRight() {
            assertTrue("Range [4.0, 6.0] should intersect [1.0, 5.0]",
                    range.intersects(4.0, 6.0));
        }
     
        @Test
        public void testIntersectsNoOverlapLeft() {
            assertFalse("Range [-2.0, 0.0] should NOT intersect [1.0, 5.0]",
                    range.intersects(-2.0, 0.0));
        }
     
        @Test
        public void testIntersectsNoOverlapRight() {
            assertFalse("Range [6.0, 8.0] should NOT intersect [1.0, 5.0]",
                    range.intersects(6.0, 8.0));
        }
     
        @Test
        public void testIntersectsTouchingLowerBound() {
            assertTrue("Range [0.0, 1.0] touching lower bound should intersect [1.0, 5.0]",
                    range.intersects(0.0, 1.0));
        }
     
        @Test
        public void testIntersectsTouchingUpperBound() {
            assertTrue("Range [5.0, 7.0] touching upper bound should intersect [1.0, 5.0]",
                    range.intersects(5.0, 7.0));
        }
     
        @Test
        public void testIntersectsIdenticalRange() {
            assertTrue("Identical range [1.0, 5.0] should intersect [1.0, 5.0]",
                    range.intersects(1.0, 5.0));
        }
     
        @Test
        public void testIntersectsLargerRange() {
            assertTrue("Range [-10.0, 10.0] containing range should intersect [1.0, 5.0]",
                    range.intersects(-10.0, 10.0));
        }
     
        // =========================================================
        // FUNCTION 7: constrain(double value)
        // =========================================================
     
        @Test
        public void testConstrainValueInsideRange() {
            assertEquals("Constraining 3.0 within [1.0, 5.0] should return 3.0",
                    3.0, range.constrain(3.0), 0.0001);
        }
     
        @Test
        public void testConstrainValueBelowRange() {
            assertEquals("Constraining -1.0 within [1.0, 5.0] should return 1.0 (lower bound)",
                    1.0, range.constrain(-1.0), 0.0001);
        }
     
        @Test
        public void testConstrainValueAboveRange() {
            assertEquals("Constraining 10.0 within [1.0, 5.0] should return 5.0 (upper bound)",
                    5.0, range.constrain(10.0), 0.0001);
        }
     
        @Test
        public void testConstrainAtLowerBound() {
            assertEquals("Constraining 1.0 (lower bound) within [1.0, 5.0] should return 1.0",
                    1.0, range.constrain(1.0), 0.0001);
        }
     
        @Test
        public void testConstrainAtUpperBound() {
            assertEquals("Constraining 5.0 (upper bound) within [1.0, 5.0] should return 5.0",
                    5.0, range.constrain(5.0), 0.0001);
        }
        
        @Test
        public void testConstrain_NaNInput_ReturnsNaN() {
            // Branch: value is NaN — constrain should propagate NaN, not clamp it
            double result = range.constrain(Double.NaN);
            assertTrue("constrain(NaN) should return NaN", Double.isNaN(result));
        }
        
       
     
        // =========================================================
        // FUNCTION 8: combine(Range range1, Range range2)
        // =========================================================
     
        @Test
        public void testCombineTwoRanges() {
            Range r1 = new Range(1.0, 5.0);
            Range r2 = new Range(3.0, 8.0);
            Range combined = Range.combine(r1, r2);
            assertEquals("Combined lower bound should be 1.0", 1.0, combined.getLowerBound(), 0.0001);
            assertEquals("Combined upper bound should be 8.0", 8.0, combined.getUpperBound(), 0.0001);
        }
     
        @Test
        public void testCombineFirstRangeNull() {
            Range r2 = new Range(3.0, 8.0);
            Range combined = Range.combine(null, r2);
            assertEquals("Combining null with [3.0, 8.0] should return [3.0, 8.0]",
                    r2, combined);
        }
     
        @Test
        public void testCombineSecondRangeNull() {
            Range r1 = new Range(1.0, 5.0);
            Range combined = Range.combine(r1, null);
            assertEquals("Combining [1.0, 5.0] with null should return [1.0, 5.0]",
                    r1, combined);
        }
     
        @Test
        public void testCombineBothRangesNull() {
            Range combined = Range.combine(null, null);
            assertNull("Combining two nulls should return null", combined);
        }
     
        @Test
        public void testCombineNonOverlappingRanges() {
            Range r1 = new Range(1.0, 3.0);
            Range r2 = new Range(7.0, 10.0);
            Range combined = Range.combine(r1, r2);
            assertEquals("Combined lower should be 1.0", 1.0, combined.getLowerBound(), 0.0001);
            assertEquals("Combined upper should be 10.0", 10.0, combined.getUpperBound(), 0.0001);
        }
     
        // =========================================================
        // FUNCTION 9: expandToInclude(Range range, double value)
        // =========================================================
     
        @Test
        public void testExpandToIncludeValueBelowRange() {
            Range expanded = Range.expandToInclude(range, -1.0);
            assertEquals("Expanded lower bound should be -1.0", -1.0, expanded.getLowerBound(), 0.0001);
            assertEquals("Expanded upper bound should remain 5.0", 5.0, expanded.getUpperBound(), 0.0001);
        }
     
        @Test
        public void testExpandToIncludeValueAboveRange() {
            Range expanded = Range.expandToInclude(range, 10.0);
            assertEquals("Expanded lower bound should remain 1.0", 1.0, expanded.getLowerBound(), 0.0001);
            assertEquals("Expanded upper bound should be 10.0", 10.0, expanded.getUpperBound(), 0.0001);
        }
     
        @Test
        public void testExpandToIncludeValueInsideRange() {
            Range expanded = Range.expandToInclude(range, 3.0);
            assertEquals("Expanding with value inside range should return same lower 1.0",
                    1.0, expanded.getLowerBound(), 0.0001);
            assertEquals("Expanding with value inside range should return same upper 5.0",
                    5.0, expanded.getUpperBound(), 0.0001);
        }
     
        @Test
        public void testExpandToIncludeNullRange() {
            Range expanded = Range.expandToInclude(null, 3.0);
            assertNotNull("Expanding null range should return non-null Range", expanded);
            assertEquals("New range from null should have lower 3.0", 3.0, expanded.getLowerBound(), 0.0001);
            assertEquals("New range from null should have upper 3.0", 3.0, expanded.getUpperBound(), 0.0001);
        }
     
        // =========================================================
        // FUNCTION 10: expand(Range range, double lowerMargin, double upperMargin)
        // =========================================================
     
        @Test
        public void testExpandWithPositiveMargins() {
            Range expanded = Range.expand(range, 0.25, 0.5);
            // length = 4.0, lower expands by 0.25*4 = 1.0, upper expands by 0.5*4 = 2.0
            assertEquals("Expanded lower bound should be 0.0", 0.0, expanded.getLowerBound(), 0.0001);
            assertEquals("Expanded upper bound should be 7.0", 7.0, expanded.getUpperBound(), 0.0001);
        }
     
        @Test
        public void testExpandWithZeroMargins() {
            Range expanded = Range.expand(range, 0.0, 0.0);
            assertEquals("Zero margin: lower should remain 1.0", 1.0, expanded.getLowerBound(), 0.0001);
            assertEquals("Zero margin: upper should remain 5.0", 5.0, expanded.getUpperBound(), 0.0001);
        }
     
        // =========================================================
        // FUNCTION 11: shift(Range base, double delta, boolean allowZeroCrossing)
        // =========================================================
     
        @Test
        public void testShiftPositiveDelta() {
            Range shifted = Range.shift(range, 2.0);
            assertEquals("Shifted lower bound should be 3.0", 3.0, shifted.getLowerBound(), 0.0001);
            assertEquals("Shifted upper bound should be 7.0", 7.0, shifted.getUpperBound(), 0.0001);
        }
     
        @Test
        public void testShiftNegativeDelta() {
            Range shifted = Range.shift(range, -1.0);
            assertEquals("Shifted lower bound should be 0.0", 0.0, shifted.getLowerBound(), 0.0001);
            assertEquals("Shifted upper bound should be 4.0", 4.0, shifted.getUpperBound(), 0.0001);
        }
     
        @Test
        public void testShiftWithZeroCrossingAllowed() {
            Range base = new Range(-1.0, 1.0);
            Range shifted = Range.shift(base, 3.0, true);
            assertEquals("Shifted lower bound should be 2.0", 2.0, shifted.getLowerBound(), 0.0001);
            assertEquals("Shifted upper bound should be 4.0", 4.0, shifted.getUpperBound(), 0.0001);
        }
     
        @Test
        public void testShiftWithZeroCrossingNotAllowed() {
            Range base = new Range(1.0, 5.0);
            Range shifted = Range.shift(base, -3.0, false);
            // Lower bound 1.0 - 3.0 = -2.0 → clamped to 0.0 (no zero crossing)
            assertEquals("Clamped lower bound should be 0.0", 0.0, shifted.getLowerBound(), 0.0001);
            assertEquals("Shifted upper bound should be 2.0", 2.0, shifted.getUpperBound(), 0.0001);
        }
     
        // =========================================================
        // FUNCTION 12: equals(Object obj)
        // =========================================================
     
        @Test
        public void testEqualsWithSameRange() {
            Range other = new Range(1.0, 5.0);
            assertTrue("Two ranges with same bounds should be equal", range.equals(other));
        }
     
        @Test
        public void testEqualsWithDifferentLowerBound() {
            Range other = new Range(2.0, 5.0);
            assertFalse("Ranges with different lower bounds should not be equal", range.equals(other));
        }
     
        @Test
        public void testEqualsWithDifferentUpperBound() {
            Range other = new Range(1.0, 6.0);
            assertFalse("Ranges with different upper bounds should not be equal", range.equals(other));
        }
     
        @Test
        public void testEqualsWithNull() {
            assertFalse("Range should not equal null", range.equals(null));
        }
     
        @Test
        public void testEqualsWithDifferentType() {
            assertFalse("Range should not equal a non-Range object", range.equals("1.0 to 5.0"));
        }
     
        // =========================================================
        // FUNCTION 13: toString()
        // =========================================================
     
        @Test
        public void testToStringFormat() {
            String result = range.toString();
            assertNotNull("toString() should not return null", result);
            assertTrue("toString() should contain lower bound", result.contains("1.0"));
            assertTrue("toString() should contain upper bound", result.contains("5.0"));
        }
     
        // =========================================================
        // FUNCTION 14: hashCode()
        // =========================================================
     
        @Test
        public void testHashCodeConsistency() {
            Range other = new Range(1.0, 5.0);
            assertEquals("Equal ranges must have equal hash codes",
                    range.hashCode(), other.hashCode());
        }
     
        @Test
        public void testHashCodeDifferentRanges() {
            Range other = new Range(2.0, 6.0);
            // Not strictly required by contract, but usually different
            assertNotEquals("Different ranges typically have different hash codes",
                    range.hashCode(), other.hashCode());
        }
        
        
        private double invokeMax(double d1, double d2) throws Exception {
            Method m = Range.class.getDeclaredMethod("max", double.class, double.class);
            m.setAccessible(true);
            return (double) m.invoke(null, d1, d2);
        }
     
        /**
         * Invoke Range.min(double, double) via reflection.
         * Returns the double result.
         */
        private double invokeMin(double d1, double d2) throws Exception {
            Method m = Range.class.getDeclaredMethod("min", double.class, double.class);
            m.setAccessible(true);
            return (double) m.invoke(null, d1, d2);
        }
     
        // =======================================================================
        // FUNCTION 15:  max(double d1, double d2) — via reflection              ===
        // =======================================================================
     
        // --- Branch 1: d1 is NaN → return d2 ---
     
        @Test
        public void testMaxD1NaN_ReturnsD2() throws Exception {
            // When d1 is NaN the method must return d2 unchanged.
            double result = invokeMax(Double.NaN, 3.0);
            assertEquals("max(NaN, 3.0) should return 3.0", 3.0, result, 0.0);
        }
     
        @Test
        public void testMaxD1NaN_D2NegativeValue() throws Exception {
            // d2 can be any real number, including negative.
            double result = invokeMax(Double.NaN, -7.5);
            assertEquals("max(NaN, -7.5) should return -7.5", -7.5, result, 0.0);
        }
     
        @Test
        public void testMaxD1NaN_D2IsZero() throws Exception {
            double result = invokeMax(Double.NaN, 0.0);
            assertEquals("max(NaN, 0.0) should return 0.0", 0.0, result, 0.0);
        }
     
        @Test
        public void testMaxD1NaN_D2IsPositiveInfinity() throws Exception {
            double result = invokeMax(Double.NaN, Double.POSITIVE_INFINITY);
            assertEquals("max(NaN, +Inf) should return +Inf",
                    Double.POSITIVE_INFINITY, result, 0.0);
        }
     
        @Test
        public void testMaxD1NaN_D2IsNaN_ReturnD2NaN() throws Exception {
            // Both NaN: d1 is NaN so we return d2, which is also NaN.
            double result = invokeMax(Double.NaN, Double.NaN);
            assertTrue("max(NaN, NaN) should return NaN", Double.isNaN(result));
        }
     
        // --- Branch 2: d1 is real, d2 is NaN → return d1 ---
     
        @Test
        public void testMaxD2NaN_ReturnsD1() throws Exception {
            double result = invokeMax(3.0, Double.NaN);
            assertEquals("max(3.0, NaN) should return 3.0", 3.0, result, 0.0);
        }
     
        @Test
        public void testMaxD2NaN_D1NegativeValue() throws Exception {
            double result = invokeMax(-7.5, Double.NaN);
            assertEquals("max(-7.5, NaN) should return -7.5", -7.5, result, 0.0);
        }
     
        @Test
        public void testMaxD2NaN_D1IsZero() throws Exception {
            double result = invokeMax(0.0, Double.NaN);
            assertEquals("max(0.0, NaN) should return 0.0", 0.0, result, 0.0);
        }
     
        @Test
        public void testMaxD2NaN_D1IsNegativeInfinity() throws Exception {
            double result = invokeMax(Double.NEGATIVE_INFINITY, Double.NaN);
            assertEquals("max(-Inf, NaN) should return -Inf",
                    Double.NEGATIVE_INFINITY, result, 0.0);
        }
     
        // --- Branch 3: neither NaN → return Math.max(d1, d2) ---
     
        @Test
        public void testMaxBothPositive_D1Larger() throws Exception {
            double result = invokeMax(9.0, 4.0);
            assertEquals("max(9.0, 4.0) should return 9.0", 9.0, result, 0.0);
        }
     
        @Test
        public void testMaxBothPositive_D2Larger() throws Exception {
            double result = invokeMax(4.0, 9.0);
            assertEquals("max(4.0, 9.0) should return 9.0", 9.0, result, 0.0);
        }
     
        @Test
        public void testMaxEqual_ReturnsSameValue() throws Exception {
            double result = invokeMax(5.0, 5.0);
            assertEquals("max(5.0, 5.0) should return 5.0", 5.0, result, 0.0);
        }
     
        @Test
        public void testMaxBothNegative_LessNegativeReturned() throws Exception {
            double result = invokeMax(-2.0, -8.0);
            assertEquals("max(-2.0, -8.0) should return -2.0", -2.0, result, 0.0);
        }
     
        @Test
        public void testMaxNegativeAndPositive() throws Exception {
            double result = invokeMax(-3.0, 3.0);
            assertEquals("max(-3.0, 3.0) should return 3.0", 3.0, result, 0.0);
        }
     
        @Test
        public void testMaxZeroAndPositive() throws Exception {
            double result = invokeMax(0.0, 1.0);
            assertEquals("max(0.0, 1.0) should return 1.0", 1.0, result, 0.0);
        }
     
        @Test
        public void testMaxPositiveInfinity() throws Exception {
            double result = invokeMax(Double.POSITIVE_INFINITY, 1000.0);
            assertEquals("max(+Inf, 1000) should return +Inf",
                    Double.POSITIVE_INFINITY, result, 0.0);
        }
     
        @Test
        public void testMaxNegativeInfinity_D2Larger() throws Exception {
            double result = invokeMax(Double.NEGATIVE_INFINITY, -1000.0);
            assertEquals("max(-Inf, -1000) should return -1000",
                    -1000.0, result, 0.0);
        }
     
        // =======================================================================
        // FUNCTION 16: min(double d1, double d2) — via reflection              ===
        // =======================================================================
     
        // --- Branch 1: d1 is NaN → return d2 ---
     
        @Test
        public void testMinD1NaN_ReturnsD2() throws Exception {
            double result = invokeMin(Double.NaN, 3.0);
            assertEquals("min(NaN, 3.0) should return 3.0", 3.0, result, 0.0);
        }
     
        @Test
        public void testMinD1NaN_D2NegativeValue() throws Exception {
            double result = invokeMin(Double.NaN, -7.5);
            assertEquals("min(NaN, -7.5) should return -7.5", -7.5, result, 0.0);
        }
     
        @Test
        public void testMinD1NaN_D2IsZero() throws Exception {
            double result = invokeMin(Double.NaN, 0.0);
            assertEquals("min(NaN, 0.0) should return 0.0", 0.0, result, 0.0);
        }
     
        @Test
        public void testMinD1NaN_D2IsNegativeInfinity() throws Exception {
            double result = invokeMin(Double.NaN, Double.NEGATIVE_INFINITY);
            assertEquals("min(NaN, -Inf) should return -Inf",
                    Double.NEGATIVE_INFINITY, result, 0.0);
        }
     
        @Test
        public void testMinD1NaN_D2IsNaN_ReturnD2NaN() throws Exception {
            double result = invokeMin(Double.NaN, Double.NaN);
            assertTrue("min(NaN, NaN) should return NaN (d2)", Double.isNaN(result));
        }
     
        // --- Branch 2: d1 is real, d2 is NaN → return d1 ---
     
        @Test
        public void testMinD2NaN_ReturnsD1() throws Exception {
            double result = invokeMin(3.0, Double.NaN);
            assertEquals("min(3.0, NaN) should return 3.0", 3.0, result, 0.0);
        }
     
        @Test
        public void testMinD2NaN_D1NegativeValue() throws Exception {
            double result = invokeMin(-7.5, Double.NaN);
            assertEquals("min(-7.5, NaN) should return -7.5", -7.5, result, 0.0);
        }
     
        @Test
        public void testMinD2NaN_D1IsZero() throws Exception {
            double result = invokeMin(0.0, Double.NaN);
            assertEquals("min(0.0, NaN) should return 0.0", 0.0, result, 0.0);
        }
     
        @Test
        public void testMinD2NaN_D1IsPositiveInfinity() throws Exception {
            double result = invokeMin(Double.POSITIVE_INFINITY, Double.NaN);
            assertEquals("min(+Inf, NaN) should return +Inf",
                    Double.POSITIVE_INFINITY, result, 0.0);
        }
     
        // --- Branch 3: neither NaN → return Math.min(d1, d2) ---
     
        @Test
        public void testMinBothPositive_D1Smaller() throws Exception {
            double result = invokeMin(4.0, 9.0);
            assertEquals("min(4.0, 9.0) should return 4.0", 4.0, result, 0.0);
        }
     
        @Test
        public void testMinBothPositive_D2Smaller() throws Exception {
            double result = invokeMin(9.0, 4.0);
            assertEquals("min(9.0, 4.0) should return 4.0", 4.0, result, 0.0);
        }
     
        @Test
        public void testMinEqual_ReturnsSameValue() throws Exception {
            double result = invokeMin(5.0, 5.0);
            assertEquals("min(5.0, 5.0) should return 5.0", 5.0, result, 0.0);
        }
     
        @Test
        public void testMinBothNegative_MoreNegativeReturned() throws Exception {
            double result = invokeMin(-2.0, -8.0);
            assertEquals("min(-2.0, -8.0) should return -8.0", -8.0, result, 0.0);
        }
     
        @Test
        public void testMinNegativeAndPositive() throws Exception {
            double result = invokeMin(-3.0, 3.0);
            assertEquals("min(-3.0, 3.0) should return -3.0", -3.0, result, 0.0);
        }
     
        @Test
        public void testMinZeroAndNegative() throws Exception {
            double result = invokeMin(0.0, -1.0);
            assertEquals("min(0.0, -1.0) should return -1.0", -1.0, result, 0.0);
        }
     
        @Test
        public void testMinNegativeInfinity() throws Exception {
            double result = invokeMin(Double.NEGATIVE_INFINITY, -1000.0);
            assertEquals("min(-Inf, -1000) should return -Inf",
                    Double.NEGATIVE_INFINITY, result, 0.0);
        }
     
        @Test
        public void testMinPositiveInfinity_D1Smaller() throws Exception {
            double result = invokeMin(1000.0, Double.POSITIVE_INFINITY);
            assertEquals("min(1000, +Inf) should return 1000", 1000.0, result, 0.0);
        }
     
        // =======================================================================
        // ===FUNCTION 17 : isNaNRange()                                            ===
        // =======================================================================
     
        // --- Branch 1: both lower and upper are NaN → true ---
     
        @Test
        public void testIsNaNRange_BothNaN_ReturnsTrue() {
            // The only way to construct a Range with NaN bounds without triggering
            // an IllegalArgumentException is to use NaN directly (NaN comparisons
            // are always false, so the constructor guard passes).
            Range nanRange = new Range(Double.NaN, Double.NaN);
            assertTrue("Range(NaN, NaN).isNaNRange() should return true",
                    nanRange.isNaNRange());
        }
     
        // --- Branch 2: lower is NaN but upper is real → false ---
     
        @Test
        public void testIsNaNRange_OnlyLowerNaN_ReturnsFalse() {
            Range r = new Range(Double.NaN, 5.0);
            assertFalse("Range(NaN, 5.0).isNaNRange() should return false",
                    r.isNaNRange());
        }
     
        // --- Branch 3: lower is real but upper is NaN → false ---
     
        @Test
        public void testIsNaNRange_OnlyUpperNaN_ReturnsFalse() {
            Range r = new Range(1.0, Double.NaN);
            assertFalse("Range(1.0, NaN).isNaNRange() should return false",
                    r.isNaNRange());
        }
     
        // --- Branch 4: neither bound is NaN → false ---
     
        @Test
        public void testIsNaNRange_NoNaN_ReturnsFalse() {
            Range r = new Range(1.0, 5.0);
            assertFalse("Range(1.0, 5.0).isNaNRange() should return false",
                    r.isNaNRange());
        }
     
        @Test
        public void testIsNaNRange_ZeroLengthRange_ReturnsFalse() {
            Range r = new Range(3.0, 3.0);
            assertFalse("Range(3.0, 3.0).isNaNRange() should return false",
                    r.isNaNRange());
        }
     
        @Test
        public void testIsNaNRange_NegativeRange_ReturnsFalse() {
            Range r = new Range(-5.0, -1.0);
            assertFalse("Range(-5.0, -1.0).isNaNRange() should return false",
                    r.isNaNRange());
        }
     
        @Test
        public void testIsNaNRange_InfiniteRange_ReturnsFalse() {
            Range r = new Range(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
            assertFalse("Range(-Inf, +Inf).isNaNRange() should return false",
                    r.isNaNRange());
        }
     
        @Test
        public void testIsNaNRange_LowerInfUpperNaN_ReturnsFalse() {
            // Extra boundary: infinite lower, NaN upper — only upper is NaN.
            Range r = new Range(Double.NEGATIVE_INFINITY, Double.NaN);
            assertFalse("Range(-Inf, NaN).isNaNRange() should return false",
                    r.isNaNRange());
        }
     
      
        
        @Test
        public void testCombine_NaNLowerBoundInR1_UsesR2Lower() {
            Range r1 = new Range(Double.NaN, 5.0);   // lower is NaN
            Range r2 = new Range(1.0, 8.0);
            Range combined = Range.combine(r1, r2);
            // min(NaN, 1.0) → should return 1.0 (d2)
            assertEquals("Combined lower should be 1.0 (NaN replaced by r2 lower)",
                    1.0, combined.getLowerBound(), 0.0001);
        }
     
        @Test
        public void testCombine_NaNUpperBoundInR2_UsesR1Upper() {
            Range r1 = new Range(1.0, 8.0);
            Range r2 = new Range(2.0, Double.NaN);   // upper is NaN
            Range combined = Range.combine(r1, r2);
            // max(8.0, NaN) → should return 8.0 (d1, since d2 is NaN)
            assertEquals("Combined upper should be 8.0 (NaN replaced by r1 upper)",
                    8.0, combined.getUpperBound(), 0.0001);
        }
     
        @Test
        public void testCombine_BothNaNBounds_ResultIsNaNRange() {
            Range r1 = new Range(Double.NaN, Double.NaN);
            Range r2 = new Range(Double.NaN, Double.NaN);
            Range combined = Range.combine(r1, r2);
            assertTrue("Combining two NaN ranges should yield a NaN range",
                    combined.isNaNRange());
        }
     
        @Test
        public void testExpandToInclude_NaNRange_WithRealValue() {
            // expandToInclude on a NaN range with a real value exercises
            // the min/max NaN branches and should produce a finite range.
            Range nanRange = new Range(Double.NaN, Double.NaN);
            Range expanded = Range.expandToInclude(nanRange, 4.0);
            assertFalse("Expanding a NaN range with a real value should not remain NaN",
                    expanded.isNaNRange());
        } 
        
        
        // =======================================================================
        // === FUNCTION 18: intersects(Range range)                                         ===
        // =======================================================================
        //
        // This overload simply calls intersects(range.getLowerBound(),
        // range.getUpperBound()).  Tests confirm the delegation is correct by
        // verifying both true and false returns, and all boundary-touching cases.
     
        // --- True cases (overlap exists) ---
     
        @Test
        public void testIntersectsRange_CompleteOverlap_ReturnsTrue() {
            // [2.0, 4.0] is fully inside [1.0, 5.0]
            Range other = new Range(2.0, 4.0);
            assertTrue("Fully contained range should intersect", positiveRange.intersects(other));
        }
     
        @Test
        public void testIntersectsRange_PartialOverlapFromLeft_ReturnsTrue() {
            // [0.0, 2.0] overlaps the left side of [1.0, 5.0]
            Range other = new Range(0.0, 2.0);
            assertTrue("Partial left overlap should intersect", positiveRange.intersects(other));
        }
     
        @Test
        public void testIntersectsRange_PartialOverlapFromRight_ReturnsTrue() {
            // [4.0, 6.0] overlaps the right side of [1.0, 5.0]
            Range other = new Range(4.0, 6.0);
            assertTrue("Partial right overlap should intersect", positiveRange.intersects(other));
        }
     
        @Test
        public void testIntersectsRange_EnclosingRange_ReturnsTrue() {
            // [-10.0, 10.0] completely contains [1.0, 5.0]
            Range other = new Range(-10.0, 10.0);
            assertTrue("Enclosing range should intersect", positiveRange.intersects(other));
        }
     
        @Test
        public void testIntersectsRange_IdenticalRange_ReturnsTrue() {
            // Identical range must intersect itself
            Range other = new Range(1.0, 5.0);
            assertTrue("Identical range should intersect", positiveRange.intersects(other));
        }
     
        @Test
        public void testIntersectsRange_TouchingAtLowerBound_ReturnsTrue() {
            // [0.0, 1.0] touches [1.0, 5.0] exactly at the lower boundary
            Range other = new Range(0.0, 1.0);
            assertTrue("Range touching at lower boundary should intersect",
                    positiveRange.intersects(other));
        }
     
        @Test
        public void testIntersectsRange_TouchingAtUpperBound_ReturnsTrue() {
            // [5.0, 7.0] touches [1.0, 5.0] exactly at the upper boundary
            Range other = new Range(5.0, 7.0);
            assertTrue("Range touching at upper boundary should intersect",
                    positiveRange.intersects(other));
        }
     
        @Test
        public void testIntersectsRange_ZeroLengthRangeInsideBounds_ReturnsTrue() {
            // [3.0, 3.0] is a single point inside [1.0, 5.0]
            Range other = new Range(3.0, 3.0);
            assertTrue("Zero-length range inside bounds should intersect",
                    positiveRange.intersects(other));
        }
     
        @Test
        public void testIntersectsRange_MixedAndPositive_ReturnsTrue() {
            // [-3.0, 3.0] overlaps [1.0, 5.0] in the [1.0, 3.0] segment
            assertTrue("Mixed and positive range should intersect",
                    positiveRange.intersects(mixedRange));
        }
     
        // --- False cases (no overlap) ---
     
        @Test
        public void testIntersectsRange_CompletelyToTheLeft_ReturnsFalse() {
            // [-2.0, 0.0] is entirely left of [1.0, 5.0]
            Range other = new Range(-2.0, 0.0);
            assertFalse("Range entirely to the left should not intersect",
                    positiveRange.intersects(other));
        }
     
        @Test
        public void testIntersectsRange_CompletelyToTheRight_ReturnsFalse() {
            // [6.0, 8.0] is entirely right of [1.0, 5.0]
            Range other = new Range(6.0, 8.0);
            assertFalse("Range entirely to the right should not intersect",
                    positiveRange.intersects(other));
        }
     
        @Test
        public void testIntersectsRange_NegativeRangeVsPositiveRange_ReturnsFalse() {
            // [-5.0, -1.0] and [1.0, 5.0] share no common point
            assertFalse("Negative range should not intersect positive range",
                    positiveRange.intersects(negativeRange));
        }
     
        @Test
        public void testIntersectsRange_DisjointNegativeRanges_ReturnsFalse() {
            // [-5.0, -1.0] vs [-10.0, -6.0] — no overlap
            Range other = new Range(-10.0, -6.0);
            assertFalse("Disjoint negative ranges should not intersect",
                    negativeRange.intersects(other));
        }
     
        // --- Symmetry check ---
     
        @Test
        public void testIntersectsRange_SymmetryHolds_BothDirections() {
            // intersects must be symmetric: A∩B == B∩A
            Range a = new Range(1.0, 4.0);
            Range b = new Range(3.0, 7.0);
            assertEquals("intersects() must be symmetric",
                    a.intersects(b), b.intersects(a));
        }
     
        @Test
        public void testIntersectsRange_DisjointSymmetry_BothDirections() {
            Range a = new Range(1.0, 3.0);
            Range b = new Range(5.0, 8.0);
            assertEquals("Non-intersecting ranges must be symmetric",
                    a.intersects(b), b.intersects(a));
        }
        
     
        
        

        // =========================================================
        // FUNCTION 19: shift() — zero-crossing clamp branches on negative/zero bounds
        // =========================================================
     
        @Test
        public void testShift_NegativeBaseRange_NoCrossing_ClampedAtZero() {
            // Branch: base bound is negative, delta pushes it positive → clamped to 0
            // [-3.0, -1.0] shifted by +5.0 without zero crossing:
            //   lower: -3.0 + 5.0 = 2.0 → but lower was negative, so clamped to 0.0
            //   upper: -1.0 + 5.0 = 4.0 → upper was negative, so clamped to 0.0
            Range base = new Range(-3.0, -1.0);
            Range shifted = Range.shift(base, 5.0, false);
            assertEquals("Negative lower clamped to 0.0", 0.0, shifted.getLowerBound(), 0.0001);
            assertEquals("Negative upper clamped to 0.0", 0.0, shifted.getUpperBound(), 0.0001);
        }
     
        
     
        @Test
        public void testShift_AllowZeroCrossing_NegativeBaseGoesPositive() {
            // Branch: allowZeroCrossing = true, so negative bound CAN cross zero
            // [-2.0, 1.0] shifted by +4.0, crossing allowed:
            //   lower: -2.0 + 4.0 = 2.0  (no clamping)
            //   upper:  1.0 + 4.0 = 5.0
            Range base = new Range(-2.0, 1.0);
            Range shifted = Range.shift(base, 4.0, true);
            assertEquals("Lower should cross to 2.0", 2.0, shifted.getLowerBound(), 0.0001);
            assertEquals("Upper should shift to 5.0", 5.0, shifted.getUpperBound(), 0.0001);
        }
        
        // =========================================================
        // FUNCTION 20:expand() — negative margins (inverted range) branch
        // =========================================================
     
        @Test
        public void testExpand_NegativeMargins_ShrinksRange() {
            // Negative margins contract the range inward
            // [1.0, 5.0], length = 4.0
            //   lower = 1.0 - (-0.25 * 4.0) = 1.0 + 1.0 = 2.0
            //   upper = 5.0 + (-0.25 * 4.0) = 5.0 - 1.0 = 4.0
            Range result = Range.expand(range, -0.25, -0.25);
            assertEquals("Lower should shrink to 2.0", 2.0, result.getLowerBound(), 0.0001);
            assertEquals("Upper should shrink to 4.0", 4.0, result.getUpperBound(), 0.0001);
        }
     
        @Test
        public void testExpand_LargeNegativeMargins_InvertsAndAverages() {
            // When margins are so large lower > upper, the spec collapses both
            // to their midpoint (average). 
            Range result = Range.expand(range, -1.0, -1.5);
            // Spec: when inverted, both bounds collapse to mid = (5.0 + (-1.0)) / 2 = 2.0
            assertEquals("Inverted lower should collapse to midpoint 2.0",
                    2.0, result.getLowerBound(), 0.0001);
            assertEquals("Inverted upper should collapse to midpoint 2.0",
                    2.0, result.getUpperBound(), 0.0001);
        }
        
        // =========================================================
        // FUNCTION 21: equals() — same reference short-circuit branch
        // =========================================================
     
        @Test
        public void testEquals_SameObjectReference_ReturnsTrue() {
            // Branch: obj == this → early return true (not hit by existing tests
            // which always create a second Range instance)
            assertTrue("A range must equal itself", range.equals(range));
        }
        
       
        
        // =========================================================
        // FUNCTION 22:scale(Range base, double factor)  — fully new coverage
        // =========================================================
     
       
     
        @Test
        public void testScale_ZeroFactor_CollapsesRangeToOrigin() {
            // Branch: factor == 0 (boundary of the guard — exactly not-negative)
            // [1.0, 5.0] × 0 = [0.0, 0.0]
            Range result = Range.scale(range, 0.0);
            assertEquals("Lower should collapse to 0.0", 0.0, result.getLowerBound(), 0.0001);
            assertEquals("Upper should collapse to 0.0", 0.0, result.getUpperBound(), 0.0001);
        }
     
        @Test
        public void testScale_FactorOne_ReturnsIdenticalRange() {
            // Branch: factor > 0, identity — range unchanged
            Range result = Range.scale(range, 1.0);
            assertEquals("Lower should stay 1.0", 1.0, result.getLowerBound(), 0.0001);
            assertEquals("Upper should stay 5.0", 5.0, result.getUpperBound(), 0.0001);
        }
     
        @Test
        public void testScale_FactorGreaterThanOne_PositiveRange_ScalesUp() {
            // Branch: factor > 0, positive range scaled up
            // [1.0, 5.0] × 2 = [2.0, 10.0]
            Range result = Range.scale(range, 2.0);
            assertEquals("Lower should scale to 2.0",  2.0,  result.getLowerBound(), 0.0001);
            assertEquals("Upper should scale to 10.0", 10.0, result.getUpperBound(), 0.0001);
        }
     
        @Test
        public void testScale_FractionalFactor_ScalesDown() {
            // Branch: 0 < factor < 1, positive range scaled down
            // [1.0, 5.0] × 0.5 = [0.5, 2.5]
            Range result = Range.scale(range, 0.5);
            assertEquals("Lower should scale to 0.5", 0.5, result.getLowerBound(), 0.0001);
            assertEquals("Upper should scale to 2.5", 2.5, result.getUpperBound(), 0.0001);
        }
     
        @Test
        public void testScale_FactorGreaterThanOne_NegativeRange_BoundsStayNegative() {
            // Branch: factor > 0, both bounds negative — order must be preserved
            // [-5.0, -1.0] × 3 = [-15.0, -3.0]
            Range negRange = new Range(-5.0, -1.0);
            Range result = Range.scale(negRange, 3.0);
            assertEquals("Lower should scale to -15.0", -15.0, result.getLowerBound(), 0.0001);
            assertEquals("Upper should scale to -3.0",  -3.0,  result.getUpperBound(), 0.0001);
        }
     
        @Test
        public void testScale_FactorGreaterThanOne_MixedRange() {
            // Branch: factor > 0, mixed (negative lower, positive upper)
            // [-3.0, 3.0] × 4 = [-12.0, 12.0]
            Range mixedRange = new Range(-3.0, 3.0);
            Range result = Range.scale(mixedRange, 4.0);
            assertEquals("Lower should scale to -12.0", -12.0, result.getLowerBound(), 0.0001);
            assertEquals("Upper should scale to 12.0",  12.0,  result.getUpperBound(), 0.0001);
        }
     
        @Test
        public void testScale_ZeroLengthRange_RemainsZeroLength() {
            // Zero-length range scaled must stay zero-length
            // [2.0, 2.0] × 5 = [10.0, 10.0]
            Range zeroRange = new Range(2.0, 2.0);
            Range result = Range.scale(zeroRange, 5.0);
            assertEquals("Lower should be 10.0", 10.0, result.getLowerBound(), 0.0001);
            assertEquals("Upper should be 10.0", 10.0, result.getUpperBound(), 0.0001);
            assertEquals("Length should remain 0.0", 0.0, result.getLength(), 0.0001);
        }
        
        // =========================================================
        // FUNCTION 23:CombineIgnoringNaN  — fully new coverage
        // =========================================================
        
          
        // Hits: range1 == null (true) → range2 != null (false) → return range2 (null)
        @Test
        public void testRange1NullRange2Null() {
            assertNull(Range.combineIgnoringNaN(null, null));
        }
     
        // Hits: range1 == null (true) → range2 != null (true) && isNaNRange (true) → return null
        @Test
        public void testRange1NullRange2NaN() {
            assertNull(Range.combineIgnoringNaN(null, new Range(Double.NaN, Double.NaN)));
        }
     
        // Hits: range1 == null (true) → range2 != null (true) && isNaNRange (false) → return range2
        @Test
        public void testRange1NullRange2Normal() {
            Range r2 = new Range(2.0, 6.0);
            assertEquals(r2, Range.combineIgnoringNaN(null, r2));
        }
     
        // Hits: range1 == null (false) → range2 == null (true) → isNaNRange (true) → return null
        @Test
        public void testRange1NaNRange2Null() {
            assertNull(Range.combineIgnoringNaN(new Range(Double.NaN, Double.NaN), null));
        }
        
        @Test
        public void testRange1NormalRange2Null() {
            Range r1 = new Range(1.0, 5.0);
            assertEquals(r1, Range.combineIgnoringNaN(r1, null));
        }
     
        // Hits: both non-null → min/max → isNaN(l) && isNaN(u) (true) → return null
        @Test
        public void testBothNaNRanges() {
            assertNull(Range.combineIgnoringNaN(
                    new Range(Double.NaN, Double.NaN),
                    new Range(Double.NaN, Double.NaN)));
        }
     
        // Hits: both non-null → min/max → isNaN(l) && isNaN(u) (false) → return new Range(l, u)
        @Test
        public void testBothNormalRanges() {
            Range result = Range.combineIgnoringNaN(new Range(1.0, 5.0), new Range(3.0, 8.0));
            assertNotNull(result);
            assertEquals(1.0, result.getLowerBound(), 0.0001);
            assertEquals(8.0, result.getUpperBound(), 0.0001);
        }
     
       
    }
    
    
    
