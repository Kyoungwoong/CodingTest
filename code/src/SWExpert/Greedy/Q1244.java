package SWExpert.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1244 {
    private static int testCase, ans, changeCnt;
    private static char[] bonus;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            bonus = st.nextToken().toCharArray();
            changeCnt = Integer.parseInt(st.nextToken());

            ans = Integer.MIN_VALUE;
//            exchange(0);
            System.out.println("=====================");
            exchange(0, 0);

            sb.append("#").append(t).append(" ").append(ans);
            if (t != testCase) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void exchange(int pos, int cnt) {
        if (cnt == changeCnt) {
            int num = Integer.parseInt(new String(bonus));
            ans = Math.max(ans, num);
            return;
        }

        int len = bonus.length;
        for (int i = pos; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                printWithIndent(i, j, cnt);
                swap(i, j);

                exchange(i, cnt + 1);

                swap(i, j);
            }
        }
    }

    private static void exchange(int cnt) {
        if (cnt == changeCnt) {
            int num = Integer.parseInt(new String(bonus));
            ans = Math.max(ans, num);
            return;
        }

        int len = bonus.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                printWithIndent(i, j, cnt);
                // swap
                swap(i, j);

                exchange(cnt + 1);

                // backtrack
                swap(i, j);
            }
        }
    }

    private static void swap(int i, int j) {
        char temp = bonus[i];
        bonus[i] = bonus[j];
        bonus[j] = temp;
    }

    private static void printWithIndent(int pos, int std, int depth) {
        for (int i = 0; i < depth; i++) System.out.print("\t");
        System.out.println("(" + pos + ", " + std + ")");
    }
}

/*
3
123 1
2737 1
32888 2
 */