package chapter4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 代码清单 4-17 二叉查找树
 *
 * @author wzl
 */
public class BinarySearchTree<E extends Comparable<? super E>> {

    private BinaryNode<E> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(E e) {
        return contains(e, root);
    }

    public E findMin() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return findMin(root).element;
    }

    public E findMax() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return findMax(root).element;
    }

    public void insert(E e) {
        root = insert(e, root);
    }

    public void remove(E e) {
        remove(e, root);
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
            BinaryNode binaryNode = queue.poll();
            System.out.println(binaryNode.element);

            if (binaryNode.left != null) {
                queue.offer(binaryNode.left);
            }
            if (binaryNode.right != null) {
                queue.offer(binaryNode.right);
            }
        }
    }

    private boolean contains(E e, BinaryNode<E> binaryNode) {
        if (binaryNode == null) {
            return false;
        }
        int compareResult = e.compareTo(binaryNode.element);
        if (compareResult < 0) {
            return contains(e, binaryNode.left);
        } else if (compareResult > 0) {
            return contains(e, binaryNode.right);
        } else {
            return true;
        }

    }

    private BinaryNode<E> findMin(BinaryNode<E> binaryNode) {
        if (binaryNode == null) {
            return null;
        } else if (binaryNode.left == null) {
            return binaryNode;
        } else {
            return findMin(binaryNode.left);
        }
    }

    private BinaryNode<E> findMax(BinaryNode<E> binaryNode) {
        if (binaryNode != null) {
            while (binaryNode.right != null) {
                binaryNode = binaryNode.right;
            }
        }
        return binaryNode;
    }

    private BinaryNode<E> insert(E e, BinaryNode<E> binaryNode) {
        if (binaryNode == null) {
            return new BinaryNode<>(e, null, null);
        }
        int compareResult = e.compareTo(binaryNode.element);
        if (compareResult < 0) {
            binaryNode.left = insert(e, binaryNode.left);
        } else if (compareResult > 0) {
            binaryNode.right = insert(e, binaryNode.right);
        }
        return binaryNode;
    }

    private BinaryNode<E> remove(E e, BinaryNode<E> binaryNode) {
        if (binaryNode == null) {
            return binaryNode;
        }

        int compareResult = e.compareTo(binaryNode.element);
        if (compareResult < 0) {
            binaryNode.left = remove(e, binaryNode.left);
        } else if (compareResult > 0) {
            binaryNode.right = remove(e, binaryNode.right);
        }
        else if (binaryNode.left != null && binaryNode.right != null) {
            binaryNode.element = findMin(binaryNode.right).element;
            binaryNode.right = remove(binaryNode.element, binaryNode.right);
        } else {
            binaryNode = (binaryNode.left != null) ? binaryNode.left : binaryNode.right;
        }

        return binaryNode;
    }

    private void printTree(BinaryNode<E> binaryNode) {
        if (binaryNode != null) {
            printTree(binaryNode.left);
            System.out.println(binaryNode.element);
            printTree(binaryNode.right);
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

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.insert(num);
        }

        bst.levelOrder();
    }
}
