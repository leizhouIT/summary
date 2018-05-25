package leetcode;

import com.hui.zhang.leetcode.node.ListNode;

public class LC24SwapNodesinPairs {

    /**
     * 成对反转连表
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null) return head;
        ListNode dmy = new ListNode(0), pre = dmy;
        dmy.next = head;
        while (pre.next != null && pre.next.next != null) {
            ListNode first = pre.next;
            pre.next = first.next.next;
            first.next = first.next.next;
            pre.next.next = first;
            pre = pre.next.next;
        }
        return dmy.next;
    }

    /**
     * 反转链表
     *
     * @param current
     * @return
     */
    public ListNode reverse(ListNode current) {
        if (current == null) return current;
        ListNode pre = null;
        ListNode next = null;
        while (current != null) {
            next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;
    }
}
