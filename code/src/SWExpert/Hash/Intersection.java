package SWExpert.Hash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.StringTokenizer;

class Intersection {
    private static int a, b, cnt = 0;
    private static HashSet<String> hash = new HashSet<>();

    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("SW_Testcase/Hash/intersection.txt"));
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            hash.clear();
            cnt = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < a; i++){
                hash.add(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < b; i++){
                if (hash.contains(st.nextToken())) {
                    cnt++;
                }
            }
            sb.append("#" + test_case + " " + cnt + "\n");
        }
        System.out.println(sb);
    }
}