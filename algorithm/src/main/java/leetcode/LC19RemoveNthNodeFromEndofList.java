package leetcode;

import com.hui.zhang.leetcode.node.ListNode;

/**
 * 给定一个链表，从列表末尾删除第n个节点并返回它的头部。
 * 给定链表：1-> 2-> 3-> 4-> 5，并且n = 2。

 从结尾删除第二个节点后，链表将变为1-> 2-> 3-> 5。

 尝试一次性完成
 */
public class LC19RemoveNthNodeFromEndofList {





    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode pa = head;
        ListNode pb = head;

        // 找到第n个结点
        for (int i = 0; i < n && pa != null; i++) {
            pa = pa.next;
        }

        //如果pa为null说明要删除的元素是链表的倒数第一个，那么直接将整个链表后移一位
        if (pa == null) {
            head = head.next;
            return head;
        }

        // pb与pa相差n-1个结点
        // 当pa.next为null，pb在倒数第n+1个位置
        while (pa.next != null) {
            pa = pa.next;
            pb = pb.next;
        }

        pb.next = pb.next.next;
        return head;
    }





    public static ListNode removeNthFromEnd(ListNode head, int n) {
        //定义一个传入链表的头节点
        ListNode start = new ListNode(0);
        //将新定义的初始节点连接上，并赋值给。slow，fast，
        ListNode slow = start, fast = start;
        slow.next = head;

        /**
         * 找出目标节点的所有后续节点，并记录到slow中
         */
        for (int i = 0; i < n; i++) {
            slow = slow.next;
//            fast = fast.next;
        }

//        while (fast != null) {
//            slow = slow.next;
//            fast = fast.next;
//        }
        //删除节点，将指针指向删除节点的下个节点
        slow.next = slow.next.next;
        return start.next;
    }

    public static void main(String[] args) {

        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next=new ListNode(5);

        ListNode listNode1 = removeNthFromEnd2(listNode, 2);
        System.out.println(listNode1);

    }
}
