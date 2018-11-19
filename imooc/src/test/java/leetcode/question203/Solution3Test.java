package leetcode.question203;

import org.junit.Test;

public class Solution3Test {

    @Test
    public void removeElements() {
        int[] num = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(num);
        System.out.println(head);

        ListNode node = (new Solution3()).removeElements(head, 6);
        System.out.println(node);
    }
}