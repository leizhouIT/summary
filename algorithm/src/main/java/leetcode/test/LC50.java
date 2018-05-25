package leetcode.test;

public class LC50 {

    public static int nx(int n,int x){
        if (x==0){
            return 1;
        }else {
            int result = nx(n, x/2);
            if (n%2==0){
                result = result*result;
            }else {
                result=result*result*n;
            }
            return result;
        }
    }
}
