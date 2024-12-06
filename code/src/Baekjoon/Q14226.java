package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q14226 {
    // https://www.acmicpc.net/problem/14226
    static class Imoticon {
        int cnt, time, copyCnt;

        public Imoticon(int cnt, int time, int copyCnt) {
            this.cnt = cnt;
            this.time = time;
            this.copyCnt = copyCnt;
        }
    }

    private static int S;

    public static void bfs() {
        Queue<Imoticon> q = new LinkedList<>();
        boolean[][] visited = new boolean[1001][1001];

        q.add(new Imoticon(1, 0, 0));
        visited[1][0] = true;

        while (!q.isEmpty()) {
            Imoticon imo = q.poll();

            if (imo.cnt == S) {
                System.out.println(imo.time);
                return;
            }

            if (!visited[imo.cnt][imo.cnt]) { // 클립보드 저장
                visited[imo.cnt][imo.cnt] = true;
                q.add(new Imoticon(imo.cnt, imo.time + 1, imo.cnt));
            }

            if (imo.copyCnt > 0 && imo.cnt + imo.copyCnt <= 1000 && !visited[imo.cnt + imo.copyCnt][imo.copyCnt]) { // 붙여넣기
                visited[imo.cnt + imo.copyCnt][imo.copyCnt] = true;
                q.add(new Imoticon(imo.cnt + imo.copyCnt, imo.time + 1, imo.copyCnt));
            }

            if (imo.cnt > 0 && !visited[imo.cnt - 1][imo.copyCnt]) { // 삭제
                visited[imo.cnt - 1][imo.copyCnt] = true;
                q.add(new Imoticon(imo.cnt - 1, imo.time + 1, imo.copyCnt));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());

        bfs();
    }
}
