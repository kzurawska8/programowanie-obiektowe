package rpn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class RPNTest {

    private RPN calculator;

    @Before
    public void setUp() {
        calculator = new RPN();
    }

    @Test
    public void testSimpleAddition() {
        assertEquals(7, calculator.evaluate("3 4 +"));
    }

    @Test
    public void testComplexExpression() {
        assertEquals(14, calculator.evaluate("3 4 + 2 *"));
    }

    @Test
    public void testSubtraction() {
        assertEquals(1, calculator.evaluate("5 4 -"));
    }

    @Test
    public void testMultiplication() {
        assertEquals(20, calculator.evaluate("4 5 *"));
    }

    @Test
    public void testDivision() {
        assertEquals(2, calculator.evaluate("8 4 /"));
    }

    @Test
    public void testDivisionByZero() {
        try {
            calculator.evaluate("8 0 /");
            assertTrue("Division by zero did not throw exception", false);
        } catch (ArithmeticException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testInvalidOperator() {
        try {
            calculator.evaluate("3 4 &");
            assertTrue("Unknown operator did not throw exception", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testSingleNumber() {
        assertEquals(5, calculator.evaluate("5"));
    }

    @Test
    public void testMissingOperator() {
        try {
            calculator.evaluate("5 6");
            assertTrue("Missing operator did not throw exception", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testEmptyExpression() {
        try {
            calculator.evaluate("");
            assertTrue("Empty expression did not throw exception", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testExpressionWithExtraSpaces() {
        assertEquals(7, calculator.evaluate(" 3  4  + "));
    }

    @Test
    public void testLargeNumbers() {
        assertEquals(1000000000, calculator.evaluate("500000000 500000000 +"));
    }

    @Test
    public void testNegativeNumbers() {
        assertEquals(-1, calculator.evaluate("3 -4 +"));
        assertEquals(-12, calculator.evaluate("-3 -4 *"));
    }

    @Test
    public void testIncorrectOrder() {
        assertEquals(-1, calculator.evaluate("4 5 -"));
        assertEquals(0, calculator.evaluate("5 4 /"));
    }

    @Test
    public void testOnlyOperators() {
        try {
            calculator.evaluate("+ - *");
            assertTrue("Expression with only operators did not throw exception", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testDivideByZero() {
        try {
            calculator.evaluate("10 0 /");
            assertTrue("Division by zero did not throw exception", false);
        } catch (ArithmeticException e) {
            assertTrue(true);
        }
        try {
            calculator.evaluate("0 0 /");
            assertTrue("Division by zero did not throw exception", false);
        } catch (ArithmeticException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testUnknownOperator() {
        try {
            calculator.evaluate("3 4 @");
            assertTrue("Unknown operator did not throw exception", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        try {
            calculator.evaluate("3 4 #");
            assertTrue("Unknown operator did not throw exception", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testMissingOperands() {
        try {
            calculator.evaluate("+");
            assertTrue("Missing operands did not throw exception", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
}