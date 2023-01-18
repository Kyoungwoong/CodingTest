package SWExpert.Bit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileInputStream;

public class first {
    private static int t, N, round, std = 10, ans;
    private static int[] num = new int[std + 1];

    public static void init(){
        round = 1;

        for(int i = 0; i <= std; i++){
            num[i] = 0;
        }
    }

    public static void findAllNum(){
        while(true){
            ans = N * round;
            int temp = ans;
            boolean check = false;

            while(temp > 0){
                num[temp%10]++;
                temp /= 10;
            }

            for(int i = 0; i < std; i++){
                if(num[i] == 0){
                    check = true;
                    break;
                }
            }

            if(check){
                round++;
            }else{
                return;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("SW_Testcase/Bit/Bit_first.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= t; tc++){
            N = Integer.parseInt(br.readLine());
            init();
            findAllNum();
            System.out.println("#" + tc + " " + ans);
        }
    }
}
