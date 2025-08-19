package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 적정체중
 *  1. 펫의 실제 체중이 적정 체중의 1/2 초과하면서 적정 체중의 2배 미만이면 행복
 *  2. 실제 체중이 0 이하일 경우 사망
 *  3. 그 외의 경우 펫은 슬픔
 * 2. 동작
 *  1. E와 숫자를 입력해 펫을 운동
 *    1. 입력된 숫자만큼(n) 실제 체중 n이 감소
 *  2. F와 숫자를 입력해 먹이를 줄 수 있음.
 *    1. 입력된 숫자만큼 펫에게 먹이를 주면 펫의 실제 체중이 n만큼 증가
 */
public class Q1362 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final String HAPPY = ":-)";
    static final String SAD = ":-(";
    static final String DEAD = "RIP";

    static int test, o, w; // o: 적정체중, w: 실제체중

    public static void main(String[] args) throws IOException {
        test = 0;

        while (true) {
            test++;
            st = new StringTokenizer(br.readLine());

            o = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            if (o == 0 && w == 0) {
                break;
            }

            while (true) {
                st = new StringTokenizer(br.readLine());

                String command = st.nextToken();
                int n = Integer.parseInt(st.nextToken());

                if (command.equals("#")) {
                    judge();
                    break;
                } else if (command.equals("F")) {
                    w += n;
                } else {
                    w -= n;
                    if (w <= 0) {
                        sb.append(String.format("%d %s\n", test, DEAD));

                        while (true) {
                            st = new StringTokenizer(br.readLine());
                            String endCommand = st.nextToken();
                            if (endCommand.equals("#")) {
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * 1. 적정체중
     *  1. 펫의 실제 체중이 적정 체중의 1/2 초과하면서 적정 체중의 2배 미만이면 행복
     *  2. 실제 체중이 0 이하일 경우 사망
     *  3. 그 외의 경우 펫은 슬픔
     */
    private static void judge() {
        if (o / 2 < w && w < 2 * o) {
            sb.append(String.format("%d %s\n", test, HAPPY));
        } else if (w == 0) {
            sb.append(String.format("%d %s\n", test, DEAD));
        } else {
            sb.append(String.format("%d %s\n", test, SAD));
        }
    }
}
