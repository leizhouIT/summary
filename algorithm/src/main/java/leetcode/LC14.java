package leetcode;

public class LC14 {
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    public static void main(String[] args) {
//        String [] arr ={"a","b","c","d"};
        String [] arr ={"abcdef"};
        System.out.println(longestCommonPrefix(arr));

    }
}
