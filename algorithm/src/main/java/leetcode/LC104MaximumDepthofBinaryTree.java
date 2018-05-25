package leetcode;

import com.hui.zhang.leetcode.node.TreeNode;

/**
 * 给定一棵两叉树，求它的最大深度。
 *
 * 递归求解，递归公式
 　　f(n) = 0; n=null,
 　　f(n) = 1+ max(f(n左)， f(n右))
 */
public class LC104MaximumDepthofBinaryTree {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left=new TreeNode(9);
        treeNode.right=new TreeNode(20);
        treeNode.left.left=new TreeNode(15);
        treeNode.right.right=new TreeNode(7);
        System.out.println(maxDepth(treeNode));
//        dis(treeNode);

    }
    /*public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }*/

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 1;
        } else {
            //求左子树的最大深度
            int left = maxDepth(root.left);
            //右子树的最大深度
            int right = maxDepth(root.right);
            //将根节点的要算上
            return 1 + (left > right ? left : right);
        }
    }

    public static void dis(TreeNode root){
        TreeNode current = root;
        if (root ==null){
            return;
        }
        System.out.println(root.val);
        while (current.left != null){
            current = current.left;
            System.out.println(current.val);
        }
        //将当前节点还原到root，查找右子树
        current=root;
        while (current.right != null){
            current =current.right;
            System.out.println(current.val);
        }
    }

}
