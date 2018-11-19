package stack;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。 左括号必须以正确的顺序闭合。 注意空字符串可被认为是有效字符串。
 *
 * 示例 1: 输入: "()" 输出: true
 *
 * 示例 2: 输入: "()[]{}" 输出: true
 *
 * 示例 3: 输入: "(]" 输出: false
 *
 * 示例 4: 输入: "([)]" 输出: false
 *
 * 示例 5: 输入: "{[]}" 输出: true
 *
 * @author wzl
 */
public class ValidParentheses {

    /**
     * 括号匹配算法：初始化一个空栈。读入字符直到文件结尾。如果字符是一个开放符号，则将其推入栈中。如果字符是一个封闭符号，则当栈空时报错。否则，将栈顶元素弹出。如果弹出的符号不是对应的开放符号，
     * 则报错。在文件结尾，如果栈非空则报错。
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (c == ')' && top != '(') {
                    return false;
                }
                if (c == ']' && top != '[') {
                    return false;
                }
                if (c == '}' && top != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(ValidParentheses.isValid("()[]{}"));
        System.out.println(ValidParentheses.isValid("([)]"));
    }
}
