package SWExpert.Bit;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class third {
    private static int tc, MOD = 1000000007, ans;
    private static int[] ABCD = new int[4];
    private static String producer;
//    private static ArrayList<Integer> today = new ArrayList<>();
//    private static ArrayList<Integer> yesterday = new ArrayList<>();
    private static int today, yesterday;

    /*
        & => 둘다 1일 때
        | => 하나만 1일 때
        ^ => exclusive or
     */

    public static boolean firstDayPossible(int firstDay){
        // A가 키를 가지고 있음.
        if((firstDay & 1) == 0){
            return false;
        }
        // 첫째날의 책임자.
        if((firstDay & (1 << producer.charAt(0)-'A'+1)) == 0){
            return false;
        }

        return true;
    }
    public static boolean isPossible(int std, int day){
        int responIdx = (producer.charAt(day) - 'A') + 1;

        // 1. 책임자 꼭 추가
        if((std & (1<< responIdx)) == 0){
            return false;
        }
        // 2. 전날과 오늘 겹치는 사람이 있냐 없냐
        if((std & yesterday) == 0){
            return false;
        }
        return true;
    }


    public static void init(){
        ans = 0;
        int responIdx = producer.charAt(0) - 'A' + 1;
//        for(int i = 0; i < 4; i++){
//            yesterday.add(0);
//            today.add(0);
//        }
//        yesterday.set(0, 1);
//        yesterday.set(responIdx, 1);
        yesterday = 1 | responIdx;

    }

    public static void findCount(int day){

        if(day == producer.length()){
            ans++;
            return;
        }

        for(int i = 1; i <= 16; i++){
            yesterday = i;
            if(firstDayPossible(yesterday)){
                for(int j = 1; j <= 16; j++){
                    if(isPossible(j, day)){
                        yesterday = j;
                        findCount(day+1);
                    }
                }
            }


        }


    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("SW_Testcase/Bit/Bit_third.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        for(int t = 1; t <= tc; t++){
            producer = br.readLine();
//            ABCD[0] = 1;

//            init();

            findCount(1);
            System.out.println("#" + t + " " + (ans % MOD));
        }
    }
}
