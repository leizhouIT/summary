package leetcode;

import com.hui.zhang.leetcode.node.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 层序遍历
 * 给定一个二叉树，输出它的每一层的结点。
 * 例如：输入
 *      3
       / \
      9  20
      /  \
     15   7

输出：
    [
     [3],
     [9,20],
     [15,7]
    ]
 *
 *
 * 使用两队列，一个保存当前处理的层，一个保存下一次要处理的层。只到每一层都处理完
 */
public class LC102BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left=new TreeNode(9);
        treeNode.right=new TreeNode(20);
        treeNode.left.left=new TreeNode(15);
        treeNode.right.right=new TreeNode(7);
        System.out.println(levelOrder(treeNode));

    }
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        if (root == null) {
            return resultList;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        //将树放入到队列中
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<Integer>();
            for (int i = 0; i < levelNum; i++) {
                //取队列的头元素的左节点，如果不为null,
                if (queue.peek().left != null) {
                    /**
                     * 首次：
                     * 将左节点放入队列
                     * 队列中是有2个元素，一个是root，一个是
                     * 所有的左子树
                     * 其次：
                     *   每次将下个节点放入队列，最后删除当前节点
                     */
                    queue.offer(queue.peek().left);
                }
                if (queue.peek().right != null) {
                    /**
                     * 将右节点放入队列
                     * 队列中是有3个元素，一个是root，
                     * 一个是所有的左子树，一个是所有的右子树
                     * 其次：
                     *   每次将下个节点放入队列，最后删除当前节点
                     */
                    queue.offer(queue.peek().right);
                }
                /**
                 * 首次：从队列中删除节点，并放入结果中，将root节点删除
                 * 其次：从队列中删除当前节点，
                 */
                subList.add(queue.poll().val);
            }
            resultList.add(subList);
        }
        return resultList;
    }
}
