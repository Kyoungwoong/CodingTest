package Baekjoon.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Q18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        TreeMap<Integer, Integer> list = new TreeMap();
        ArrayList<Integer> arr = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr.add(num);
            list.put(num, 1);
        }
        int cnt = 0;
        int idx = 0;
        for (int num: list.keySet()) {
            idx = list.get(num);
            list.put(num, cnt);
            cnt += idx;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.size(); i++) {
            sb.append(list.get(arr.get(i)) + " ");
        }
        System.out.println(sb);

    }
}
