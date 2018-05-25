package datastructure;

import java.util.Arrays;

/**
 * Created by zhoulei8 on 2017/5/17.
 */
public class ArraysDemo {


    public static void main(String[] args) {
        ArraysDemo arraysDemo = new ArraysDemo(100);
//        arraysDemo.insert(56);
//        arraysDemo.insert(55);
//        arraysDemo.insert(40);
//        arraysDemo.insert(1);
        arraysDemo.print();
        arraysDemo.insterSort();
        arraysDemo.print();
    }

    private long[] longarr ;
    private int nElems ;


    public ArraysDemo(int max) {
        longarr = new long[]{5,7,8,3,2,1,5};//new long[max];
        nElems = 7;
    }

    public int size() {
        return nElems;
    }

    /**
     * 二分查找数据中的元素,返回元素的下标
     *
     * @param target
     * @return
     */
    public int find(long target) {
        int startPoint = 0;
        int endPoint = nElems - 1;//数组下标从0开始，
        int minPoint = 0;
        while (true) {
            minPoint = (startPoint + endPoint) / 2;
            if (longarr[minPoint] == target) {
                //返回下标
                return minPoint;
            } else if (startPoint > endPoint) {//开始大于结束节点，没有找到
                return -1;
            } else {
                if (longarr[minPoint] < target) {//小于查找的数据，

                    startPoint = minPoint + 1;
                } else {
                    endPoint = minPoint - 1;
                }
            }
        }
    }

    /**
     * 按照从小到大插入数据
     *
     * @param value
     */
    public void insert(long value) {
        int j;
        for (j = 0; j < nElems; j++) {
            //循环数组中的元素，找到最小值，记录j=将要插入的下标
            if (longarr[j] > value) {
                break;
            }
        }
        //移动元素，将原数组中的元素全部向前移一位
        for (int k = nElems; k > j; k--) {
            longarr[k] = longarr[k - 1];
        }
        longarr[j] = value;
        nElems++;
    }

    /**
     * 删除元素中的数据
     *
     * @param value
     * @return
     */
    public boolean delete(long value) {
        int j = find(value);
        if (j == -1) {
            return false;
        }
        if (j == nElems) {//数组角标越界
            return false;
        } else {
            //移动元素，删除元素的位置后面元素向前移动一位
            for (int i = j; i < nElems; i++) {
                longarr[i] = longarr[i + 1];
            }
            nElems--;
            return true;
        }
    }

    public void print() {
        System.out.println(Arrays.toString(longarr));

    }

    //*****************sort****************************

    /**
     * 插入排序
     */
    public void insterSort() {
        int in, out;
        for (out = 0; out < nElems; out++) {
            long temp = longarr[out];//记录目标值
            in = out;// 将当前下标定义为目标位置
            //将temp的值和左面的做比较，并且移动temp左边的值向右移动一位
            while (in > 0 && longarr[in - 1] >= temp) {
                longarr[in] = longarr[in - 1];
                --in;
            }
            // 将目标值记录到最小值位置
            longarr[in] = temp;
        }
    }
}

/**
 * 归并排序
 */
class MergeSort{

    private long[] theArr;
    private int nElems;
    public MergeSort(int max){
        theArr = new long[max];
        nElems=0;
    }

    public void mergeSort(){
        long[] workSpace = new long[nElems];
        recmergeSort(workSpace,0,nElems-1);

    }
    private void recmergeSort(long[] arr,int lowerBound,int upperBound){

        if (lowerBound==upperBound){
            return;
        }else {
            int mid = (lowerBound+upperBound)/2;
            recmergeSort(arr,lowerBound,mid);
            recmergeSort(arr,mid+1,upperBound);
            merge(arr,lowerBound, mid+1,upperBound);
        }
    }

    private void merge(long[] arrs,int lowPtr,int highPtr,int upperBound){

        int j=0;
        int lowerBound=lowPtr;
        int mid=highPtr-1;
        int n=upperBound-lowerBound+1;
        while (lowPtr<=mid && highPtr<=upperBound){
            if (theArr[lowerBound]<theArr[highPtr]){
                arrs[j++]=theArr[lowPtr++];
            }else {
                arrs[j++]=theArr[highPtr++];
            }
        }
        while (lowPtr<=mid){
            arrs[j++]=theArr[highPtr++];
        }
        for (j=0;j<n;j++){
            theArr[lowerBound+j]=arrs[j];
        }
    }
}
