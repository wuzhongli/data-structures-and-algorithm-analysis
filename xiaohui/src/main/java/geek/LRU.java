package geek;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRU<K, V> extends LinkedHashMap<K, V> implements Map<K, V> {

    private static int DEFAULT_CAPACITY = 5;
    private int capacity;


    public LRU(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor, true);
        this.capacity = DEFAULT_CAPACITY;
    }

    public LRU(int initialCapacity, float loadFactor, int capacity) {
        super(initialCapacity, loadFactor, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Entry eldest) {
        if (size() > capacity) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

        LRU<Character, Integer> lru = new LRU<Character, Integer>(
                16, 0.75f);

        String s = "abcdefghijkl";
        for (int i = 0; i < s.length(); i++) {
            lru.put(s.charAt(i), i);
        }
        System.out.println("LRU中key为h的Entry的值为： " + lru.get('h'));
        System.out.println("LRU的大小 ：" + lru.size());
        System.out.println("LRU ：" + lru);
    }
}
