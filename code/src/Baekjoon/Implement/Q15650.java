package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 *
 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
 * 고른 수열은 오름차순이어야 한다.
 */
public class Q15650 {
    private static int N, M;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dfs(1, 0, "");

        System.out.println(sb.toString());
    }

    private static void dfs(int pos, int size, String appendix) {
        if (size == M) {
            sb.append(appendix + "\n");
            return;
        }

        for (int i = pos; i <= N; i++) {
            String nextStr = appendix.length() == 0 ? String.valueOf(i) : appendix + " " + i;
            dfs(i + 1, size + 1, nextStr);
        }
    }
}
