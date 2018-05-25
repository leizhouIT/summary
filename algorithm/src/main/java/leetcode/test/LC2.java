package leetcode.test;

import com.hui.zhang.leetcode.node.ListNode;

public class LC2 {
    // todo 后续
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1==null && l2==null){
            return null;
        }
        ListNode p1=l1;
        ListNode p2=l2;
        ListNode head = new ListNode(0);
        int jinwei = 0;
        head.next=l1;
        int sum;
        while (p1!=null && p2!=null){
            sum = p1.val + p2.val + jinwei;
            jinwei = sum / 10;
            l1.val=sum%10;
            p1 = p1.next;
            p2=p2.next;
        }
        if (p1==null){
            head.next=p1;
        }
        if (p2==null){
            head.next=p2;
        }
        return head.next;

    }
}
