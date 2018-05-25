package leetcode;


import java.util.ArrayList;
import java.util.List;

/**
 * 对一棵二叉树进行中序遍历。
 */
public class LC94 {


    private static List<Integer> list = new ArrayList<>();
    public static List<Integer> forNode(Node listNode){
        if (listNode !=null){
            list.add(listNode.value);
            forNode(listNode.next);
            forNode(listNode.pre);
        }
        return list;
    }
}
