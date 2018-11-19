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
        ListNode result = removeElements(head.next, val);
        // 判断头结点是否需要删除
        if (head.val == val) {
            return result;
        }

        head.next = result;
        return head;
    }
}
