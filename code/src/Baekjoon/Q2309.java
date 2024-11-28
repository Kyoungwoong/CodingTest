package Baekjoon;

import java.util.*;

public class Q2309 {
    // https://www.acmicpc.net/problem/2309
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] dwarfs = new int[9];
        int totalSum = 0;

        // 난쟁이 키 입력 및 합 계산
        for (int i = 0; i < 9; i++) {
            dwarfs[i] = sc.nextInt();
            totalSum += dwarfs[i];
        }

        // 두 명의 난쟁이를 찾기
        outer:
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (totalSum - (dwarfs[i] + dwarfs[j]) == 100) {
                    dwarfs[i] = -1; // 제외 표시
                    dwarfs[j] = -1;
                    break outer;
                }
            }
        }

        // 나머지 7명 출력
        List<Integer> result = new ArrayList<>();
        for (int dwarf : dwarfs) {
            if (dwarf != -1) {
                result.add(dwarf);
            }
        }
        Collections.sort(result);

        for (int height : result) {
            System.out.println(height);
        }
    }
}
