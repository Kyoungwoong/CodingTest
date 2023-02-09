package SWExpert.Hash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.io.FileInputStream;

// https://github.com/SJaeYub/algo_practice/blob/1b7c1f0c1366646a34232e45506dde3a5aff08eb/src/swea4038/Solution.java
class SubString {
    private static int cnt;
    private static String b, s;
    private static HashMap<String, Integer> hash = new HashMap<>();
    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("SW_Testcase/Hash/substring.txt"));
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            hash.clear();
            cnt = 0;
            b = br.readLine();
            s = br.readLine();

            int bLen = b.length();
            int sLen = s.length();
            for(int i = 0; i <= bLen-sLen; i++){
                String push = b.substring(i, i+sLen);
                hash.put(push, hash.getOrDefault(push, 0) + 1);
            }
            cnt = hash.get(s);
            sb.append("#" + test_case + " " + cnt +"\n");
        }
        System.out.println(sb);
    }
}