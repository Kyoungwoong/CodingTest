package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 티셔츠는 S, M, L, XL, XXL, 그리고 XXXL의 6가지 사이즈가 있습니다.
 *      티셔츠는 같은 사이즈의 T장 묶음으로만 주문할 수 있습니다.
 * 펜은 한 종류로, P자루씩 묶음으로 주문하거나 한 자루씩 주문할 수 있습니다.
 */
public class Q30802 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, T, P;
    static int[] peoples = new int[6];

    public static void main(String[] args) throws IOException {
        input();
        orderTShirts();
        orderPen();
        System.out.println(sb.toString());
    }

    private static void orderTShirts() {
        int ans = 0;
        for (int idx = 0; idx < 6; idx++) {
            if (peoples[idx] <= T && peoples[idx] != 0) {
                ans++;
            } else {
                ans += peoples[idx] / T;
                if (peoples[idx] % T != 0) {
                    ans++;
                }
            }
        }

        sb.append(ans).append("\n");
    }

    private static void orderPen() {
        int quote = N / P;
        int remain = N % P;
        sb.append(quote).append(" ").append(remain);
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < 6; idx++) {
            peoples[idx] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
    }
}
