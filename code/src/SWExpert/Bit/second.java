package SWExpert.Bit;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class second {
    private static int tc, n, m;
    private static ArrayList<Integer> ans;

    public static boolean isRight(){
        ans = new ArrayList<>();
        boolean right = false;

        while(m > 0){
            ans.add(m%2);
            m /= 2;
        }
//        System.out.println("=============ans==========");
//        for(int i = 0; i < (int)ans.size(); i++){
//            System.out.print(ans.get(i) + " ");
//        }
//        System.out.println();

        if((int)ans.size() < n){
            return false;
        }

        for(int i = 0; i < n; i++){
            if(ans.get(i) != 1){
                right = false;
                break;
            }else{
                right = true;
            }
        }

        return right;

    }

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("SW_Testcase/Bit/Bit_second.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        for(int t = 1; t <= tc; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            System.out.print("#" + t);
            if(isRight()){
                System.out.println(" ON");
            }else{
                System.out.println(" OFF");
            }
        }

    }
}
