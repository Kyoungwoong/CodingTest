package ThisIsCodingTest.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringSort {
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

        List<Character> arr = new ArrayList<>();
        int next = 0;
        for (int i = 0; i < len; i++) {
            if (S.charAt(i) - '0' >= 0 && S.charAt(i) - '0' <= 9) {
                next += S.charAt(i) - '0';
            } else {
                arr.add(S.charAt(i));
            }
        }
        Collections.sort(arr);
        System.out.println(String.valueOf(arr.stream().toArray()));

        StringBuilder sb = new StringBuilder();
        for (Character word : arr) {
            sb.append(word);
        }
        sb.append(next);
        System.out.println("sb = " + sb);
    }

    private static void prev() throws IOException {
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
