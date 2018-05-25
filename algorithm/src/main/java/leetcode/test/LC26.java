package leetcode.test;

public class LC26 {

    public static void main(String[] args) {
        int [] arr = {1,2,2,3};
        System.out.println(removeDuplicates(arr));
    }
    public static int removeDuplicates(int[] A) {

        if (A.length == 0) {
            return 0;
        }

        int index = 0;//[0,index]只记录数组中出现的按从小到大的唯一一个数，已经排好序了
        int next = 1;

        // 算法思想：找index之后的比A[index]大的数，如是找到就移动到A[index+1]处，
        // index移动到下一个位置，next移动到下一个位置，再找比A[index]大的数

        while (next < A.length) {
            while (next < A.length && A[index] == A[next] ) { // 找不等于数组中最
                next++;
            }

            if (next < A.length) {
                index++;
                A[index] = A[next];
                next++;
            }
        }
        return index + 1;
    }

    private static void swap(int[] a, int x, int y) {
        int tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }
}
