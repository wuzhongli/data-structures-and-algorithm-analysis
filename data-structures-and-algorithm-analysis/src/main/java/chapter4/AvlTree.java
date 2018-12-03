package chapter4;

/**
 * AVL树
 *
 * @author wzl
 */
public class AvlTree<E extends Comparable<? super E>> {


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

        } else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE) {

        }

        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    /**
     * 返回节点的高度，如果节点为{@code null} 则返回-1
     */
    private int height(AvlNode<E> t) {
        return t == null ? -1 : t.height;
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
}
