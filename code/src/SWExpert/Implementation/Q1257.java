package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Q1257 {
    private static int tc, K;
    private static TreeSet<String> dict;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= tc; t++) {
            K = Integer.parseInt(br.readLine());
            String word = br.readLine();
            dict = new TreeSet<>();

            int size = word.length();

            for (int i = 0; i < size; i++) {
                for (int j = i+1; j <= size; j++) {
//                    System.out.println(word.substring(i, j));
                    dict.add(word.substring(i, j));
                }
            }

            String ans = null;
            int i = 1;
            while (true) {
                String std = dict.pollFirst();
//                System.out.println("i: " + i + "\t" + std);
                if (i == K) {
                    ans = std;
                    break;
                }
                i++;
            }
            sb.append("#").append(t).append(" ").append(ans == null ? "none" : ans).append("\n");
        }
        System.out.println(sb);
    }
}

/*
3
7
love
10
food
281
tfbpqyuekmsonzgdlvjhcawxr
 */