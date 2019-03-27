package linkedlist;

/**
 * 链表逆序算法
 *
 * @author wzl
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        // 初始化链表
        Node tmp = new Node(9, null);
        tmp = new Node(4, tmp);
        tmp = new Node(1, tmp);
        tmp = new Node(5, tmp);
        tmp = new Node(3, tmp);
        head = tmp;

        // 逆序前输出数组
        while (tmp != null) {
            System.out.println(tmp.data);
            tmp = tmp.next;
        }

        // 逆序链表
        reverseLinkedList();

        // 逆序后输出链表
        System.out.println("逆序");
        tmp = head;
        while (tmp != null) {
            System.out.println(tmp.data);
            tmp = tmp.next;
        }
    }

    private static Node head;

    public static void reverseLinkedList() {
        if (head == null || head.next == null) {
            return;
        }

        Node pre = head;
        Node current = head.next;
        Node next;

        while (current != null) {
            next = current.next;

            current.next = pre;
            pre = current;
            current = next;
        }

        head.next = null;
        head = pre;
    }

    private static class Node {

        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
