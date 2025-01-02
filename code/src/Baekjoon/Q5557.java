package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q5557 {
    // https://www.acmicpc.net/problem/5557
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Long[] arr = new Long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        List<Map<Long, Long>> result = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            result.add(new HashMap<>());
        }
        result.get(0).put(arr[0], 1L);

        for (int i = 1; i < N - 1; i++) {
            for (Map.Entry<Long, Long> entry : result.get(i - 1).entrySet()) {
                long prev = entry.getKey();
                long value = entry.getValue();

                long nextP = prev + arr[i];
                long nextN = prev - arr[i];

                // 범위 체크 및 값 갱신
                if (nextP >= 0 && nextP <= 20) {
                    result.get(i).put(nextP, result.get(i).getOrDefault(nextP, 0L) + value);
                }
                if (nextN >= 0 && nextN <= 20) {
                    result.get(i).put(nextN, result.get(i).getOrDefault(nextN, 0L) + value);
                }
            }
        }

        long target = arr[N - 1];
        long cnt = result.get(N - 2).getOrDefault(target, 0L);
        System.out.println(cnt);
    }
}
/*
11
8 3 2 4 8 7 2 4 0 8 8
ans: 10
 */