package leetcode;

/**
 * Created by zhanghui on 2018/1/7.
 */
public class LC38CountAndSay {

    public static String countAndSay(int n) {
        if (n < 1) {
            return null;
        }
        String result = "1";
        for (int i = 2; i <= n; i++) {
            result = countAndSay(result);
        }
        return result;
    }

    public static String countAndSay(String str) {
        StringBuilder builder = new StringBuilder();

        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                count++;
            } else {
                builder.append(count);
                builder.append(str.charAt(i - 1));
                count = 1;
            }
        }
        builder.append(count);
        builder.append(str.charAt(str.length() - 1));
        return builder.toString();

    }

    public static void main(String[] args){
        String result=countAndSay(4);
        System.out.println(result);
    }
}
