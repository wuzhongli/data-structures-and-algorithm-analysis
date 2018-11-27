package leetcode.question203;

/**
 * 递归版
 *
 * @author wzl
 */
public class Solution4 {

    public ListNode removeElements(ListNode head, int val) {
        // 定义递归的基准情形
        if (head == null) {
            return null;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }
}
