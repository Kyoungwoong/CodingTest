package CodeTree.IntermediateLow.DP1.first;

import java.util.Scanner;

public class Fibo {
    private static int n;
    private static int[] arr = new int[46];

    public static void fibo(int cnt){

        if(cnt <= 2){
            arr[cnt] = 1;
        }else{
            arr[cnt] = arr[cnt-2] + arr[cnt-1];
        }

        if(cnt == n){
            System.out.println(arr[cnt]);
            System.exit(0);
        }
        fibo(cnt+1);
    }
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        fibo(1);
    }
}