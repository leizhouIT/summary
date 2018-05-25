package leetcode;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * 字符串转int
 */
public class LC8StringtoInteger {

    public static int myAtoi(String str) {
        //去掉字符串两端的空格
        str = str.trim();
        //记录结果
        int result = 0;
        //记录正负号，true为+，false为-
        boolean isPos = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0 && (c == '-' || c == '+')) {
                isPos = c == '+' ? true : false;
                /**
                 * 对于字符char，对应的int值，0-9字符对应int48-57
                 * 判断是否在0-9之间
                 */
            } else if (c >= '0' && c <= '9') {
                /**
                 * 判断结果是否超出最大值范围
                 * c - '0' 获取当前位表示的值，例如，如果c是0，那么减去'0',也就相当于
                 * 48-48，
                 */
                int i1 = c - '0';
                //此判断是为了保证下次读取值后，结果不能溢出
                if (result > (Integer.MAX_VALUE - (i1)) / 10) {
                    return isPos ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                /**
                 * 拼装结果，如果第一次，那么
                 * 就相当于0*10+当前位，第二次
                 * 上次得到的位扩大10倍然后加上当前位，例如上次得到9，当前位位8
                 * 那么就是9*10+8，相当于将之前的结果向前移动一位
                 */
                result = result * 10 + (c - '0');
            } else {
                //将之前记录的正负返回
                return isPos ? result : -result;
            }
        }
        return isPos ? result : -result;
    }

    public static void main(String[] args) {
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3);
        ArrayList<Integer> integers1 = Lists.newArrayList(2, 5, 3, 6);


        int ab123 = myAtoi("-92a3");
        System.out.println(ab123);
    }
}
