package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q10824 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        long A = 0, B = 0;
        String strA = "", strB = "";
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < 4; idx++) {
            if (idx == 0 || idx == 1) {
                strA += st.nextToken();
                if (idx == 1) {
                    A = Long.parseLong(strA);
                }
            } else {
                strB += st.nextToken();
                if (idx == 3) {
                    B = Long.parseLong(strB);
                }
            }
        }

        System.out.println(A + B);
    }
}
