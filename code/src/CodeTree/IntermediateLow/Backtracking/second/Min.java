package CodeTree.IntermediateLow.Backtracking.second;

import java.util.Scanner;
import java.util.ArrayList;

public class Min {
    private static int n;
    private static ArrayList<Integer> ans = new ArrayList<>();

    public static boolean isEqual(int start1, int end1, int start2, int end2) {
        for(int i = 0; i <= end1 - start1; i++) {
            if(ans.get(start1 + i) != ans.get(start2 + i))
                return false;
        }

        return true;
    }

    public static boolean check(){
        int len = 1;
        while(true) {
            // 연속 부분 수열의 시작과 끝 인덱스를 설정하여 줍니다.
            int end1 = ans.size() - 1, start1 = end1 - len + 1;
            int end2 = start1 - 1, start2 = end2 - len + 1;

            if(start2 < 0)
                break;

            // 인접한 연속 부분 수열이 같은지 여부를 확인합니다.
            if(isEqual(start1, end1, start2, end2))
                return false;

            len++;
        }

        return true;
    }

    public static void print(){
        for(int i = 0; i < (int)ans.size(); i++){
            System.out.print(ans.get(i));
        }
    }

    public static void findNum(int num){
        if(!check()){
            return;
        }

        if(num == n){
            print();
            System.exit(0);
            // return;
        }

        // System.out.println("round is " + num);
        // for(int i = 0; i < (int)ans.size(); i++){
        //     System.out.print(ans.get(i));
        // }
        // System.out.println();

        for(int i = 4; i <= 6; i++) {
            ans.add(i);
            findNum(num+1);
            ans.remove(ans.size()-1);
            // findNum(num+1);
        }
    }

    public static void main(String[] args) {
        // Your Program Goes Here
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        findNum(0);
    }
}
