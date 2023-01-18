package CodeTree.IntermediateLow.DP1.fourth;

import java.util.Scanner;

public class SUM125 {
    private static int n, MOD = 10007, ans = 0;
    private static int[] arr = new int[1001];

    public static void sum125(int target){
        arr[0] = 1;
        for(int i = 1; i <= target; i++){
            for(int j = 0; j < 3; j++){
                if(j == 0 && i - 1 >= 0){
                    arr[i] = (arr[i] + arr[i - 1]) % MOD;
                }else if(j == 1 && i - 2 >= 0){
                    arr[i] = (arr[i] + arr[i - 2]) % MOD;
                }else if(j == 2 && i - 5 >= 0){
                    arr[i] = (arr[i] + arr[i - 5]) % MOD;
                }

            }
        }

    }

    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        sum125(n);

        System.out.println(arr[n]);
    }
}