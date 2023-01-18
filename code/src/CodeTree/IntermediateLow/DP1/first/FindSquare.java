package CodeTree.IntermediateLow.DP1.first;

import java.util.Scanner;

public class FindSquare {
    private static int n;
    private static int[] arr = new int[1001];

    public static void find(){
        for(int i = 4; i <= n; i++){
            arr[i] = (arr[i-1] + arr[i-2]) % 10007;
        }
    }

    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 3;

        find();

        System.out.println(arr[n]);
    }
}