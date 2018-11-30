package search;

/**
 * 符号表
 *
 * @author wzl
 */
public class SymbolTable<Key extends Comparable<Key>, Value> {

    /**
     * 创建一张符号表
     */
    public SymbolTable() {

    }

    /**
     * 将键值对存入表中（若值为空则将键从表中删除）
     */
    public void put(Key key, Value value) {

    }

    /**
     * 获取键key对应的值（若键key不存在则返回null）
     */
    public Value get(Key key) {
        return null;
    }

    /**
     * 从表中删去键key（及其对应的值）
     */
    public void delete(Key key) {
        put(key, null);
    }

    /**
     * 键key在表中是否有对应的值
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     * 表是否为空
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 表中键值对的数量
     */
    public int size() {
        return 0;
    }

    /**
     * 最小的键
     */
    public Key min() {
        return null;
    }

    /**
     * 最大的键
     */
    public Key max() {
        return null;
    }

    /**
     * 小于等于key的最大键
     */
    public Key floor(Key key) {
        return null;
    }

    /**
     * 大于等于key的最小键
     */
    public Key ceiling(Key key) {
        return null;
    }

    /**
     * 小于key的键的数量
     */
    public int rank(Key key) {
        return 0;
    }

    /**
     * 排名为k的键
     */
    public Key select(int k) {
        return null;
    }

    /**
     * 删除最小的键
     */
    public void deleteMin() {
    }

    /**
     * 删除最大的键
     */
    public void deleteMax() {
    }

    /**
     * [lo..hi]之间键的数量
     */
    public int size(Key lo, Key hi) {
        return 0;
    }

    /**
     * [lo..hi]之间的所有键，已排序
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    /**
     * 表中的所有键的集合，已排序
     */
    public Iterable<Key> keys() {
        return null;
    }

}
