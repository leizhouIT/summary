package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LC54 {

    public static void main(String[] args) {
        int[][] arr = {{1,2,3},
                       {4,5,6},
                       {7,8,9}};
        List<Integer> list = spiralOrder(arr);
        System.out.println(list);
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>(50);

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        // 只有一行的情况
        if (matrix.length == 1) {
            for (int i : matrix[0]) {
                result.add(i);
            }
            return result;
        }

        // 只有一列的情况
        if (matrix[0].length == 1) {
            for (int i = 0; i < matrix.length; i++) {
                result.add(matrix[i][0]);
            }

            return result;
        }

        // 计算有多少圈
        int row = matrix.length;//所有行
        int col = matrix[0].length;//所有列
        int cycle = row < col ? row : col;
        cycle = (cycle + 1) / 2;//计算所有圈数

        int round = 0; // 记录当前是第几圈
        int left = 0;//最左则最大高度
        int right = matrix[0].length - 1;//最右侧的最大高度
        int top = 0;//行的长度
        int down = matrix.length - 1;//最下层长度
        int total = col*row;//所有要打印的总数
        int count = 0;
        while (round < cycle) {

            // 上面一行
            for (int i = left; i <= right && count < total; i++) {
                count++;
                result.add(matrix[round][i]);
            }
            top++; //

            // 右边一列
            for (int i = top; i <= down && count < total; i++) {
                count++;
                result.add(matrix[i][col - round - 1]);
            }
            right--;

            // 底下一行
            for (int i = right; i >= left && count < total; i--) {
                count++;
                result.add(matrix[row - round - 1][i]);

            }
            down--;

            // 左边一列
            for (int i = down; i >= top && count < total; i--) {
                count++;
                result.add(matrix[i][round]);
            }
            left++;

            round++;
        }

        return result;
    }
}
