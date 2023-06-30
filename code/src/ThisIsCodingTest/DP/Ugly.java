package ThisIsCodingTest.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Ugly {
    private static int n;
    private static boolean[] numbers = new boolean[1000001];
    private static ArrayList<Integer> arr = new ArrayList<>();

    public static void init() {
        numbers[1] = true;
        numbers[2] = true;
        numbers[3] = true;
        numbers[5] = true;

        for (int i = 4; i <= 1000000; i++) {
            if(numbers[i]) continue;
            if (i % 2 == 0) {
                numbers[i] = true;
            } else if (i % 3 == 0) {
                if (numbers[i / 3]) {
                    numbers[i] = true;
                }
            } else if (i % 5 == 0) {
                if (numbers[i / 5]) {
                    numbers[i] = true;
                }
            }
        }
    }

    public static boolean isUglyNum(int num) {
        if(numbers[num]){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        init();

        for (int i = 1; i <= 100000; i++) {
            if (isUglyNum(i)) {
                arr.add(i);
            }
        }

        for (int i = 0; i < 10; i++) {
            System.out.print(arr.get(i) + " ");
        }
        System.out.println();

        System.out.println(arr.get(n-1));
    }
}
/*
10
--- 12

4
--- 4
 */
