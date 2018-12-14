package chapter5;

import java.util.List;

/**
 * 分离链接法实现的哈希表
 *
 * @author wzl
 */
public class SeparateChainingHashTable<E> {

    public SeparateChainingHashTable() {

    }

    public SeparateChainingHashTable(int size) {

    }

    public void insert(E e) {

    }

    public void remove(E e) {

    }

    public boolean contains(E e) {
        return false;
    }

    public void makeEmpty() {

    }

    private static final int DEFAULT_TABLE_SIZE = 101;

    private List<E>[] theLists;
    private int currentSize;

    private void rehash() {

    }

    private int myHash(E e) {
        int hash = e.hashCode();
        hash %= theLists.length;
        if (hash < 0) {
            hash += theLists.length;
        }
        return hash;
    }


}
