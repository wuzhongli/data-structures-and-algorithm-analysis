package stack;

/**
 * 150 逆波兰表达式求值
 *
 * @author wzl
 */
public class EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        int[] stack = new int[tokens.length];
        int top = 0;
        for (String str : tokens) {
            if ("+".equals(str) || "-".equals(str) || "*".equals(str) || "/".equals(str)) {
                int a = stack[--top];
                int b = stack[--top];
                if ("+".equals(str)) {
                    stack[top++] = a + b;
                }
                if ("-".equals(str)) {
                    stack[top++] = b - a;
                }
                if ("*".equals(str)) {
                    stack[top++] = a * b;
                }
                if ("/".equals(str)) {
                    stack[top++] = b / a;
                }
            } else {
                stack[top++] = Integer.parseInt(str);
            }
        }

        return stack[0];
    }

    public static void main(String[] args) {
        EvaluateReversePolishNotation eval = new EvaluateReversePolishNotation();
        String[] tokens = {"2", "1", "+", "3", "*"};
        String[] tokens1 = {"4", "13", "5", "/", "+"};
        String[] tokens2 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};

        System.out.println(eval.evalRPN(tokens));
        System.out.println(eval.evalRPN(tokens1));
        System.out.println(eval.evalRPN(tokens2));
    }
}
