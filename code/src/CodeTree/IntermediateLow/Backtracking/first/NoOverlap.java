package CodeTree.IntermediateLow.Backtracking.first;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

class N_Pair implements Comparable<N_Pair>{
    int x1, x2;

    public N_Pair(int x1, int x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    @Override
    public int compareTo(N_Pair pair){
        if(pair.x1 < x1){
            return 1;
        }else if(pair.x1 == x1) {
            return 0;
        }else{
            return -1;
        }
    }
}

public class NoOverlap{
    private static int n, count = 0;
    private static int[] arr= new int[1001];
    private static ArrayList<N_Pair> line = new ArrayList<>();
    private static ArrayList<N_Pair> std = new ArrayList<>();

    public static int calc(){
        int cnt = 0;
        boolean check = false;

        for(int i = 0; i < (int)std.size()-1; i++) {
            if(std.get(i).x2 >= std.get(i+1).x1){
                if(!check){
                    check = true;
                    cnt += 2;
                }
                else{ // check == true
                    cnt++;;
                }
            }
            else{
                check = false;
            }
        }
        int n = (int)std.size() - cnt;
        return n < 0 ? 0 : n;
    }

    public static void findMaxCount(int num) {
        if(num == n){
            // if((int)std.size() != 0 && std.get(0).x1 == 380) {
            //     for(int i = 0; i < (int)std.size(); i++) {
            //         System.out.print(std.get(i).x1 +  " " + std.get(i).x2 + " " );
            //     }
            //     System.out.println();
            // }

            count = Math.max(count, calc());
            return;
        }


        int x1 = line.get(num).x1;
        int x2 = line.get(num).x2;

        // System.out.println(num);

        std.add(new N_Pair(x1, x2));
        findMaxCount(num+1);
        std.remove(std.size()-1);
        findMaxCount(num+1);

    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            line.add(new N_Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(line);
        findMaxCount(0);

        System.out.println(count);
    }
}
