package chapter3;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * 表达式转换
 *
 * @author wzl
 */
public class ExpressionConversion {

    private static Map<Character, Integer> map = new HashMap<>();

    private static final Pattern PATTERN = Pattern.compile("^-?[0-9]+$");

    static {
        map.put('+', 0);
        map.put('-', 0);
        map.put('*', 1);
        map.put('/', 1);
        map.put('(', 2);
    }

    /**
     * 由中缀表达式构造后缀表达式
     */
    public String buildPostExpression(String infixExpression) {
        // 操作符栈， '('也存入栈中
        Stack<Character> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < infixExpression.length(); i++) {
            char ch = infixExpression.charAt(i);

            // 如果读取到')' 则从栈中弹出
            if (ch == ')') {
                while (true) {
                    char out = stack.pop();
                    if (out == '(') {
                        break;
                    }
                    builder.append(out);
                }
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(') {
                if (stack.isEmpty()) {
                    stack.push(ch);
                    continue;
                }
                int priority = map.get(ch);
                int top = map.get(stack.peek());
                if (priority > top) {
                    stack.push(ch);
                } else {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        char c = stack.pop();
                        if (map.get(c) >= priority) {
                            builder.append(c);
                        }
                    }
                    stack.push(ch);
                }
            } else {
                builder.append(ch);
            }
        }
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        return builder.toString();
    }


    public static void main(String[] args) {
        String infix = "a+b*c+(d*e+f)*g";
        ExpressionConversion conversion = new ExpressionConversion();
//        System.out.println(conversion.buildPostExpression(infix));
//        System.out.println(conversion.buildPostExpression("a-b-c"));

        String[] tokens = {"2", "1", "+", "3", "*"};
        String[] tokens1 = {"4", "13", "5", "/", "+"};
        String[] tokens2 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(conversion.evalRPN(tokens));
        System.out.println(conversion.evalRPN(tokens1));
        System.out.println(conversion.evalRPN(tokens2));
    }

    public int evalRPN(String[] tokens) {
        // 操作数栈
        Stack<Integer> stack = new Stack<>();
        int left, right;
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            // 操作数入栈
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            }
            // 操作符
            else if (isOperationSymbol(token)) {
                right = stack.pop();
                left = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(left + right);
                        break;
                    case "-":
                        stack.push(left - right);
                        break;
                    case "*":
                        stack.push(left * right);
                        break;
                    case "/":
                        stack.push(left / right);
                        break;
                    default:
                        break;
                }
            }
            // 后缀表达式
            else {
                stack.push(evalRPN(token));
            }
        }
        return stack.pop();
    }

    private int evalRPN(String token) {
        return 0;
    }

    private static boolean isNumber(String str) {
        return PATTERN.matcher(str).matches();
    }

    private static boolean isOperationSymbol(String str) {
        return "+".equals(str) || "-".equals(str) || "*".equals(str) || "/".equals(str);
    }
}
