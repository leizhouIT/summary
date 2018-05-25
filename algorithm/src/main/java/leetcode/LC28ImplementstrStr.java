package leetcode;

/**
 * Created by zhanghui on 2018/1/7.
 * 一个字符串在另一个字符串中的位置
 * 例如：
 * 输入： haystack =“hello”，needle =“ll” 输出： 2
 * 输入： haystack =“aaaaa”，needle =“bba”输出： -1
 */
public class LC28ImplementstrStr {

    /*public static int strStr(String haystack, String needle) {
        int l1 = haystack.length(), l2 = needle.length();
        if (l1 < l2) return -1;
        else if (l2 == 0) return 0;

        int threshold = l1 - l2;
        for (int i = 0; i <= threshold; ++i) {
            if (haystack.substring(i, i + l2).equals(needle)) {
                return i;
            }
        }
        return -1;
    }*/

    public static int strStr(String haystack, String needle) {

        if (haystack == null || needle == null) {
            return -1;
        }

        if (needle.length() > haystack.length()) {
            return -1;
        }

        if ("".equals(haystack)) {
            if ("".equals(needle)) {
                return 0;
            } else {
                return -1;
            }
        } else {
            if ("".equals(needle)) {
                return 0;
            }
        }


        return kmpIndex(haystack, needle);
    }

    private static int kmpIndex(String haystack, String needle) {


        int i = 0;
        int j = 0;
        int[] next = next(needle);
        while (i < haystack.length() && j < needle.length()) {
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                ++i;
                ++j;
            } else {
                j = next[j];
            }
        }

        if (j == needle.length()) {
            return i - j;
        } else {
            return -1;
        }
    }

    private static int[] next(String needle) {
        int[] next = new int[needle.length()];
        next[0] = -1;
        int i = 0;
        int j = -1;
        int k = needle.length() - 1;
        while (i < k) {
            if (j == -1 || needle.charAt(i) == needle.charAt(j)) {
                ++i;
                ++j;
                if (needle.charAt(i) != needle.charAt(j)) {
                    next[i] = j;
                } else {
                    next[i] = next[j];
                }

            } else {
                j = next[j];
            }
        }

        return next;
    }

    public static void main(String[] args) {
        int i = strStr("helloabcd", "ab");
        System.out.println(i);
    }
}
