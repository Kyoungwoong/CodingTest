package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q10886 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String CUTE = "Junhee is cute!", NOT_CUTE = "Junhee is not cute!";

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int cute = 0, notCute = 0;
        for (int idx = 0; idx < N; idx++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 1) {
                cute++;
            } else {
                notCute++;
            }
        }

        System.out.println(cute > notCute ? CUTE : NOT_CUTE);
    }
}
