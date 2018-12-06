package chapter3;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 表达式转换
 *
 * @author wzl
 */
public class ExpressionConversion {

    private static Map<Character, Integer> map = new HashMap<>(8);

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
        System.out.println(conversion.buildPostExpression(infix));
        System.out.println(conversion.buildPostExpression("a-b-c"));
    }

}
