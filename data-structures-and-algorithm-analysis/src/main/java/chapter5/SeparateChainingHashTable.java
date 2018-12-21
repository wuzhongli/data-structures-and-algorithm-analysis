package chapter5;

import java.util.LinkedList;
import java.util.List;

/**
 * 分离链接法实现的哈希表
 *
 * @author wzl
 */
public class SeparateChainingHashTable<E> {

    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size) {
        theLists = new LinkedList[nextPrime(size)];
        for (int i = 0; i < theLists.length; i++) {
            theLists[i] = new LinkedList<>();
        }
    }

    public void insert(E e) {
        List<E> list = theLists[myHash(e)];
        if (!list.contains(e)) {
            list.add(e);

            if (++currentSize > theLists.length) {
                // Rehash; see Section 5.5
                rehash();
            }
        }
    }

    public void remove(E e) {
        List<E> list = theLists[myHash(e)];
        if (list.contains(e)) {
            list.remove(e);
            currentSize--;
        }
    }

    public boolean contains(E e) {
        List<E> list = theLists[myHash(e)];
        return list.contains(e);
    }

    /**
     * 使哈希表在逻辑上为空
     */
    public void makeEmpty() {
        for (int i = 0; i < theLists.length; i++) {
            theLists[i].clear();
        }
        currentSize = 0;
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

    private static int nextPrime(int n) {
        if (n % 2 == 0) {
            n++;
        }

        for (; !isPrime(n); n += 2) {
        }

        return n;
    }

    private static boolean isPrime(int n) {
        if (n == 2 || n == 3) {
            return true;
        }

        if (n == 1 || n % 2 == 0) {
            return false;
        }

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }


}
