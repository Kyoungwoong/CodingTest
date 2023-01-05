package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Scanner;

public class SW1244 {
    private static int t, n, ans = -1;
    private static ArrayList<Integer> arrayList = new ArrayList<>();
    private static ArrayList<Integer> changedList = new ArrayList<>();

    // public static void init(){
    //     while(){
    //         array
    //     }
    // }

    public static void intToArrayList(int std) {

        while(std > 0){
            arrayList.add(0, std%10);
            changedList.add(0,std%10);
            std /= 10;
        }
    }
    public static void dfs(int idx, int cnt){
        if(cnt == n){
            int std = 0;
            for(int i = 0; i < (int)changedList.size(); i++){
                std = std*10 + changedList.get(i);
            }
            ans = Math.max(std, ans);
            return;
        }

        for(int i = idx; i < (int)changedList.size()-1; i++){
            for(int j = i+1; j < (int)changedList.size(); j++){
                int tmp = changedList.get(i);
                changedList.set(i, changedList.get(j));
                changedList.set(j, tmp);

                dfs(i, cnt+1);

                changedList.set(j, changedList.get(i));
                changedList.set(i, tmp);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // t = Integer.parseInt(br.readLine());
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();

        for(int tc = 0; tc < t; tc++){
            // StringTokenizer st = new StringTokenizer(br.readLine());
            // int std = Integer.parseInt(st.nextToken());
            // n = Integer.parseInt(st.nextToken());
            int std = sc.nextInt();
            n = sc.nextInt();

            arrayList.clear();
            changedList.clear();

            intToArrayList(std);

            dfs(0,0);

            System.out.println(ans);
        }
    }
}
