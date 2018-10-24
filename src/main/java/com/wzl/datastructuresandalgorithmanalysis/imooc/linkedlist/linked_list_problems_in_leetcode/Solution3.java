package com.wzl.datastructuresandalgorithmanalysis.imooc.linkedlist.linked_list_problems_in_leetcode;

public class Solution3 {

    public ListNode removeElements(ListNode head, int val) {

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {

        int[] num = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(num);
        System.out.println(head);

        ListNode node = (new Solution3()).removeElements(head, 6);
        System.out.println(node);
    }
}
