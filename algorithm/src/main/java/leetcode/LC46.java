package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC46 {

    public static void main(String[] args) {
        int[] arr = {1,2,3};
        List<List<Integer>> permute = permute(arr);
        System.out.println(permute);
    }


    private static List<List<Integer>> result;
    public static List<List<Integer>> permute(int[] num) {

        result = new LinkedList<>();
        if (num != null) {
            permute(0, num);
        }

        return result;
    }

    private static void permute(int i, int[] num) {

        if (i == num.length) {
            List<Integer> l = new ArrayList<>();
            for (int n: num) {
                l.add(n);
            }

            result.add(l);
        }else {

            for (int j = i; j < num.length; j++) {
                swap(num, j, i);
                permute(i + 1, num);
                swap(num, j, i);
            }
        }
    }

    private static void swap(int[] A, int x, int y) {
        int tmp = A[x];
        A[x] = A[y];
        A[y] = tmp;
    }
}
