package leetcode.test;

import com.hui.zhang.leetcode.node.ListNode;

public class LC19 {

    public static ListNode del(ListNode listNode,int n){
        ListNode pa=listNode;
        ListNode pb=listNode;
        for (int i=0;i<n && pa!=null;i++){
            pa=pa.next;
        }
        if (pa==null){
            listNode=listNode.next;
            return listNode;
        }
        while (pa.next!=null){
            pa=pa.next;
            pb=pb.next;
        }
        pb.next=pb.next.next;
        return listNode;
    }
}
