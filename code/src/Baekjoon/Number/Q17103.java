package Baekjoon.Number;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q17103 {
    public static boolean[] prime = new boolean[1000001];
    public static void isPrime() {
        prime[0] = prime[1] = false;
        for (int i = 2; i <= 1000000; i++) {
            prime[i] = true;
        }
        for(int i = 2; i <= 1000000; i++){
            if(prime[i] == false) continue; //소수가 아니라면 continue

            for(int j = 2*i; j <= 1000000; j+=i)
                prime[j] = false; //소수의 배수는 소수를 약수로 가지므로 제외
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        isPrime();
        for (int i = 0; i < T; i++) {
            int cnt = 0;
            int N = Integer.parseInt(br.readLine());
            boolean[] visited = new boolean[N];
            for (int j = 2; j <= N/2; j++) {
                if (!visited[j] && prime[j] && prime[N-j]) {
//                    System.out.println(j + " " + (N-j));
                    visited[j] = true;
                    visited[N-j] = true;
                    cnt++;
                }
            }
            sb.append(cnt + "\n");
        }
        System.out.println(sb);

    }
}
