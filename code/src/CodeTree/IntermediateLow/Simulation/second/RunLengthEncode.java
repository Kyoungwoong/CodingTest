package CodeTree.IntermediateLow.Simulation.second;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class RunLengthEncode {
    private static String str = "";

    public static int RunLengthEncode(String obj){
        int len = obj.length(), count, min = 100;

        for(int i = 0; i <= len; i++) {
            count = 1;
            str = "";
            if(i != 0) {
                char temp = obj.charAt(len-1);
                obj = temp + obj.substring(0, len-1);
            }

            // str 설정
            int j;
            for(j = 1; j < len; j++) {
                if(obj.charAt(j) != obj.charAt(j-1)){
                    str += obj.charAt(j-1) + String.valueOf(count);
                    count = 1;
                }else{
                    count++;
                }

                if(j == len-1 && count != 0){
                    str += obj.charAt(j) + String.valueOf(count);
                }
            }



            min = Math.min(min, str.length());
            // System.out.println("obj is " + obj + " str is " + str);
        }
        // System.out.println(str);
        if(len == 1) {
            min = 2;
        }
        return min;
    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String obj = br.readLine();

        int ans = RunLengthEncode(obj);
        System.out.println(ans);
    }
}