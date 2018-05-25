package leetcode;

/**
 * 给定一个有序数组，删除重复内容，使每个元素只出现一次并返回新的长度。

 不要为其他数组分配额外空间，您必须通过在 O（1）额外内存中就地修改输入数组来实现此目的。
 例如：
 给定nums = [1,1,2]，

 你的函数应该返回length = 2，num的前两个元素分别是1和2。
 无论你在新的长度以外留下什么都没有关系。
 */
public class LC26 {
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        //定义其中一个指针
        int i = 0;
        //上面i已经定义了一个指针，j定义下个指针，如果i和j对应的下标的值相同，继续向后移动j的指针
        for (int j = 1; j < nums.length; j++) {
            //比较相邻的两个元素的值
            if (nums[i] == nums[j]) {
                //移动指针
                i++;
                //去掉重复的值，并将不重复的值向前移动
                nums[i]=nums[j];
            }
        }
        //返回当前i移动的下标数+1，+1取到数据的长度，应为数组是从0开始的。
        return i + 1;
    }

    public static void main(String[] args) {
        int [] arr ={1,2,2,3};
        int i = removeDuplicates(arr);
        System.out.println(i);
    }
}
