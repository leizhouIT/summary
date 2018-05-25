package leetcode;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class MergeList {

    public static List<Integer> mergeTwoSortList(List<Integer> aList,
                                                 List<Integer> bList) {
        int aLength = aList.size(), bLength = bList.size();
        List<Integer> mergeList = new ArrayList();
        int i = 0, j = 0;
        while (aLength > i && bLength > j) {
            if (aList.get(i) > bList.get(j)) {
                mergeList.add(i + j, bList.get(j));
                j++;
            } else {
                mergeList.add(i + j, aList.get(i));
                i++;
            }
        }
        // blist元素已排好序， alist还有剩余元素
        while (aLength > i) {
            mergeList.add(i + j, aList.get(i));
            i++;
        }
        // alist元素已排好序， blist还有剩余元素
        while (bLength > j) {
            mergeList.add(i + j, bList.get(j));
            j++;
        }
        return mergeList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3);
        ArrayList<Integer> integers1 = Lists.newArrayList(2, 5, 7, 9);
        List<Integer> list = mergeTwoSortList(integers, integers1);
        System.out.println(list);
    }
}
