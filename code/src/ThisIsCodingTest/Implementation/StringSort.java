package ThisIsCodingTest.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class StringSort {
    public static String S;
    public static int len;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        S = br.readLine();
        len = S.length();

        char[] temp = S.toCharArray();
        Arrays.sort(temp);
//        S = temp.toString();

        ArrayList<Character> cmd = new ArrayList<>();
        for(int i = 0; i < len; i++){
            if(temp[i] >= '0' && temp[i] <= '9'){
                cmd.add(temp[i]);
            }else{
                sb.append(temp[i]);
            }
        }
        int sum = 0;
        for(int i = 0; i < cmd.size(); i++) {
            sum += cmd.get(i)-'0';
        }
        sb.append(sum);

        System.out.println(sb);
    }
}
/*
K1KA5CB7

AJKDLSI412K4JSJ9D
 */
