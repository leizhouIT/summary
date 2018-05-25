package leetcode;

import java.util.Stack;

/**
 * Created by zhanghui on 2018/1/7.
 * 最长有效的括号
 */
public class LC32LongestValidParentheses {

    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')' && !stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                stack.pop();
                max = Math.max(max, i - stack.peek());
            } else {
                stack.push(i);
            }
        }
        return max;
    }
}
