package leetcode;

/**
 * 给定一个字符串S，找出它的最大的回文子串，你可以假设字符串的最大长度是1000，而且存在唯一的最长回文子串
 * 思路:
 * 动态规划法，
 　　假设dp[ i ][ j ]的值为true，表示字符串s中下标从 i 到 j 的字符组成的子串是回文串。那么可以推出：
 　　dp[ i ][ j ] = dp[ i + 1][ j - 1] && s[ i ] == s[ j ]。
 　　这是一般的情况，由于需要依靠i+1, j -1，所以有可能 i + 1 = j -1, i +1 = (j - 1) -1，
    因此需要求出基准情况才能套用以上的公式：
 　　a. i + 1 = j -1，即回文长度为1时，dp[ i ][ i ] = true;
 　　b. i +1 = (j - 1) -1，即回文长度为2时，dp[ i ][ i + 1] = （s[ i ] == s[ i + 1]）。
 　　有了以上分析就可以写出代码了。需要注意的是动态规划需要额外的O(n^2)的空间。
 */
public class LC5 {

    public static String longestPalindrome(String s) {

        if (s == null || s.length() < 2) {
            return s;
        }

        //记录当前回文的最大长度
        int maxLength = 0;
        //记录最后回文的字符串
        String longest = null;

        int length = s.length();
        //定义当前找到的两个位置的字符是否相同，如果相同true
        boolean[][] table = new boolean[length][length];
        /**
         * 单个字符都是回文
         * 例如 "a",回文a
          */
        for (int i = 0; i < length; i++) {
            table[i][i] = true;
            longest = s.substring(i, i + 1);
            maxLength = 1;
        }

        /**
         * 判断两个字符是否是回文
         * 例如"aaxweaa" 回文aa
          */
        for (int i = 0; i < length - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                table[i][i + 1] = true;
                longest = s.substring(i, i + 2);
                maxLength = 2;
            }
        }

        /**
         * 求长度大于2的子串是否是回文串
         * 例如 "abbbsewfbbba" 回文bbb
         */
        //当间隔为3之后依次增长
        for (int len = 3; len <= length; len++) {
            //i和j间隔为2依次递增
            for (int i = 0, j; (j = i + len - 1) <= length - 1; i++) {
                if (s.charAt(i) == s.charAt(j)) {
                    table[i][j] = true;
                    //如果间隔为2的两个元素相同，并且当前最大长度小于当前要找的长度
                    if (table[i][j] && maxLength < len) {
                        //截取新的回文字符串
                        longest = s.substring(i, j + 1);
                        //修改找到的最大回文长度
                        maxLength = len;
                    }
                } else {
                    table[i][j] = false;
                }
            }
        }
        return longest;
    }

    /*public static String longestPalindrome1(String s){
        String result=null;
        String tempString="";
        //定义最长回文子串的长度
        int max=0;
        //遍历字符串中的所有元素
        for(int i=0;i<s.length();i++){
            //数组下标指针j从字符串后面开始往前遍历
            for(int j=s.length()-1;j>i;j--){
                //判断每一个字符串时候为回文
                tempString=s.subStr( i, j+1);
                //如果tempString是回文子串并且其长度(j-i+1)>max
                if(isPalindrome(tempString)&&(j-i+1)>max){
                    max=j-i+1;
                    result=subString(i, j+1);
                }
            }
        }
        return result;
    }*/

    public static void main(String[] args) {
        String aabcdcbaa = longestPalindrome("abbbsewfbbba");
        System.out.println(aabcdcbaa);
    }
}
