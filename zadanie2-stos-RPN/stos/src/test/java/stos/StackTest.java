package stos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

    @Test
    public void testPopEmptyStack() {
        try {
            stack.pop();
            assertTrue("Pop from empty stack did not throw exception", false);
        } catch (IllegalStateException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testPeekEmptyStack() {
        try {
            stack.peek();
            assertTrue("Peek from empty stack did not throw exception", false);
        } catch (IllegalStateException e) {
            assertTrue(true);
        }
    }
}