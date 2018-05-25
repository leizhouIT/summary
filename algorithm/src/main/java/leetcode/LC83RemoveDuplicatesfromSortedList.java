package leetcode;

import com.hui.zhang.leetcode.node.ListNode;

/**
 * 有序链表去重
 */
public class LC83RemoveDuplicatesfromSortedList {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }
}
