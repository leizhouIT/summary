package leetcode.test;

import com.hui.zhang.leetcode.node.TreeNode;

public class LC104 {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left=new TreeNode(9);
        treeNode.right=new TreeNode(20);
        treeNode.left.left=new TreeNode(15);
        treeNode.right.right=new TreeNode(7);
        System.out.println(zuida(treeNode));
    }

    public static int zuida(TreeNode treeNode){
        if (treeNode ==null){
            return 0;
        }else if (treeNode.left==null && treeNode.right==null){
            return 1;
        }else {
            int left = zuida(treeNode.left);
            int right = zuida(treeNode.right);
            return 1+(left>right?left:right);
        }
    }
}
