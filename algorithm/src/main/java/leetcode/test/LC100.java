package leetcode.test;

import com.hui.zhang.leetcode.node.TreeNode;

public class LC100 {

    public static boolean isSame(TreeNode a, TreeNode b){
        if (a == null && b==null){
            return true;
        }
        if (a != null && b==null){
            return false;
        }
        if (a== null && b!=null){
            return false;
        }
        return a.val==b.val && isSame(a.left,b.left)&&isSame(a.right,b.right);
    }
}
