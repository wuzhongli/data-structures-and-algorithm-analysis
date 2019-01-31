package chapter5;

import java.util.Random;

/**
 * 布谷鸟散列表
 *
 * @author wzl
 */
public class CuckooHashTable<T> {

    public CuckooHashTable(HashFamily<T> hashFamily) {
        this(hashFamily, DEFAULT_TABLE_SIZE);
    }

    public CuckooHashTable(HashFamily<T> hashFamily, int size) {
        allocateArray(nextPrime(size));
        doClear();
        hashFunctions = hashFamily;
        numHashFunctions = hashFamily.getNumberOfFunctions();
    }

    public void makeEmpty() {
        doClear();
    }

    public boolean contains(T x) {
        return findPos(x) != -1;
    }

    /**
     * 使用指定的哈希函数计算x的哈希码。
     *
     * @param x 要计算的项。
     * @param which 哈希函数。
     * @return 哈希码。
     */
    private int myHash(T x, int which) {
        int hash = hashFunctions.hash(x, which);
        hash %= array.length;

        if (hash < 0) {
            hash += array.length;
        }
        return hash;
    }

    /**
     * 搜索所有哈希函数位置的方法。
     *
     * @param x 要搜索的项。
     * @return 搜索终止的位置，如果未找到，则返回-1。
     */
    private int findPos(T x) {
        for (int i = 0; i < numHashFunctions; i++) {
            int pos = myHash(x, i);
            if (array[pos] != null && array[pos].equals(x)) {
                return pos;
            }
        }
        return -1;
    }

    /**
     * 从哈希表中删除元素
     *
     * @param x 要删除的元素
     * @return 如果找到并删除元素返回true，否则返回false
     */
    public boolean remove(T x) {
        int pos = findPos(x);
        if (pos == -1) {
            return false;
        }

        array[pos] = null;
        currentSize--;
        return true;
    }

    /**
     * 在哈希表中插入元素。如果元素已存在，返回false。
     *
     * @param x 要插入的元素
     */
    public boolean insert(T x) {
        if (contains(x)) {
            return false;
        }
        if (currentSize >= array.length * MAX_LOAD) {
            expand();
        }
        return insertHelper1(x);
    }

    private void expand() {
        rehash((int) (array.length / MAX_LOAD));
    }

    private void rehash() {
        hashFunctions.generateNewFunctions();
        rehash(array.length);
    }

    private void rehash(int newLength) {
        T[] oldArray = array;
        allocateArray(nextPrime(newLength));
        currentSize = 0;

        // 复制原表
        for (T t : oldArray) {
            if (t != null) {
                insert(t);
            }
        }
    }

    private void doClear() {
        currentSize = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    private void allocateArray(int arraySize) {
        array = (T[]) new Object[arraySize];
    }

    /**
     * 找到大于等于n的最小素数的内部方法。
     *
     * @param n 起始数字(必须是正数).
     * @return 一个大于等于n的素数.
     */
    protected static int nextPrime(int n) {
        if (n % 2 == 0) {
            n++;
        }

        for (; !isPrime(n); n += 2) {
        }

        return n;
    }

    /**
     * 测试数字是否为素数的内部方法。这并不是一个有效的算法。
     *
     * @param n 要测试的数字.
     * @return 测试结果.
     */
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

    private static final double MAX_LOAD = 0.4;
    private static final int ALLOWED_REHASHES = 1;
    private static final int DEFAULT_TABLE_SIZE = 101;

    private final HashFamily<? super T> hashFunctions;
    private final int numHashFunctions;
    private T[] array;
    private int currentSize;

    private int rehashes = 0;
    private Random r = new Random();

    private boolean insertHelper1(T x) {
        final int COUNT_LIMIT = 100;

        while (true) {
            int lastPos = -1;
            int pos;

            for (int count = 0; count < COUNT_LIMIT; count++) {
                for (int i = 0; i < numHashFunctions; i++) {
                    pos = myHash(x, i);

                    if (array[pos] == null) {
                        array[pos] = x;
                        currentSize++;
                        return true;
                    }
                }

                // 没有一个地方可用，随机踢出一个
                int i = 0;
                do {
                    pos = myHash(x, r.nextInt(numHashFunctions));
                } while (pos == lastPos && i++ < 5);

                T tmp = array[lastPos = pos];
                array[pos] = x;
                x = tmp;
            }

            if (++rehashes > ALLOWED_REHASHES) {
                // 扩容数组
                expand();
                rehashes = 0;
            } else {
                rehash();
            }
        }
    }
}
