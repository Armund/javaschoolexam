package com.tsystems.javaschool.tasks.calculator;


import java.util.*;

public class Calculator {
    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    private Stack<Double> Numbers = new Stack<>();

    public String evaluate(String statement) {
        // TODO: Implement the logic here

        String resultString;
        try {
            String statementModified = reversePolish(format(statement));

            String[] tokens = statementModified.split(" ");
            for (String str : tokens) {
                switch (str) {
                    case "*":
                        Numbers.push(Numbers.pop() * Numbers.pop());
                        break;
                    case "+":
                        Numbers.push(Numbers.pop() + Numbers.pop());
                        break;
                    case "-":
                        Numbers.push(-Numbers.pop() + Numbers.pop());
                        break;
                    case "/":
                        double a = Numbers.pop(), b = Numbers.pop();
                        Numbers.push(b / a);
                        break;
                    default:
                        Numbers.push(Double.parseDouble(str));
                        break;
                }
            }

            Double resultDouble = Numbers.pop();
            if (resultDouble.isInfinite() || !Numbers.isEmpty()) {
                return null;
            }
            resultString = resultDouble.toString();
            if (resultDouble % 1 == 0) resultString = resultString.substring(0, resultString.length()-2);

        } catch (Exception e) {
            return null;
        }
        return resultString;
    }

    private final Map<Character, Integer> operationPriority = new HashMap<>();

    public Calculator() {
        operationPriority.put('(', 0);
        operationPriority.put('+', 1);
        operationPriority.put('-', 1);
        operationPriority.put('*', 2);
        operationPriority.put('/', 2);
    }

    private String reversePolish (String statement) {
        String result = "";
        Stack<Character> stack = new Stack<>();
        String[] tokens = statement.split(" ");

        for (String token : tokens) {
            if (isNumber(token)) {
                result += token + " ";
            } else {
                Character ch = token.charAt(0);
                if (isOperationSign(ch)) {
                    boolean pushed = false;
                    while (!pushed) {
                        if (stack.isEmpty() || operationPriority.get(stack.peek()) < operationPriority.get(ch)) {
                            stack.push(ch);
                            pushed = true;
                        } else {
                            result += stack.pop() + " ";
                        }
                    }
                } else if (ch.equals('(')) {
                    stack.push(ch);
                } else if (ch.equals(')')) {
                    Character character = stack.pop();
                    while (!character.equals('(')) {
                        result += character + " ";
                        character = stack.pop();
                    }
                }
            }
        }

        for (int i = stack.size(); i > 0; i--) {
            result += stack.pop() + " ";
        }

        return result.substring(0,result.length()-1);
    }

    private boolean isNumber(String str) {
        return Character.isDigit(str.charAt(0));
    }

    private boolean isOperationSign (Character ch) {
        String value = Character.toString(ch);
        return "+-*/".contains(value);
    }

    private boolean isLegit (Character ch) {
        String value = Character.toString(ch);
        return "+-*/()".contains(value);
    }

    private String format(String str) {
        String result = "";
        Character currentChar;
        for (int i = 0; i < str.length(); i++) {
            currentChar = str.charAt(i);
            if (!Character.isSpaceChar(currentChar)) {
                if (Character.isDigit(currentChar)) {
                    result += currentChar;
                    if (i != str.length() - 1) {
                        if (!Character.isDigit(str.charAt(i+1)) && str.charAt(i+1) != '.') {
                            result += " ";
                        }
                    }
                } else if (currentChar.equals('.')) {
                    result += currentChar;
                } else if (isLegit(currentChar)) {
                    result += currentChar + " ";
                } else {
                    return null;
                }
            }
        }

        return result.charAt(result.length()-1) == ' ' ? result.substring(0, result.length()-1) : result;
    }
}