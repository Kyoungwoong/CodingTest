package Baekjoon.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<String> list = new ArrayList<>();
        Set<String> wordsSet = new HashSet<>();
        while (n-- > 0) {
            String cmd = br.readLine();
            if (wordsSet.contains(cmd)) {
                continue;
            }
            wordsSet.add(cmd);
            list.add(cmd);
        }

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1.length() == s2.length()) {
                    return s1.compareToIgnoreCase(s2);
                }
                return s1.length() - s2.length();
            }
        });

        for (String s : list) {
            System.out.println(s);
        }
    }
}
