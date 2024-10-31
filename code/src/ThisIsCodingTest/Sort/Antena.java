package ThisIsCodingTest.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Antena {
    private static int N;
    private static int[] town;

    public static void main(String[] args) throws IOException {
//        prev();
        oct31();
    }

    private static void oct31() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        town = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            town[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(town);
        int mid = (town[(N - 1) / 2]);

        System.out.println("mid = " + mid);
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        town = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            town[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(town);

        int sum = 0;
        if (N % 2 == 1) {
            int mid = town[N / 2];
            for (int i = 0; i < N; i++) {
                sum += Math.abs(mid - town[i]);
            }
            System.out.println(sum);
        }else{
            int mid = town[N / 2];
            for (int i = 0; i < N; i++) {
                sum += Math.abs(mid - town[i]);
            }
            System.out.println("sum = " + sum + " "+ mid);

            int ans = 0;
            mid = town[N / 2 - 1];
            for (int i = 0; i < N; i++) {
                ans += Math.abs(mid - town[i]);
            }
            System.out.println("ans = " + ans  + " " + mid);
            System.out.println(Math.min(sum, ans));
        }
    }
}
/*
4
5 1 7 9
--- 5
 */
