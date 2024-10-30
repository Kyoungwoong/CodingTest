package ThisIsCodingTest.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StringComp {
    public static String S;
    public static int len;

    public static void main(String[] args) throws IOException {
//        prev();
        oct30();

    }

    private static void oct30() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        len = S.length();

        int result = len; // i가 1일때
        for (int i = 1; i <= len / 2; i++) {
            // i는 자르는 단위
            System.out.println("i = " + i);
            System.out.println("=============================================");
            String cmd = "";
            String previous = "";
            int comp = 1;
            for (int j = 0; j < len; j += i) {
                if (j + i > len) {
                    if (previous.equals("")) {
                        cmd += S.substring(j);
                    } else {
                        cmd += previous + S.substring(j);
                    }
                    break;
                }

                System.out.print(S.substring(j, j + i) + "\t");
                if (previous.equals(S.substring(j, j + i))) {
                    comp++;
                } else if (previous.equals("")) {
                    previous = S.substring(j, j + i);
                    comp = 1;
                } else {
                    if (comp == 1) {
                        cmd += previous;
                    } else {
                        cmd += comp + previous;
                    }
                    comp = 1;
                    previous = S.substring(j, j + i);
                }

                if (j + i == len) {
                    if (comp == 1) {
                        cmd += S.substring(j, j + i);
                    } else {
                        cmd += comp + S.substring(j, j + i);
                    }
                    break;
                }
            }

            result = Math.min(result, cmd.length());
            System.out.println("\ncmd = " + cmd + ": " + cmd.length());
        }
        System.out.println("result = " + result);
    }

    private static void prev() throws IOException {
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
