package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class English implements Comparable<English> {
    String word;
    int cnt;

    public English(String word) {
        this.word = word;
        this.cnt = 1;
    }

    @Override
    public int compareTo(English e) {
        if (this.cnt == e.cnt) {
            if (this.word.length() == e.word.length()) {
                return this.word.compareTo(e.word);
            } else {
                return e.word.length() - this.word.length();
            }
        } else {
            return e.cnt - this.cnt;
        }

    }
}

public class Q20920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, English> wordMap = new HashMap<>();
        ArrayList<English> words = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            if (word.length() < M) continue;

            if (wordMap.containsKey(word)) {
                wordMap.get(word).cnt++;
                continue;
            }
            English e = new English(word);
            words.add(e);
            wordMap.put(word, e);
        }

        Collections.sort(words);
        for (English e : words) {
            sb.append(e.word).append('\n');
        }
        System.out.println(sb);
    }
}
