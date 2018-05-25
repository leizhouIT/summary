package leetcode;

/**
 * 反转int型数值
 * 输入： 123
 输出：   321
 输入： 120
 输出： 21
 */
public class LC7ReverseInteger {
    public static int reverse(int x) {
        int sign = x < 0 ? -1 : 1;
        x = Math.abs(x);
        int res = 0;
        while (x > 0) {
            if (Integer.MAX_VALUE / 10 < res || (Integer.MAX_VALUE - x % 10) < res * 10) {
                return 0;
            }
            res = res * 10 + x % 10;
            x /= 10;
        }
        return sign * res;
    }

    /**
     * 次函数相当于从数据的最后一位向前按一位一位寻找
     * @param x
     * @return
     */
    public static int reverse2(int x) {
            int y=0;
            int n;    //接收余数
            while( x != 0){
                //获取余数，如果末尾非0，其实返回微末的值，如果是0返回0
                n = x%10;
                //判断值是否溢出
                if (y > Integer.MAX_VALUE/10 || y < Integer.MIN_VALUE/10){    //如果反转后整数溢出，return 0.
                    return 0;
                }
                /**处理当前找到的尾数的值，
                 * 首次y是0，获取余数n上面已经取到末尾的值
                 * 下次应该是将y扩大10倍然后加上余数，因为需要对每次是对数据加一位，相当于在原有的
                 * 基础上扩大10倍在加上余数n
                 *
                 * */
                y = y*10 + n;
                //将数据缩小一位，将原有数据不断后末尾一位一位的缩小
                x /= 10;
            }
            return y;
    }

    public static void main(String[] args) {
        int reverse = reverse2(-25018883);
        System.out.println(189%10);
    }
}
