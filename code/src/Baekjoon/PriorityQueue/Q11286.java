package Baekjoon.PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Q11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int q = Integer.parseInt(br.readLine());
            switch (q) {
                case 0:
                    if(pq.isEmpty()){
                        sb.append(0 + "\n");
                    } else {
                        int key = pq.poll();
//                        System.out.println(key);
                        if (map.containsKey(key)) {
                            if (map.get(key) != 0) {
                                sb.append((-1 * key) + "\n");
                                map.put(key, map.getOrDefault(key, 0) - 1);
                                continue;
                            }
                        }
                        sb.append(key + "\n");

                    }
                    break;
                default:
                    if (q < 0) {
                        q = -q;
                        map.put(q, map.getOrDefault(q, 0) + 1);
                    }
                    pq.add(q);
                    break;
            }
        }
        System.out.println(sb);

    }
}
