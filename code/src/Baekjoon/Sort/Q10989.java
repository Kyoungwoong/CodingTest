package Baekjoon.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q10989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> dupMap = new HashMap<>();
        while (n-- > 0) {
            int num = Integer.parseInt(br.readLine());
            if (!dupMap.containsKey(num)) {
                list.add(num);
            }
            dupMap.put(num, dupMap.getOrDefault(num, 0) + 1);
        }

        Collections.sort(list);
        StringBuilder sb = new StringBuilder();

        for (int key : list) {
            int cnt = dupMap.get(key);
            for (int i = 0; i < cnt; i++) {
                sb.append(key).append("\n");
            }
        }
        System.out.println(sb);
    }
}
