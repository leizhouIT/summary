package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 求两数的和等于指定值
 * 例如：输入数组{2, 7, 11, 15},9.返回下标为0和1的
 */
public class LC1TwoSum {


    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            //取到差值
            int complement = target - nums[i];
            //查看map是否存在并且不和当前相同
            if (map.containsKey(complement) && map.get(complement) != i) {
                //记录当前下标和map中找到的下标
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    public static void main(String[] args) {
        int [] arr ={2, 7, 11, 15};
        int[] ints = twoSum2(arr, 9);
        System.out.println(Arrays.toString(ints));
    }
}
