package CodeTree.IntermediateLow.Backtracking.thrid;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Pair{
    int x, y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class NtoM {
    private static int n, m, count = 10000;
    private static ArrayList<Pair> ans = new ArrayList<>();
    private static Pair[] arr = new Pair[20];

    public static int calc(){
        int max = -1;

        for(int i = 0; i < (int)ans.size()-1; i++) {
            int x1, x2, y1, y2;
            x1 = ans.get(i).x;
            y1 = ans.get(i).y;

            for(int j = i+1; j <(int)ans.size(); j++){
                x2 = ans.get(j).x;
                y2 = ans.get(j).y;

                max = Math.max(max, ((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)));
            }
        }
        // for(int i = 0; i < m; i++) {
        //     System.out.println(ans.get(i).x + " " + ans.get(i).y);
        // }
        // System.out.println(max);
        // System.out.println();
        // System.out.println();
        return max;
    }

    public static void findMinDist(int cnt){
        if(ans.size() == m){
            count = Math.min(count, calc());
            return;
        }

        if(cnt >= n) {
            return;
        }

        ans.add(arr[cnt]);
        findMinDist(cnt + 1);
        ans.remove(ans.size()-1);
        findMinDist(cnt + 1);

    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            Pair newPair = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            arr[i] = newPair;
        }

        findMinDist(0);

        System.out.println(count);
    }
}