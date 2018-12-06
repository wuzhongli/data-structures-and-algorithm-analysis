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
     * 使用数组构建二叉树 例如 [3, 9, 20, -1, -1, 15, 7]
     */
    public static BinaryNode<Integer> buildBinaryTreeByArray(int[] arr, int index, int n) {
        BinaryNode<Integer> binaryNode = null;
        if (index < n && arr[index] != -1) {
            binaryNode = new BinaryNode<>(arr[index], null, null);
            binaryNode.left = buildBinaryTreeByArray(arr, 2 * index + 1, n);
            binaryNode.right = buildBinaryTreeByArray(arr, 2 * index + 2, n);
        }
        return binaryNode;
    }

    /**
     * 使用字符串构建二叉树 例如"[1,2,2,3,3,null,null,4,4]"
     */
    public static BinaryNode stringToBinaryNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        BinaryNode root = new BinaryNode(Integer.parseInt(item));
        Queue<BinaryNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            BinaryNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new BinaryNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new BinaryNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

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
