package chapter4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树
 *
 * @author wzl
 */
public class BinaryTree<E> {

    /**
     * 使用后缀表达式构造表达式树
     */
    public BinaryNode<Character> buildExpressionTree(String postfixExpression) {

        Stack<BinaryNode<Character>> stack = new Stack<>();
        for (int i = 0; i < postfixExpression.length(); i++) {
            char ch = postfixExpression.charAt(i);

            // 操作符 则从栈中弹出两颗树right和left
            if (OperationSymbolEnum.isOperationSymbol(ch)) {
                BinaryNode<Character> right = stack.pop();
                BinaryNode<Character> left = stack.pop();
                BinaryNode<Character> node = new BinaryNode<>(ch, left, right);
                stack.push(node);
            }
            // 操作数 创建单节点树压入栈中
            else {
                BinaryNode<Character> node = new BinaryNode<>(ch);
                stack.push(node);
            }
        }

        return stack.pop();
    }

    /**
     * 二叉树的层序遍历（广度优先遍历）
     */
    public void levelOrder(BinaryNode<E> binaryNode) {
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.offer(binaryNode);

        while (!queue.isEmpty()) {
            BinaryNode current = queue.poll();
            System.out.println(current.element);

            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
    }

    private static class BinaryNode<E> {

        BinaryNode(E element) {
            this(element, null, null);
        }

        BinaryNode(E element, BinaryNode<E> left, BinaryNode<E> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        E element;
        BinaryNode<E> left;
        BinaryNode<E> right;
    }

    /**
     * 操作符枚举
     */
    private enum OperationSymbolEnum {
        ADD('+'), SUBTRACT('-'), MULTIPLY('*'), DIVIDE('/');

        OperationSymbolEnum(Character symbol) {
            this.symbol = symbol;
        }

        private Character symbol;

        /**
         * 判断是否为操作符
         */
        public static boolean isOperationSymbol(char symbol) {
            return ADD.symbol.equals(symbol) ||
                    SUBTRACT.symbol.equals(symbol) ||
                    MULTIPLY.symbol.equals(symbol) ||
                    DIVIDE.symbol.equals(symbol);
        }
    }

    public static void main(String[] args) {
        BinaryTree<Character> binaryTree = new BinaryTree<>();
        String s = "ab+cde+**";
        BinaryNode<Character> binaryNode = binaryTree.buildExpressionTree(s);
        binaryTree.levelOrder(binaryNode);
    }
}
