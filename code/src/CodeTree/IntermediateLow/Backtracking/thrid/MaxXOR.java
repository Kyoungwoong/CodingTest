package CodeTree.IntermediateLow.Backtracking.thrid;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class MaxXOR {
    private static int n,m, max = 0;
    private static int[] arr = new int[20];

    public static void findMaxXor(int cnt, int idx, int curVal){
        if(cnt == m){
            max = Math.max(max, curVal);
            return;
        }
        if(idx == n) {
            return;
        }
        findMaxXor(cnt, idx+1, curVal);
        findMaxXor(cnt+1, idx+1, curVal ^ arr[idx]);
    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        findMaxXor(0, 0, 0);
        System.out.println(max);
    }
}