package ThisIsCodingTest.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StringComp {
    public static String S;
    public static int len;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        len = S.length();

        int minLen = 10001;
        for(int i = 1; i <= len/2; i++){
            // i만큼 자르기.
            int cnt = 1;
            ArrayList<String> ans = new ArrayList<>();
            for(int j = 0; j < len-i; j += i){
//                System.out.println("i: "+ i + " j = " + j);

                if(j+2*i > len){
                    if(cnt == 1){
                        ans.add(S.substring(j, len));
                    }else{
                        ans.add(Integer.toString(cnt+1));
                        ans.add(S.substring(j-i, j));
                    }
                    break;
                }else if(j+2*i == len){
                    if(S.substring(j, j+i).equals(S.substring(j+i, j+2*i))){
                        ans.add(Integer.toString(cnt+1));
                        ans.add(S.substring(j, j+i));
                    }else{
                        ans.add(S.substring(j, len));
                    }
                    break;
                }

                if(S.substring(j, j+i).equals(S.substring(j+i, j+2*i))){
                    cnt++;
                }else{
                    if(cnt == 1){
                        ans.add(S.substring(j, j+i));
                    }else{
                        ans.add(Integer.toString(cnt));
                        ans.add(S.substring(j, j+i));
                        cnt = 1;
                    }
                }
            }
            int min = 0;
            System.out.print("i: " + i +"\t");
            for(int j = 0; j < ans.size(); j++){
                System.out.print(ans.get(j));
                min += ans.get(j).length();
            }
            System.out.println("\t"+ min);


            minLen = Math.min(minLen, min);
        }
        System.out.println("minLen = " + minLen);

    }
}
/*
s                       result
aabbaccc                    7
ababcdcdababcdcd            9
abcabcdede                  8
abcabcabcabcdededededede    14
xababcdcdababcdcd           17
 */
