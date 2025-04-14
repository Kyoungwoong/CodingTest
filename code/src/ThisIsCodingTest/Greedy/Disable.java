package ThisIsCodingTest.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Disable {
    private static int N;
    public static ArrayList<Integer> arrayList = new ArrayList<>();
    public static List<Integer> coins = new ArrayList<>();
    public static Set<Integer> bucket = new HashSet<>();

    public static void main(String[] args) throws IOException {
//        prev();
        oct28();
    }

    private static void oct28() throws IOException {
        // N개의 동전.
        // N개의 동전을 이용하여 만들 수 없는 양의 정수 금액중 최솟값.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
//        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(coins);
        /*
        0, 0-1, 0-1-2, 0-1-2...N/
        1,
        N
         */
//        recursive();
        int target = 1;
        int i = 0;
        while (true) {
            if (target < coins.get(i)) {
                System.out.println(target);
                break;
            }
            target += coins.get(i);
            System.out.println("target = " + target);
            i++;
        }
    }

    public static void recursive() {
        for (int i = 0; i < N; i++) {
            putCoin(i, coins.get(i));
        }
    }

    public static void putCoin(int idx, int addCoin) {
        bucket.add(addCoin);
        if (idx == N -1) {
            return;
        }
        putCoin(idx + 1, addCoin + coins.get(idx + 1));
    }


    private static void prev() throws IOException {
        // N개의 동전.
        // N개의 동전을 이용하여 만들 수 없는 양의 정수 금액중 최솟값.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int value = Integer.parseInt(st.nextToken());
            arrayList.add(value);
//            coin.put(value, coin.getOrDefault(value, 0) + 1);
//            System.out.println("coin.get(value) = " + coin.get(value));
        }
//        HashMap<Integer, Integer> copy;
//        for(Integer key : copy.keySet()){
//            System.out.println(key + ": " +copy.get(key));
//        }
        Collections.sort(arrayList);

        int target = 1;
        for (int i = 0; i < N; i++) {
            // 만들 수 없는 금액을 찾았을 때 반복 종료
            if (target < arrayList.get(i)) break;
            target += arrayList.get(i);
        }

        System.out.println(target);
    }
}
/*
5
3 2 1 1 9

3
3 5 7
 */
