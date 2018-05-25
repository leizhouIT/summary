package leetcode.test;

import java.util.ArrayList;
import java.util.List;

public class LC54 {
    public static List<Integer> test(int [][] arr){
        List<Integer> result = new ArrayList<>();
        if (arr.length==1){
            for (int i : arr[0]){
                result.add(i);
            }
            return result;
        }

        if (arr[0].length==1){
            for (int i=0; i<arr[0].length;i++){
                result.add(arr[i][0]);
            }
            return result;
        }

        int row=arr.length;
        int col = arr[0].length;
        int cycle = (row>col?col:row+1)/2;
        int round=0;
        int top=0;
        int left =0;
        int right=arr[0].length-1;
        int down=arr.length-1;
        int totle = row*col;
        int count=0;
        while (round<cycle){
            for (int i=left;i<right && count<totle;i++){
                result.add(arr[round][i]);
                count++;
            }
            top++;
            for (int i=top;i<down && count<totle;i++){
                result.add(arr[i][col-round-1]);
                count++;
            }
            right--;
            for (int i=right;i>=left && count<totle ;i--){
                result.add(arr[row-round-1][i]);
                count++;
            }
            down--;
            for (int i=down;i>=top && count<totle;i--){
                result.add(arr[i][round]);
                count++;
            }
            left++;
            round++;
        }
        return result;
    }
}
