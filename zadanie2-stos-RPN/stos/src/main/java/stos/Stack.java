package stos;

public class Stack {
    private String[] elements;
    private int size;

    public Stack() {
        elements = new String[10];
        size = 0;
    }

    public void push(String element) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = element;
    }

    public String pop() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        return elements[--size];
    }

    public String peek() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        return elements[size - 1];
    }

    private void resize() {
        String[] newArray = new String[elements.length * 2];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }
}