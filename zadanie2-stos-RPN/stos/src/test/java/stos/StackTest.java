package stos;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class StackTest {

    private Stack stack;

    @Before
    public void setUp() {
        stack = new Stack();
    }

    @Test
    public void testPushAndPop() {
        stack.push("A");
        stack.push("B");
        assertEquals("B", stack.pop());
        assertEquals("A", stack.pop());
    }

    @Test
    public void testPeek() {
        stack.push("A");
        assertEquals("A", stack.peek());
        assertEquals("A", stack.pop());
    }

    @Test(expected = IllegalStateException.class)
    public void testPopEmptyStack() {
        stack.pop();
    }

    @Test(expected = IllegalStateException.class)
    public void testPeekEmptyStack() {
        stack.peek();
    }

    @Test
    public void testStackResize() {
        for (int i = 0; i < 15; i++) {
            stack.push("Element " + i);
        }
        assertEquals("Element 14", stack.pop());
        assertEquals("Element 13", stack.pop());
    }

    @Test
    public void testPushEmptyString() {
        stack.push("");
        assertEquals("", stack.pop());
    }
    
    @Test
    public void testMixedOperations() {
        stack.push("A");
        stack.push("B");
        assertEquals("B", stack.pop());
        stack.push("C");
        assertEquals("C", stack.peek());
        assertEquals("C", stack.pop());
        assertEquals("A", stack.pop());
    }
}