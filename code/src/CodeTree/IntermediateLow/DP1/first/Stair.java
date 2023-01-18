package CodeTree.IntermediateLow.DP1.first;

import java.util.Scanner;

public class Stair {
    private static int n;
    private static int[] arr = new int[1001];

    public static void stair(){
        for(int i = 4; i <= n; i++){
            arr[i] = (arr[i-2] + arr[i-3]) % 10007;

        }
    }

    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        arr[0] = 1;
        arr[2] = 1;
        arr[3] = 1;

        stair();

        System.out.println(arr[n] % 10007);


    }
}