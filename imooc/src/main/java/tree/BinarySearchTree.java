package tree;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二分搜索树
 *
 * @author wzl
 */
public class BinarySearchTree<E extends Comparable<E>> {

    private Node root;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向二分搜索树中添加新的元素e
     */
    public void add(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
        } else {
            add(root, e);
        }
    }

    /**
     * 看二分搜索树中是否包含元素e
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 二分搜索树的前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    public void preOrderNonRecursive() {
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.println(current.e);
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }

    }

    /**
     * 二分搜索树的中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 二分搜索树的后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 二分搜索树的层序遍历（广度优先遍历）
     */
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.println(current.e);

            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
    }

    /**
     * 寻找二分搜索树的最小元素
     */
    public E min() {
        if (root == null) {
            return null;
        }
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }

        return current.e;
    }

    /**
     * 返回以node为根的二分搜索树的最小值所在的节点
     */
    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    /**
     * 寻找二分搜索树的最大元素
     */
    public E max() {
        if (root == null) {
            return null;
        }
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }

        return current.e;
    }

    /**
     * 从二分搜索树中删除最小值所在节点, 返回最小值
     */
    public E removeMin() {
        E e = min();
        root = removeMin(root);
        return e;
    }

    /**
     * 删除掉以node为根的二分搜索树中的最小节点 返回删除节点后新的二分搜索树的根
     */
    private Node removeMin(Node node) {

        if (node.left == null) {
            size--;
            return node.right;
        }

        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 从二分搜索树中删除最大值所在节点
     */
    public E removeMax() {
        E e = max();
        root = removeMax(root);
        return e;
    }

    /**
     * 删除掉以node为根的二分搜索树中的最大节点 返回删除节点后新的二分搜索树的根
     */
    private Node removeMax(Node node) {
        if (node.right == null) {
            size--;
            return node.left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 从二分搜索树中删除元素为e的节点
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * 删除掉以node为根的二分搜索树中值为e的节点, 递归算法 返回删除节点后新的二分搜索树的根
     */
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else if (node.left != null && node.right != null) {
            node.e = min(node.right).e;
            node.right = removeMin(node.right);
        } else {
            size--;
            node = (node.left == null) ? node.right : node.left;
        }
        return node;
    }

    /**
     * 向以node为根的二分搜索树中插入元素e，递归算法 返回插入新节点后二分搜索树的根
     */
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    /**
     * 看以node为根的二分搜索树中是否包含元素e, 递归算法
     */
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            return contains(node.right, e);
        } else {
            return true;
        }
    }

    /**
     * 前序遍历以node为根的二分搜索树, 递归算法
     */
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历以node为根的二分搜索树, 递归算法
     */
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 后序遍历以node为根的二分搜索树, 递归算法
     */
    private void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    private class Node {

        E e;
        Node left;
        Node right;

        public Node(E e) {
            this(e, null, null);
        }

        public Node(E e, Node left, Node right) {
            this.e = e;
            this.left = left;
            this.right = right;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    /**
     * 生成以node为根节点，深度为depth的描述二叉树的字符串
     */
    private void generateBSTString(Node node, int depth, StringBuilder res) {

        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }
}
