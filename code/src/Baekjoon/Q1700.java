package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1700 {
    // https://www.acmicpc.net/problem/1700
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 멀티탭 구멍 수
        int K = Integer.parseInt(st.nextToken()); // 전기 용품 사용 횟수
        int[] schedule = new int[K]; // 사용 순서 배열

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            schedule[i] = Integer.parseInt(st.nextToken());
        }

        Set<Integer> consent = new HashSet<>(); // 현재 멀티탭에 꽂혀 있는 플러그
        int ans = 0; // 플러그를 뽑는 횟수

        for (int i = 0; i < K; i++) {
            int appliance = schedule[i];

            if (consent.contains(appliance)) {
                continue;
            }

            if (consent.size() < N) {
                consent.add(appliance);
                continue;
            }

            // 멀티탭에 자리가 없는 경우 교체 필요
            ans++;
            int toRemove = -1;
            int latestUse = -1; // 가장 나중에 사용되거나 더 이상 사용되지 않는 플러그

            // 멀티탭에 꽂혀 있는 플러그 중 교체 대상 찾기
            for (int plug : consent) {
                int nextUse = Integer.MAX_VALUE; // 다음 사용 시점 (사용되지 않으면 매우 큰 값)
                for (int j = i + 1; j < K; j++) {
                    if (schedule[j] == plug) {
                        nextUse = j;
                        break;
                    }
                }
                // 가장 나중에 사용되거나 사용되지 않는 플러그 선택
                if (nextUse > latestUse) {
                    latestUse = nextUse;
                    toRemove = plug;
                }
            }

            consent.remove(toRemove);
            consent.add(appliance);
        }

        System.out.println(ans);
    }
}
