package leetcode;

/**
 * 给定一个数组和一个值，就地移除该值的所有实例并返回新的长度。

 不要为其他数组分配额外空间，您必须通过在 O（1）额外内存中就地修改输入数组来实现此目的。

 元素的顺序可以改变。无论你在新的长度以外留下什么都没有关系。
 例如：
 给定nums = [3,2,2,3]，val = 3，

 你的函数应该返回length = 2，num的前两个元素是2
 */
public class LC27 {
    public static int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int [] arr ={3,2,2,3};
        System.out.println(removeElement(arr,3));
    }
}
