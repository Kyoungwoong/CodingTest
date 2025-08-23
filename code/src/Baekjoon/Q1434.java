package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 빈박스 N개, 박스에 책 M개 넣음
 *
 * 1. 현재 책이 현재 박스에 들어가지 않으면, 3번으로 간다. 아니면 2번으로 간다.
 * 2. 현재 책을 현재 박스에 넣는다. 다음 책을 손에 집고 1번으로 간다.
 * 3. 현재 박스를 다른 쪽으로 치운 다음에, 테이프로 못 열게 봉인한다.
 *    다음 박스를 앞으로 가져오고 1번으로 간다.
 */
public class Q1434 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int[] boxes;

    /**
     * 1. 현재 책이 현재 박스에 들어가지 않으면, 3번으로 간다. 아니면 2번으로 간다.
     * 2. 현재 책을 현재 박스에 넣는다. 다음 책을 손에 집고 1번으로 간다.
     * 3. 현재 박스를 다른 쪽으로 치운 다음에, 테이프로 못 열게 봉인한다.
     *    다음 박스를 앞으로 가져오고 1번으로 간다.
     */
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boxes = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            boxes[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int idx = 0, ans = 0;
        for (int i = 0; i < M; i++) {
            int book = Integer.parseInt(st.nextToken());
            if (boxes[idx] < book) {
                // 현재 박스를 다른 쪽으로 치운 다음에, 테이프로 못 열게 봉인한다.
                // 다음 박스를 앞으로 가져오고 1번으로 간다.
                while (boxes[idx] < book) {
                    idx++;
                }
            }
            // 현재 책을 현재 박스에 넣는다. 다음 책을 손에 집고 1번으로 간다.
            boxes[idx] -= book;
        }

        for (int i = 0; i < N; i++) {
            ans += boxes[i];
        }
        System.out.println(ans);
    }

}
