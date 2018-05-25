package leetcode.test;


import java.util.HashMap;
import java.util.Map;

public class LC1 {
    public int[] sum(int[] arr, int targe) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        for (int i = 0; i < arr.length; i++) {
            int i1 = targe - arr[i];
            Integer integer = map.get(i1);
            if (map.containsKey(i1) && map.get(i1) != i) {
                return new int[]{i, integer};
            }
        }
        return new int[]{};
    }
}
