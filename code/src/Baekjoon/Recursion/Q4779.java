package Baekjoon.Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q4779 {
    static int N;
    static char c[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str;
        while((str = br.readLine()) != null && !str.isEmpty()){
            N = Integer.parseInt(str);

            int cnt = (int) Math.pow(3,N);
            c = new char[cnt];

            for (int i = 0; i < cnt; i++) c[i] = '-';

            dfs(0,cnt);

            for (int i = 0; i < cnt; i++) {
                sb.append(c[i]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int start,int length){
        if(length < 3) {
            return;
        }

        for (int i = start + length / 3; i < start + length / 3 * 2; i++) {
            c[i] = ' ';
        }

        dfs(start,length/3);

        dfs(start+length/3*2,length/3);
    }
}
