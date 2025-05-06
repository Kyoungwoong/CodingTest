package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q14891 {
    static class Cycle {
        public char[] magnets;

        public Cycle(char[] magnets) {
            this.magnets = magnets;
        }

        public void rotate(int dir) {
            if (dir == 1) rotateClockwise();
            else rotateCounterClockwise();
        }

        public void rotateClockwise() {
            char temp = magnets[7];
            for (int i = 7; i > 0; i--) {
                magnets[i] = magnets[i - 1];
            }
            magnets[0] = temp;
        }

        public void rotateCounterClockwise() {
            char temp = magnets[0];
            for (int i = 0; i < 7; i++) {
                magnets[i] = magnets[i + 1];
            }
            magnets[7] = temp;
        }
    }

    private static Cycle[] cycles = new Cycle[4];
    private static boolean[] visited;
    private static final int RIGHT = 2, LEFT = 6;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            String str = br.readLine();
            char[] magnets = str.toCharArray();
            Cycle cycle = new Cycle(magnets);
            cycles[i] = cycle;
        }

        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            visited = new boolean[4];
            changeCycle(idx, dir);
        }

        int sum = 0, std = 1;
        for (int i = 0; i < 4; i++) {
            if (cycles[i].magnets[0] == '1') {
                sum += std;
            }
            std *= 2;
        }
        System.out.println(sum);
    }

    private static void changeCycle(int idx, int dir) {
        int[] rotateDir = new int[4];
        rotateDir[idx] = dir;

        for (int i = idx - 1; i >= 0; i--) {
            if (cycles[i].magnets[RIGHT] != cycles[i + 1].magnets[LEFT]) {
                rotateDir[i] = -rotateDir[i + 1];
            } else break;
        }

        for (int i = idx + 1; i < 4; i++) {
            if (cycles[i - 1].magnets[RIGHT] != cycles[i].magnets[LEFT]) {
                rotateDir[i] = -rotateDir[i - 1];
            } else break;
        }

        for (int i = 0; i < 4; i++) {
            if (rotateDir[i] != 0) {
                cycles[i].rotate(rotateDir[i]);
            }
        }
    }
}
