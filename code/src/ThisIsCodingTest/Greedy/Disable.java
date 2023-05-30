package ThisIsCodingTest.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Disable {
    private static int N;
    public static ArrayList<Integer> arrayList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
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
