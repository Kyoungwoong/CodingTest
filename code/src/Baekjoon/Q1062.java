package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1062 {
    // https://www.acmicpc.net/problem/1062
    private static int ans = 0;
    private static List<Set<Character>> words = new ArrayList<>();
    private static Set<Character> known = new HashSet<>();
    private static boolean[] visited = new boolean[26];

    public static void getAlphabet(int std, int K, int idx) {
        if (std == K) {
            int result = 0;
            for (Set<Character> word : words) {
                boolean canRead = true;
                for (char c : word) {
                    if (!known.contains(c)) {
                        canRead = false;
                        break;
                    }
                }
                if (canRead) {
                    result++;
                }
            }
            ans = Math.max(ans, result);
            return;
        }

        for (int i = idx; i < 26; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            known.add((char) ('a' + i));

            getAlphabet(std + 1, K, i+1);

            known.remove((char) ('a' + i));
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            words.add(new HashSet<>());
            String word = br.readLine();
            for (int j = 0; j < word.length(); j++) {
                if (word.charAt(j) != 'a' ||
                        word.charAt(j) != 'n' ||
                        word.charAt(j) != 't' ||
                        word.charAt(j) != 'i' ||
                        word.charAt(j) != 'c') {
                    words.get(i).add(word.charAt(j));
                }
            }
        }

        if (K < 5) {
            System.out.println(0);
            return;
        }

        // 필수 문자
        known.add('a');
        known.add('n');
        known.add('t');
        known.add('i');
        known.add('c');

        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        getAlphabet(5, K, 0);

        System.out.println(ans);
    }
}
