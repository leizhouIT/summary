package leetcode;

import com.hui.zhang.leetcode.node.TreeNode;

import java.util.Stack;

/**
 * 验证二叉搜索树
 *
 * 对二叉搜索树进行中序遍历，结果按顺序保存起来，
 * 对于二叉搜索树中序遍历其结果有一个从小到大的排列的序列，
 * 并且没有重重元素，由此可以判断一棵树是否是二叉搜索树
 */
public class LC98 {
    private Stack<Integer> stack;
    public boolean isValidBST(TreeNode root) {

        if (root == null) {
            return true;
        }

        stack = new Stack<>();
        //根据中序遍历将值按照从小到大的顺序入栈
        inOrder(root);

        //弹出栈顶元素，也就是最大的元素
        int i = stack.pop();
        int j;
        while (!stack.isEmpty()) {
            //弹出第二个元素
            j = stack.pop();
            //根据完全二叉树，栈中存储的是从小到大的顺序
            if (i <= j) {
                return false;
            }

            //将i的指针移动到j，然后j继续弹出栈比较
            i = j;
        }

        return true;
    }

    /**
     * 如果是一棵二叉查找树必必是有序的
     * @param root
     */
    public void inOrder(TreeNode root) {

        if (root != null) {
            inOrder(root.left);
            //将小到大的顺序的数据入栈
            stack.push(root.val);
            inOrder(root.right);
        }
    }
}
