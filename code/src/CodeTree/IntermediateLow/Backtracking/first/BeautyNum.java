package CodeTree.IntermediateLow.Backtracking.first;

import java.util.Scanner;
import java.util.ArrayList;

public class BeautyNum {
    private static int n, ans = 0;
    private static ArrayList<Integer> answer = new ArrayList<>();

    public static boolean isBeauty() {

        for(int i = 0; i < n; i += answer.get(i)) {
            if(i + answer.get(i) -1 >= n) {
                return false;
            }

            for(int j = i; j < i + answer.get(i); j++) {
                if(answer.get(j) != answer.get(i)){
                    return false;
                }
            }
        }

        return true;
    }

    public static int makeBeauty(int cnt){
        if(cnt == n+1) {
            if(isBeauty()){
                ans++;
            }

            return 0;
        }

        for(int i = 1; i <= 4; i++) {
            answer.add(i);
            makeBeauty(cnt+1);
            answer.remove(answer.size() - 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        // Your Program Goes Here
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        System.out.print(makeBeauty(1));

    }
}