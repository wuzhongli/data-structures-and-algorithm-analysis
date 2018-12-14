import java.util.HashMap;
import java.util.Map;

/**
 * LRU 缓存淘汰算法
 *
 * @author wzl
 */
public class LruCache {

    private Node head;
    private Node tail;

    /**
     * 缓存存储上限
     */
    private int limit;

    private Map<String, Node> map;

    public LruCache(int limit) {
        this.limit = limit;
        map = new HashMap<>(limit);
    }

    public String get(String key) {
        Node node = map.get(key);
        if (node == null) {
            return null;
        }
        refreshNode(node);
        return node.value;
    }

    public void put(String key, String value) {
        Node node = map.get(key);
        if (node == null) {
            // 如果key不存在，插入key-value
            if (map.size() >= limit) {
                String oldKey = removeNode(head);
                map.remove(oldKey);
            }
            node = new Node(key, value);
            addNode(node);
            map.put(key, node);
        } else {
            // 如果key存在，刷新key-value
            node.value = value;
            refreshNode(node);
        }
    }

    public void remove(String key) {
        Node node = map.get(key);
        removeNode(node);
        map.remove(key);
    }

    /**
     * 刷新被访问的节点位置
     *
     * @param node 被访问的节点
     */
    private void refreshNode(Node node) {
        // 如果访问的是尾节点，无需移动节点
        if (node == tail) {
            return;
        }
        // 移除节点
        removeNode(node);
        // 重新插入节点
        addNode(node);
    }

    /**
     * 删除节点
     *
     * @param node 要删除的节点
     */
    private String removeNode(Node node) {
        if (node == tail) {
            // 移除尾节点
            tail = tail.pre;
        } else if (node == head) {
            // 移除头节点
            head = head.next;
        } else {
            // 移除中间节点
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.key;
    }

    /**
     * 尾部插入节点
     *
     * @param node 要插入的节点
     */
    private void addNode(Node node) {
        if (tail != null) {
            tail.next = node;
            node.pre = tail;
            node.next = null;
        }
        tail = node;
        if (head == null) {
            head = node;
        }
    }

    class Node {

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }

        Node pre;
        Node next;
        String key;
        String value;
    }

    public static void main(String[] args) {
        LruCache lruCache = new LruCache(5);
        lruCache.put("001", "用户1信息");
        lruCache.put("002", "用户2信息");
        lruCache.put("003", "用户3信息");
        lruCache.put("004", "用户4信息");
        lruCache.put("005", "用户5信息");
        lruCache.get("002");
        lruCache.put("004", "用户4信息更新");
        lruCache.put("006", "用户6信息");
        System.out.println(lruCache.get("001"));
        System.out.println(lruCache.get("006"));
    }
}
