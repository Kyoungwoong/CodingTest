package SWExpert.Heap;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class MidValue {

    public static ArrayList<Integer> heap;
    private static int n, a, ans, MOD = 20171109;


    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("SW_Testcase/Heap/mid_value.txt"));
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(br.readLine());


        for(int test_case = 1; test_case <= T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());


        }
    }
}