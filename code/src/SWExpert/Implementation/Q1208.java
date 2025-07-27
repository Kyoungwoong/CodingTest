package SWExpert.Implementation;

import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1208 {
    private static int tc = 10, K;
    private static TreeMap<Integer, Integer> boxMap;
    private static Set<Integer> boxSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int t = 1; t <= tc; t++) {
            K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            boxMap = new TreeMap<>();
            for (int i = 0; i < 100; i++) {
                int height = Integer.parseInt(st.nextToken());
                boxMap.put(height, boxMap.getOrDefault(height, 0) + 1);
            }

            for (int i = 0; i < K; i++) {
                int small = boxMap.firstKey();
                int large = boxMap.lastKey();

                if (large - small == 0 || large - small == 1) {
                    break;
                }

                if (boxMap.get(small) != 1) {
                    boxMap.put(small, boxMap.get(small) - 1);
                } else {
                    boxMap.remove(small);
                }
                small += 1;
                boxMap.put(small, boxMap.getOrDefault(small, 0) + 1);

                if (boxMap.get(large) != 1) {
                    boxMap.put(large, boxMap.get(large)-1);
                } else {
                    boxMap.remove(large);
                }
                large -= 1;
                boxMap.put(large, boxMap.getOrDefault(large, 0) + 1);
            }

            sb.append("#").append(t).append(" ")
                    .append(boxMap.lastKey() - boxMap.firstKey()).append("\n");
        }
        System.out.println(sb);
    }
}
