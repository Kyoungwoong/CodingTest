package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SW1206 {

    private static int garo, tc = 10, ans;
    private static int[] arr;

    public static void findView(int step){
        ans = 0;

        for(int i = 2; i < garo-2; i++){
            int max = Math.max(Math.max(arr[i-2], arr[i-1]), Math.max(arr[i+1], arr[i+2]));

            if(arr[i] - max > 0){
                ans += arr[i]-max;
            }
        }
        System.out.println("#" + step + " " + (ans));
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int t = 0; t < tc; t++){
            garo = Integer.parseInt(br.readLine());
            arr = new int[garo + 4];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int building = 0; building < garo; building++){
                arr[building] = Integer.parseInt(st.nextToken());
            }

            findView(t+1);
        }

    }
}
