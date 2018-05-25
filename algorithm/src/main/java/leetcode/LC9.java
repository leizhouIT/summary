package leetcode;

/**
 * 实现正则表达式与支持匹配'.'和'*'
 * '' 匹配任何单个字符。
 *   '*'匹配零个或多个前面的元素。

 * 匹配应覆盖整个输入字符串（不是部分）。

 * 函数原型应该是：
 * bool isMatch（const char * s，const char * p）

 * 一些例子：
 * isMatch（“aa”，“a”）→false
 * isMatch（“aa”，“aa”）→true
 * isMatch（“aaa”，“aa”）→false
 * isMatch（“aa”，“a*”）→true
 * isMatch（“aa”，“.*”）→true
 * isMatch（“ab”，“.*”）→true
 * isMatch（“aab”，“c*a*b”）→true
 */
public class LC9 {

    public static boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty())
            return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatch(text, pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1), pattern)));
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    public static void main(String[] args) {
        boolean aab = isMatch("ab", "cbb*a*b");
        System.out.println(aab);
    }
}
