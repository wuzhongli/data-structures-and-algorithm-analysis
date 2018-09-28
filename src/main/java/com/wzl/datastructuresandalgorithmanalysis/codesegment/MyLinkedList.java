package com.wzl.datastructuresandalgorithmanalysis.codesegment;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Iterable<E> {

    private int size;
    private int modCount;
    private Node<E> head;
    private Node<E> tail;

    public MyLinkedList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean add(E e) {
        add(size, e);
        return true;
    }

    public void add(int index, E e) {
        addBefore(getNode(index), e);
    }

    public E get(int index) {
        return getNode(index).data;
    }

    public E set(int index, E newData) {
        Node<E> p = getNode(index);
        E oldData = p.data;
        p.data = newData;
        return oldData;
    }

    private E remove(int index) {
        return remove(getNode(index));
    }

    private E remove(Node<E> e) {
        e.next.prev = e.prev;
        e.prev.next = e.next;
        size--;
        modCount--;
        return e.data;
    }

    private void addBefore(Node<E> node, E e) {
        Node<E> newNode = new Node<>(e, node.prev, node);
        node.prev.next = newNode;
        node.prev = newNode;
        size++;
        modCount++;
    }

    private Node<E> getNode(int index) {
        Node<E> node;
        if (index <= 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index < size >> 1) {
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = tail;
            for (int i = size; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }


    @Override
    public Iterator<E> iterator() {
        return null;
    }

    private class LinkedListIterator implements Iterator<E> {

        private Node<E> current = head.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != tail;
        }

        @Override
        public E next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E next = current.data;
            current = current.next;
            okToRemove = true;
            return next;
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }

            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }

    }

    private void doClear() {
        head = new Node<>(null, null, null);
        tail = new Node<>(null, head, null);
        head.next = tail;
        size = 0;
        modCount++;
    }

    private static class Node<E> {

        public Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        public E data;
        public Node<E> prev;
        public Node<E> next;
    }
}
