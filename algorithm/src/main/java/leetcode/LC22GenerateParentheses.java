package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghui on 2018/1/6.
 * n对括号  有多少种匹配方式
 */
public class LC22GenerateParentheses {


    public static List<String> generateParentheses(int n) {

        List<String> list = new ArrayList<String>();
        backtract(list, "", 0, 0, n);
        return list;
    }

    public static void backtract(List<String> list, String str, int open, int close, int max) {

        if (str.length() == max * 2) {
            list.add(str);
            return;
        }
        if (open < max) {
            backtract(list, str + '(', open + 1, close, max);
        }
        if (close < open) {
            backtract(list, str + ')', open, close + 1, max);
        }
    }
}
