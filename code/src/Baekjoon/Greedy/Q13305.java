package Baekjoon.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Q13305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] dist = new long[N - 1];
        long[] costs = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            int length = Integer.parseInt(st.nextToken());
            dist[i] = length;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int cost = Integer.parseInt(st.nextToken());
            costs[i] = cost;
        }

        long sum = 0;
        long min = costs[0];
        for (int i = 0; i < N - 1; i++) {
            if(costs[i] < min) {
                min = costs[i];
            }
            sum += (min * dist[i]);
        }
        System.out.println(sum);

    }
}
