package leetcode;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据所有子集
 */
public class LC78Subsets {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        backtrace(lists, new ArrayList<Integer>(), nums, 0);
        return lists;
    }

    private static void backtrace(List<List<Integer>> lists, List<Integer> tempList, int[] nums, int start) {
        lists.add(new ArrayList<Integer>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrace(lists, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = subsets(new int[]{2, 4, 1});
        System.out.println(JSON.toJSONString(lists));
    }
}
