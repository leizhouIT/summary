package leetcode.test;

import com.hui.zhang.leetcode.node.ListNode;

public class LC206 {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(0);
        listNode.next=new ListNode(1);
        listNode.next.next=new ListNode(2);
        listNode.next.next.next=new ListNode(3);
        System.out.println(res(listNode));
    }

    public static ListNode res2(ListNode head){
        ListNode root = new ListNode(0);
       ListNode nextNode=null;
       while (head!= null){
           nextNode=head.next;
           head.next=root.next;
           root.next=head;
           head=nextNode;
       }
       return root.next;
    }






    public static ListNode res(ListNode head){
        // 头结点，辅助节点
        ListNode root = new ListNode(0);
        ListNode nextNode;
        while (head != null) {
            //将原链表的头节点的以下记录到nextNode中
            nextNode = head.next;
            //取出原链表的头节点，将头节点连接到辅助节点的下个节点
            head.next = root.next;
            //将辅助节点的下个节点指向原链表，此时原链表中只包含一个当前的根节点
            root.next = head;
            //将之前记录的取出头节点的原链表，在重新赋给原链表
            head = nextNode;
        }
        return root.next;

    }
}
