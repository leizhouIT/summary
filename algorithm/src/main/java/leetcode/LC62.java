package leetcode;

public class LC62 {

    public static void main(String[] args) {
        System.out.println(uniquePaths(5,6));
    }


    public static int uniquePaths(int m, int n) {
        int[][] result = new int[m][n];
        for (int i=0;i<m;i++){
            result[0][i]=1;
        }
        for (int i =1;i<n;i++){
            result[i-1][0]=1;
        }
        for (int i =1 ;i<m;i++){
            for (int j=1;j<n;j++){
                result[i][j]=result[i][j-1]+result[i-1][j];
            }
        }
        return result[m-1][n-1];










        /*int[][] result = new int[m][n];

        // 第一列的解
        for (int i = 0; i < m; i++) {
            result[i][0] = 1;
        }

        // 第一行的解
        for (int i = 1; i < n; i++) {
            result[0][i] = 1;
        }

        // 其它位置的解
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                result[i][j] = result[i - 1][j] + result[i][j - 1];
            }
        }

        // 所求的解
        return result[m - 1][n - 1];*/
    }
}
