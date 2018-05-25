package leetcode.test;

import org.springframework.util.StringUtils;

public class LC5 {

    public static void main(String[] args) {
        String aasfaa = huiwen("aasfaa");
        System.out.println(aasfaa);
    }


    public static String huiwen(String str){
        if (StringUtils.isEmpty(str) || str.length()<2){
            return str;
        }
        int max = 0;
        String result=null;
        int length = str.length();
        boolean[][] table = new boolean[length][length];
        //处理单个字符都是回文
        for (int i=0;i<length;i++){
            table[i][i]=true;
            result = str.substring(i,i+1);
            max=1;
        }
        for (int i=0;i<length-1;i++){
            if (str.charAt(i)==str.charAt(i+1)){
                table[i][i+1]=true;
                result=str.substring(i,i+2);
                max=2;
            }
        }
        for (int len=3;len<length;len++){
            for (int i=0,j;(j=i+len-1)<=length-1;i++){
                if (str.charAt(i)==str.charAt(j)){
                   table[i][j]=true;
                   if (table[i][j]&&max<len){
                       result = str.substring(i,j+1);
                       max=len;
                   }
                }else {
                    table[i][j]=false;
                }
            }
        }
        return result;

    }
}
