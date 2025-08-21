package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Q1371 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static Map<Character, Integer> alphabetMap = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        init();

        String line;
        // EOF까지 모든 줄 읽기
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) continue;
            line = line.toLowerCase();

            for (int k = 0; k < line.length(); k++) {
                char cur = line.charAt(k);
                if ('a' <= cur && cur <= 'z') {
                    alphabetMap.put(cur, alphabetMap.get(cur) + 1);
                }
            }
        }

        check();
        System.out.println(sb.toString());
    }

    private static void check() {
        int max = 0;

        for (Character alphabet : alphabetMap.keySet()) {
//            System.out.println(alphabet + ": " + alphabetMap.get(alphabet));
            if (alphabetMap.get(alphabet) > max) {
                max = alphabetMap.get(alphabet);
                sb = new StringBuilder();
                sb.append(alphabet);
            } else if (alphabetMap.get(alphabet) == max && max != 0) {
                sb.append(alphabet);
            }
        }

    }

    private static void init() {
        for (int idx = 0; idx < 26; idx++) {
            alphabetMap.put((char) ('a' + idx), 0);
        }
    }
}
