package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Q9506 {

    public static boolean check(ArrayList<Integer> arr, int N) {
        int std = 0;
        for (int i = 0; i < arr.size(); i++) {
            std += arr.get(i);
        }
        if (std == N) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            int N = Integer.parseInt(br.readLine());
            ArrayList<Integer> arr = new ArrayList<>();
            if (N == -1) {
                System.out.println(sb);
                break;
            }
            for (int i = 1; i < N; i++) {
                if (N % i == 0) {
                    arr.add(i);
                }
            }
            if (check(arr, N)) {
                sb.append(N + " = ");
                for (int i = 0; i < arr.size(); i++) {
                    if (i == arr.size() - 1) {
                        sb.append(arr.get(i) + "\n");
                    } else {
                        sb.append(arr.get(i) + " + ");
                    }
                }
            } else {
                sb.append(N + " is NOT perfect.\n");
            }
        }
    }
}
