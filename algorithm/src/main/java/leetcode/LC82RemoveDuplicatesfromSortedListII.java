package leetcode;

import com.hui.zhang.leetcode.node.ListNode;

/**
 * 链表删除重复节点
 */
public class LC82RemoveDuplicatesfromSortedListII {

    public ListNode deleteDeplicates(ListNode head) {
        if (head == null) return head;
        ListNode fake = new ListNode(0);
        ListNode pre = fake;
        ListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            if (pre.next == cur) {
                pre = pre.next;
            } else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return fake.next;
    }
}
