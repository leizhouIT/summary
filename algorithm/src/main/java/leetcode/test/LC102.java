package leetcode.test;

import com.hui.zhang.leetcode.node.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC102 {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left=new TreeNode(9);
        treeNode.right=new TreeNode(20);
        treeNode.left.left=new TreeNode(15);
        treeNode.right.right=new TreeNode(7);
        System.out.println(zhongxu(treeNode));
    }
    static List<Integer> list = new ArrayList<>();







    //层序遍历
    public static List<Integer> bianli(TreeNode root){
        TreeNode curr=root;
        if (curr ==null){
            return null;
        }
        list.add(curr.val);
        while (curr.left != null){
            list.add(curr.left.val);
            curr=curr.left;
        }
        curr =root;
        while (curr.right!=null){
            list.add(curr.right.val);
            curr=curr.right;
        }
        return list;
    }


    public static List<Integer> zhongxu(TreeNode treeNode){
        if (treeNode != null){

            zhongxu(treeNode.left);
            zhongxu(treeNode.right);
            list.add(treeNode.val);
        }
        return list;
    }
}
