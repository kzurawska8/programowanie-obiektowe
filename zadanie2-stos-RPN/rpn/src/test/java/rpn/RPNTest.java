package rpn;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class RPNTest {

    private RPN calculator;

    @Before
    public void setUp() {
        calculator = new RPN();
    }

    @Test
    public void testAdd() {
        assertEquals(7, calculator.evaluate("3 4 +"));
    }

    @Test
    public void testAddMult() {
        assertEquals(14, calculator.evaluate("3 4 + 2 *"));
    }

    @Test
    public void testSub() {
        assertEquals(1, calculator.evaluate("5 4 -"));
    }

    @Test
    public void testMult() {
        assertEquals(20, calculator.evaluate("4 5 *"));
    }

    @Test
    public void testDiv() {
        assertEquals(2, calculator.evaluate("8 4 /"));
    }

    @Test(expected = ArithmeticException.class)
    public void testDivByZero() {
        calculator.evaluate("8 0 /");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidOperator() {
        calculator.evaluate("3 4 &");
    }

    @Test
    public void testSingleNumber() {
        assertEquals(5, calculator.evaluate("5"));
    }

    @Test
    public void testExtraSpaces() {
        assertEquals(7, calculator.evaluate(" 3  4  + "));
    }

    @Test
    public void testLargeNumbers() {
        assertEquals(1000000000, calculator.evaluate("500000000 500000000 +"));
    }

    @Test
    public void testNegativeNumbers() {
        assertEquals(-1, calculator.evaluate("3 -4 +"));
        assertEquals(12, calculator.evaluate("-3 -4 *"));
    }

    @Test
    public void testIncorrectOrder() {
        assertEquals(-1, calculator.evaluate("4 5 -"));
        assertEquals(1, calculator.evaluate("5 4 -"));
    }
}