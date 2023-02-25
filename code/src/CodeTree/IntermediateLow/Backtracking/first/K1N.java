package CodeTree.IntermediateLow.Backtracking.first;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class K1N {
    private static int k, n;
    private static ArrayList<Integer> answer = new ArrayList<>();

    public static void print(){
        for(int i = 0; i < answer.size(); i++) {
            System.out.print(answer.get(i) + " ");
        }
        System.out.println();
    }

    public static void choose(int num) {
        if(num == n+1) {
            print();
            return;
        }

        for(int i = 0; i < k; i ++) {
            answer.add(1 + i);
            choose(num+1);
            answer.remove(answer.size()-1);
        }

    }
    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        // 반복
        choose(1);
    }
}