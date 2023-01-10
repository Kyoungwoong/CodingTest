package SWExpert;

import java.util.Scanner;
import java.io.FileInputStream;


public class Pre2{
    private static int tc, x, y;
    private static String n;
    private static boolean round = false;
    private static StringBuilder stringBuilder;

    public static void init(){
        int tmp = x;
        if(y > x){
            x = y;
            y = tmp;
        }

        round = false;
        stringBuilder = new StringBuilder();
    }
    public static void findPresent(){
        if(x == 0 && y == 0){
            stringBuilder.append("-1");
            return;
        }
        int xIdx = -1;

        // 0이 한개 y == 0, 0이 없을 때
        for(int i = 0; i < n.length(); i++) {
            int std = n.charAt(i) - '0';
            if (round) {
                stringBuilder.append(Integer.toString(x));
                xIdx = i;
            } else if (std >= x) {
                if (std > x) {
                    round = true;
                }
                stringBuilder.append(Integer.toString(x));
                xIdx = i;
            } else if (std >= y) {
                if (i == 0 && y == 0) {
                    round = true;
                    continue;
                }
                if (std > y) {
                    round = true;
                }
                stringBuilder.append(Integer.toString(y));
            } else { // std < x && std < y
                if(xIdx == -1){
                    stringBuilder = new StringBuilder();
                    for(int j = 0; j < n.length()-1; j++){
                        stringBuilder.append(Integer.toString(x));
                    }
                }else{
                    stringBuilder.substring(0, xIdx+1);

                    stringBuilder.delete(xIdx, xIdx + 1);
                    stringBuilder.insert(xIdx, Integer.toString(y));
                    for(int j = xIdx+1; j < n.length(); j++){
                        stringBuilder.insert(j, Integer.toString(x));
                    }

                    while(stringBuilder.length() != n.length()){
                        stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
                    }

                }
                return;
            }
        }
    }

    public static void main(String[] args) throws Exception{

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++){
            n = sc.next();

            x = sc.nextInt();
            y = sc.nextInt();

            init();

            findPresent();

            String ans = stringBuilder.toString();
            if(ans.equals("")){
                stringBuilder.append("-1");
                ans = stringBuilder.toString();
            }
            System.out.println("#" + (test_case) + " " + ans);

        }
    }
}