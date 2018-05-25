package leetcode;

import com.hui.zhang.leetcode.node.ListNode;

/**
 * Created by zhanghui on 2018/1/7.
 * 两个有序链表合并
 * 输入： 1-> 2-> 4，1-> 3-> 4
 *  输出： 1-> 1-> 2-> 3-> 4-> 4
 */
public class LC21MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //头节点
        ListNode head = new ListNode(0);
        //用于记录当前找的链表节点
        ListNode tail = head;
        while (l1 != null && l2 != null) {
            /**
             * 如果l1的值小于等于l2的值，记录当前链表到tail的next后，并且将l1指针向下移动
             */
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            /**
             * 每次查找后想记录的指针向后移动一位，为了保留之前已经找到的节点
             */
            tail = tail.next;
        }
        //保留之前已经找到的节点，连接不为null的节点
        tail.next = (l1 != null ? l1 : l2);
        return head.next;
    }


    public static void main(String[] args){
//        ListNode l1=new ListNode(1);
//        ListNode l2=new ListNode(3);
        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(2);
//        listNode1.next.next = new ListNode(3);
        listNode1.next.next = new ListNode(4);
        listNode1.next.next.next=new ListNode(5);

        ListNode listNode2 = new ListNode(1);
        listNode2.next = new ListNode(3);
        listNode2.next.next = new ListNode(4);
        LC21MergeTwoSortedLists mergeTwoSortedList=new LC21MergeTwoSortedLists();
        ListNode l3= mergeTwoSortedList.mergeTwoLists(listNode1,listNode2);
        System.out.println(l3);
    }

}

