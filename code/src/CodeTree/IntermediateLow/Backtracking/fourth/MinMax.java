package CodeTree.IntermediateLow.Backtracking.fourth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class MinMax {
    private static int n, min = -1;
    private static int[][] arr = new int[10][10];
    private static ArrayList<Integer> col = new ArrayList<>();
    private static boolean[] visited = new boolean[10];

    public static int calc(){
        int val = 10001;

        for(int i = 0; i < (int)col.size(); i++) {
            val = Math.min(val, arr[i][col.get(i)]);
        }

        return val;
    }

    public static void findMinValue(int row) {
        if(row == n) {
            min = Math.max(min, calc());
            return;
        }

        for(int i = 0; i < n; i++) {
            if(visited[i]) {
                continue;
            }
            visited[i] = true;
            col.add(i);

            findMinValue(row + 1);

            col.remove(col.size()-1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findMinValue(0);

        System.out.println(min);
    }
}