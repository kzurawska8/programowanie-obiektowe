package rpn;

import stos.Stack;

public class RPN {
    public int evaluate(String expression) {
        Stack stack = new Stack();
        String[] tokens = expression.split(" ");
        
        for (String token : tokens) {
            if (isOperator(token)) {
                int b = Integer.parseInt(stack.pop());
                int a = Integer.parseInt(stack.pop());
                int result = applyOperator(a, b, token);
                stack.push(String.valueOf(result));
            } else {
                stack.push(token);
            }
        }
        
        return Integer.parseInt(stack.pop());
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private int applyOperator(int a, int b, String operator) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": 
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
            default: throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}