package chapter4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * AVL树
 *
 * @author wzl
 */
public class AvlTree<E extends Comparable<? super E>> {

    private AvlNode<E> root;

    public void insert(E x) {
        System.out.println("插入节点：" + x);
        root = insert(x, root);
    }

    private AvlNode<E> insert(E x, AvlNode<E> t) {
        if (t == null) {
            return new AvlNode<>(x, null, null);
        }

        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        }

        return balance(t);
    }

    /**
     * 允许的不平衡
     */
    private static final int ALLOWED_IMBALANCE = 1;

    private AvlNode<E> balance(AvlNode<E> t) {
        if (t == null) {
            return t;
        }
        if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE) {
            if (height(t.left.left) >= height(t.left.right)) {
                return rotateWithLeftChild(t);
            } else {
                return doubleWithLeftChild(t);
            }
        } else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE) {
            if (height(t.right.right) >= height(t.right.left)) {
                return rotateWithRightChild(t);
            } else {
                return doubleWithRightChild(t);
            }
        }

        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    /**
     * 用左子树旋转二叉树节点。对于AVL树，这是案例1（左-左）的单旋转。更新高度，然后返回新的根节点。
     */
    private AvlNode<E> rotateWithLeftChild(AvlNode<E> avlNode) {
        System.out.println("节点：" + avlNode.element + "左左单旋转");
        AvlNode<E> root = avlNode.left;
        avlNode.left = root.right;
        root.right = avlNode;
        avlNode.height = Math.max(height(avlNode.left), height(avlNode.right)) + 1;
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        return root;
    }

    /**
     * 用右子树旋转二叉树节点。对于AVL树，这是案例4（右-右）的单旋转。更新高度，然后返回新的根节点。
     */
    private AvlNode<E> rotateWithRightChild(AvlNode<E> avlNode) {
        System.out.println("节点：" + avlNode.element + "右右单旋转");
        AvlNode<E> root = avlNode.right;
        avlNode.right = root.left;
        root.left = avlNode;
        avlNode.height = Math.max(height(avlNode.left), height(avlNode.right)) + 1;
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        return root;
    }

    /**
     * 双旋转二叉树节点：先对左子树进行（右-右）单旋转; 然后再整体进行（左-左）单旋转。 对于AVL树，这是案例2（左-右）的双旋转。 更新高度，然后返回新根。
     */
    private AvlNode<E> doubleWithLeftChild(AvlNode<E> avlNode) {
        System.out.println("节点：" + avlNode.element + "左右双旋转");
        avlNode.left = rotateWithRightChild(avlNode.left);
        return rotateWithLeftChild(avlNode);
    }

    /**
     * 双旋转二叉树节点：先对右子树进行（左-左）单旋转; 然后再整体进行（右-右）单旋转。 对于AVL树，这是案例3（右-左）的双旋转。 更新高度，然后返回新根。
     */
    private AvlNode<E> doubleWithRightChild(AvlNode<E> avlNode) {
        System.out.println("节点：" + avlNode.element + "右左双旋转");
        avlNode.right = rotateWithLeftChild(avlNode.right);
        return rotateWithRightChild(avlNode);
    }

    /**
     * 返回节点的高度，如果节点为{@code null} 则返回-1
     */
    private int height(AvlNode<E> t) {
        return t == null ? -1 : t.height;
    }

    /**
     * 二叉树的层序遍历（广度优先遍历）
     */
    public void levelOrder() {
        Queue<AvlNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            AvlNode current = queue.poll();
            System.out.println(current.element);

            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
    }

    private static class AvlNode<E> {

        AvlNode(E element) {
            this(element, null, null);
        }

        public AvlNode(E element, AvlNode<E> left, AvlNode<E> right) {
            this.element = element;
            this.left = left;
            this.right = right;
            height = 0;
        }

        /**
         * 节点数据
         */
        E element;
        /**
         * 左孩子
         */
        AvlNode<E> left;
        /**
         * 右孩子
         */
        AvlNode<E> right;
        /**
         * 节点的高度
         */
        int height;
    }

    public static void main(String[] args) {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(3);
        tree.insert(2);
        tree.levelOrder();
        System.out.println("----------");
        tree.insert(1);
        tree.levelOrder();
        System.out.println("----------");
        tree.insert(4);
        tree.levelOrder();
        System.out.println("----------");
        tree.insert(5);
        tree.levelOrder();
        System.out.println("----------");
        tree.insert(6);
        tree.levelOrder();
        System.out.println("----------");
        tree.insert(7);
        tree.levelOrder();
        System.out.println("----------");
        tree.insert(16);
        tree.levelOrder();
        System.out.println("----------");
        tree.insert(15);
        tree.levelOrder();
    }
}
