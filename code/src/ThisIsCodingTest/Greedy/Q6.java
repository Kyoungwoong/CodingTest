package ThisIsCodingTest.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Dish implements Comparable<Dish> {
    int idx, quan;

    public Dish(int idx, int quan) {
        this.idx = idx;
        this.quan = quan;
    }

    @Override
    public int compareTo(Dish dish) {
        return this.quan - dish.quan;
    }
}

public class Q6 {
    private static int N, K;
    private static ArrayList<Dish> food_times = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            food_times.add(new Dish(i, Integer.parseInt(st.nextToken())));
        }
        Collections.sort(food_times); // 1 2 3

        int remain = K / N;
        while (remain != 0) {
            if (food_times.get(0).quan > remain) {
                for (int i = 0; i < N; i++) {
                    food_times.get(i).quan -= remain;
                }
                K -= remain * N;
            } else {
                int cnt = food_times.get(0).quan;
                for (int i = 0; i < N; i++) {
                    food_times.get(i).quan -= cnt;
                }
                K -= cnt * N;
                food_times.remove(0);
                N--;
            }
//            Collections.sort(food_times);
            if (N == 0) {
                break;
            }
            remain = K / N;
        }

        Collections.sort(food_times, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return o1.idx - o2.idx;
            }
        });

        if (N == 0) {
            System.out.println(-1);
        }else{
            System.out.println(food_times.get(K % N).idx);
        }
    }
}
/*
3 5
3 1 2

3 6
3 1 2
 */
