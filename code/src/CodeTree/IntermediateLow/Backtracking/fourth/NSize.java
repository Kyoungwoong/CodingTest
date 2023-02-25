package CodeTree.IntermediateLow.Backtracking.fourth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class NSize {
    private static int n;
    private static ArrayList<Integer> ans = new ArrayList<>();
    private static boolean[] visited = new boolean[9];

    public static void print(){
        for(int i = 0; i <(int)ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
        System.out.println();
    }

    public static void choose(int cnt){
        if(cnt == n) {
            print();
            return;
        }

        for(int i = 1; i <= n; i++) {
            if(visited[i]){
                continue;
            }

            visited[i] = true;
            ans.add(i);

            choose(cnt+1);

            ans.remove(ans.size()-1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        choose(0);
    }
}