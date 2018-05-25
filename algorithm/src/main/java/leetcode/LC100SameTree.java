package leetcode;

import com.hui.zhang.leetcode.node.TreeNode;

/**
 * 给定两个二叉树，判断这两棵树是否相等。
 * 仅当两棵树的结构相同，结点值都相等时都会相等
 *
 * 使用递归进行求解，先判断当前结点值是否相等，
 * 如果相等就再比较其左右子树，只有当所有的结点都相等才相等。
 */
public class LC100SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }
}
