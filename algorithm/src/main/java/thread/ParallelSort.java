package thread;

import java.util.Arrays;

/**
 * Created by zhoulei8 on 2017/3/22.
 */
public class ParallelSort {

    public static void main(String[] args) {
        int [] arr = {8,7,6,3,1,9,5,8,9};
//        bubbleSort(arr);
        oddEvenSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 冒泡排序
     * @param arr
     */
    public static void bubbleSort(int[] arr){
        for (int i = arr.length-1; i>0;i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1]=temp;
                }

            }
        }
    }

    /**
     * 奇偶交换排序
     * @param arr
     */
    public static void oddEvenSort(int[] arr){

        /**
         * exchFlag ,用于记录当前数据交换，每交换一次，变更表示，
         * start 表示是奇偶 0偶交换，1，奇交换
         */
        int exchFlag = 1,start =0;
        while (exchFlag == 1 || start ==1){
            exchFlag = 0;
            /**
             * i+=2 选中对应的奇偶位
             */
            for (int i = start; i < arr.length -1; i+=2) {
                if (arr[i]>arr[i+1]){
                    int temp = arr[i];
                    arr[i]=arr[i+1];
                    arr[i] = temp;
                    exchFlag = 1;
                }
            }
            if (start ==0){
                start =1;
            }else {
                start =0;
            }
        }

    }
}
