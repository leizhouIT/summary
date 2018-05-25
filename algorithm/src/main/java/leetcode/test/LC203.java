package leetcode.test;

import com.hui.zhang.leetcode.node.ListNode;

public class LC203 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(0);
        listNode.next=new ListNode(1);
        listNode.next.next=new ListNode(2);
        listNode.next.next.next=new ListNode(3);
        System.out.println(del(listNode,2));
    }

    public static ListNode del(ListNode listNode,int target){
        ListNode root = new ListNode(0);
        root.next=listNode;
        ListNode pre = root;
        while (pre.next !=null){
           if (pre.next.val == target){
               pre.next=pre.next.next;
           }else {
               pre = pre.next;
           }
        }
        return root.next;
    }
}
