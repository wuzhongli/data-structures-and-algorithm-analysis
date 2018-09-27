package com.wzl.datastructuresandalgorithmanalysis.imooc.linkedlist;

/**
 * 单向链表
 *
 * @author wzl
 */
public class LinkedList<E> {

    /**
     * 虚拟头结点
     */
    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node(null, null);
    }

    /**
     * 获取链表中的元素个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 返回链表是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在链表的index(0-based)位置添加新的元素e 在链表中不是一个常用的操作，练习用：）
     */
    public void add(int index, E e) {
        rangeCheck(index);

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

//            Node node = new Node(data);
//            node.next = prev.next;
//            prev.next = node;

        prev.next = new Node(e, prev.next);
        size++;
    }

    /**
     * 在链表头添加新的元素e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在链表末尾添加新的元素e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 获得链表的第index(0-based)个位置的元素 在链表中不是一个常用的操作，练习用：）
     */
    public E get(int index) {
        rangeCheck(index);

        Node<E> current = dummyHead.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * 获得链表的第一个元素
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获得链表的最后一个元素
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 修改链表的第index(0-based)个位置的元素为e 在链表中不是一个常用的操作，练习用：）
     */
    public void set(int index, E e) {
        rangeCheck(index);

        Node<E> current = dummyHead;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = e;
    }

    /**
     * 查找链表中是否有元素e
     */
    public boolean contains(E e) {
        return indexOf(e) > -1;
    }

    public int indexOf(E e) {
        int index = 0;
        if (e == null) {
            for (Node<E> node = dummyHead.next; node != null; node = node.next) {
                if (node.data == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (Node<E> node = dummyHead.next; node != null; node = node.next) {
                if (node.equals(e)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    public E remove(int index) {
        rangeCheck(index);

        Node<E> prev = dummyHead;
        for (int i = 0; i < i; i++) {
            prev = prev.next;
        }
        Node<E> retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size--;
        return retNode.data;
    }


    private void rangeCheck(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
    }

    private class Node<E> {

        public E data;
        public Node next;

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(E data) {
            this(data, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        for (Node current = dummyHead.next; current != null; current = current.next) {
            res.append(current + "->");
        }
        res.append("NULL");

        return res.toString();
    }
}
