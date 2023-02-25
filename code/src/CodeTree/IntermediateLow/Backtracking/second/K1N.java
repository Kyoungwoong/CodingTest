package CodeTree.IntermediateLow.Backtracking.second;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class K1N {
    private static int k, n;
    private static ArrayList<Integer> ans = new ArrayList<>();

    public static void print(){
        for(int i = 0; i <(int)ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
        System.out.println();
    }

    public static void choose(int num) {
        if(num == n+1) {
            print();
            return;
        }

        for(int i = 1; i <= k; i++) {
            int size = ans.size();
            if(size >= 2 && ans.get(size-1) == i && ans.get(size-2) == i ) {
                continue;
            }
            ans.add(i);
            choose(num + 1);
            ans.remove(ans.size()-1);
        }

    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        choose(1);
    }
}