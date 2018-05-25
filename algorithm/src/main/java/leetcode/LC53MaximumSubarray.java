package leetcode;

/**
 * Created by zhanghui on 2018/1/8.
 */
public class LC53MaximumSubarray {

    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }
        int max = Integer.MIN_VALUE;
        int curSum = 0;
        for (int n : nums) {
            if (curSum <= 0) {
                curSum = n;
            } else {
                curSum += n;
            }
            if (max < curSum) {
                max = curSum;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-1,-2,-3}));
    }
}
