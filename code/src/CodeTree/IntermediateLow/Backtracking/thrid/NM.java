package CodeTree.IntermediateLow.Backtracking.thrid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class NM {
    private static int n, m;
    private static ArrayList<Integer> ans = new ArrayList<>();

    public static void print(){
        for(int i = 0; i <(int)ans.size(); i++) {
            System.out.print(ans.get(i)+" ");
        }
        System.out.println();
    }

    public static void choose(int num, int std){
        if(std == n +1){
            if(num == m){
                print();
            }
            return;
        }

        ans.add(std);
        choose(num+1, std+1);
        ans.remove(ans.size()-1);
        choose(num, std+1);

    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        choose(0, 1);
    }
}