package leetcode.test;

import com.hui.zhang.leetcode.node.ListNode;

public class LC21 {

    public static ListNode mer(ListNode l1,ListNode l2){
        ListNode head = new ListNode(-1);//辅助节点
        ListNode root = head;
        while (l1!=null && l2!=null){
            if (l1.val<l2.val){
                root.next=l1;
                l1=l1.next;
            }else {
                root.next=l2;
                l2=l2.next;
            }
            root=root.next;
        }
        root.next=l1!=null?l1:l2;
        return head.next;
    }
}
