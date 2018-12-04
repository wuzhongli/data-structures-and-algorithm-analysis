package chapter4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 代码清单 4-17 二叉查找树
 *
 * @author wzl
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    private BinaryNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    public T findMin() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return findMin(root).element;
    }

    public T findMax() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return findMax(root).element;
    }

    public void insert(T x) {
        root = insert(x, root);
    }

    public void remove(T x) {
        remove(x, root);
    }

    public void printTree() {
        if (isEmpty()) {
            System.out.println("Empty tree");
        } else {
            printTree(root);
        }
    }

    /**
     * 二分搜索树的层序遍历（广度优先遍历）
     */
    public void levelOrder() {
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.offer(root);

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

    private boolean contains(T x, BinaryNode<T> t) {
        if (t == null) {
            return false;
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            return true;
        }

    }

    private BinaryNode<T> findMin(BinaryNode<T> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        } else {
            return findMin(t.left);
        }
    }

    private BinaryNode<T> findMax(BinaryNode<T> t) {
        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }
        return t;
    }

    private BinaryNode<T> insert(T x, BinaryNode<T> t) {
        if (t == null) {
            return new BinaryNode<>(x, null, null);
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        }
        return t;
    }

    private BinaryNode<T> remove(T x, BinaryNode<T> t) {
        // Item not found; do nothing
        if (t == null) {
            return t;
        }

        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        }
        // Two children
        else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }

        return t;
    }

    private void printTree(BinaryNode<T> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    private static class BinaryNode<T> {

        BinaryNode(T element) {
            this(element, null, null);
        }

        BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.insert(num);
        }

        bst.levelOrder();
    }
}
