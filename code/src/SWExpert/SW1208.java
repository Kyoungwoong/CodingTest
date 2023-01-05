package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Arrays;

public class SW1208 {
    private static int garo = 100, tc = 10, dumpCount, ans = 100;
    private static Integer[] arr;

    public static void doFlat(int count, int step){

        while(count > 0){


            if(ans == 0 || ans == 1){
                break;
            }
            int std = Math.min(arr[0]-arr[1], arr[98]-arr[99]) + 1;

            arr[0] = arr[0] - std;
            arr[99] = arr[99] + std;

            Arrays.sort(arr, Collections.reverseOrder());

            System.out.println();
            count -= std;
            ans = arr[0]-arr[99];
        }

        System.out.println("#" + step + " " + (ans));
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int t = 0; t < tc; t++){
            dumpCount = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            arr = new Integer[garo];

            for(int i = 0;  i < garo; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr, Collections.reverseOrder());

            doFlat(dumpCount, t+1);
        }

    }
}
