package set;

import tree.BinarySearchTree;

/**
 * 二叉查找树集合
 *
 * @author wzl
 */
public class BinarySearchTreeSet<E extends Comparable<E>> implements Set<E> {

    private BinarySearchTree<E> binarySearchTree;

    public BinarySearchTreeSet() {
        this.binarySearchTree = new BinarySearchTree<>();
    }

    @Override
    public void add(E e) {
        binarySearchTree.add(e);
    }

    @Override
    public boolean contains(E e) {
        return binarySearchTree.contains(e);
    }

    @Override
    public void remove(E e) {
        binarySearchTree.remove(e);
    }

    @Override
    public int getSize() {
        return binarySearchTree.size();
    }

    @Override
    public boolean isEmpty() {
        return binarySearchTree.isEmpty();
    }
}
