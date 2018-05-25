package leetcode;

import com.hui.zhang.leetcode.node.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class LC145 {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left=new TreeNode(9);
        treeNode.right=new TreeNode(20);
        treeNode.left.left=new TreeNode(15);
        treeNode.right.right=new TreeNode(7);
        List<Integer> list = postorderTraversal(treeNode);
        System.out.println(list);
    }

    private static List<Integer> result;
    public static List<Integer> postorderTraversal(TreeNode root) {
        result = new LinkedList<>();
        preOrder(root);
        return result;
    }
    private static void preOrder(TreeNode root) {
        if (root != null) {
//            result.add(root.val);
            preOrder(root.left);
            preOrder(root.right);
            result.add(root.val);
        }
    }
}
