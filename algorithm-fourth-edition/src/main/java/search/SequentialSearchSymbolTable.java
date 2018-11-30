package search;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayDeque;

/**
 * 顺序查找符号表
 *
 * @author wzl
 */
public class SequentialSearchSymbolTable<Key, Value> {

    /**
     * 链表头结点
     */
    private Node first;

    /**
     * 返回与此符号表中指定的键关联的值。
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                // 命中
                return x.value;
            }
        }
        // 未命中
        return null;
    }

    /**
     * 将指定的键值对插入符号表中，如果符号表已包含指定的键，则使用新值覆盖旧的 如果指定的键为{@code null}，则从符号表中删除指定的键（及其关联的值）
     */
    public void put(Key key, Value value) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                // 命中，更新
                x.value = value;
                return;
            }
        }
        // 未命中，新建结点（头插法）
        first = new Node(key, value, first);
    }

    /**
     * 从符号表中删除指定的键（及其关联的值）
     */
    public void delete(Key key) {
        Node dummyHead = new Node(null, null, first);

        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.key == key) {
                prev.next = prev.next.next;
                break;
            } else {
                prev = prev.next;
            }
        }
    }

    public Iterable<Key> keys() {
        ArrayDeque<Key> queue = new ArrayDeque<>();
        for (Node x = first; x != null; x = x.next) {
            queue.offerLast(x.key);
        }
        return queue;
    }

    /**
     * 链表节点
     */
    private class Node {

        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

}
