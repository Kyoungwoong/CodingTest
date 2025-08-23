package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 세준: N명의 병사
 * 세비: M명의 병사
 *
 * 각 전투에서 살아있는 병사 중 제일 약한 병사가 죽는다.
 *   만약 제일 약한 병사가 여러 명이고, 제일 약한 병사가 모두 같은 편에 있다면,
 *   그 중에 한 명이 임의로 선택되어 죽는다.
 * 제일 약한 병사가 여러 명이고, 양 편에 모두 있다면,
 *   세비의 제일 약한 병사 중 한 명이 임의로 선택되어 죽는다.
 */
public class Q1524 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder ans = new StringBuilder();

    static int testCase, N, M;
    static PriorityQueue<Integer> sj, sb;

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine());

        for (int test = 1; test <= testCase; test++) {
            input();

            int winner = play();

            ans.append(winner == 0 ? "S" : "B").append("\n");
        }
        System.out.println(ans.toString());
    }

    private static int play() {
        while (sj.size() + sb.size() != 1) {
            if (sj.size() == 0 || sb.size() == 0) {
                break;
            }

            int seJoonWarrior = sj.poll();
            int seBiWarrior = sb.poll();
            if (seJoonWarrior == seBiWarrior || seJoonWarrior > seBiWarrior) {
                sj.add(seJoonWarrior);
            } else if (seJoonWarrior < seBiWarrior) {
                sb.add(seBiWarrior);
            }
        }

        if (sj.size() == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    private static void input() throws IOException {
        br.readLine();
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sj = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sj.add(Integer.parseInt(st.nextToken()));
        }

        sb = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            sb.add(Integer.parseInt(st.nextToken()));
        }
    }
}
