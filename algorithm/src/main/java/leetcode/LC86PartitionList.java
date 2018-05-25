package leetcode;

import com.hui.zhang.leetcode.node.ListNode;

/**
 * 链表分区，小于指定值的移到链表的前面，大值不变
 */
public class LC86PartitionList {

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode small = new ListNode(-1);
        ListNode newSmall = small;
        ListNode big = new ListNode(-1);
        ListNode newbighead = big;

        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                big.next = head;
                big = big.next;
            }
            head = head.next;
        }
        big.next = null;
        small.next = newbighead.next;
        return newSmall.next;
    }
}
