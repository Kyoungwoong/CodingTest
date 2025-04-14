package Programmers;
import java.util.*;

public class Rollcake {

    class Diff {
        int left, right;
        public Diff(int l, int r) {
            this.left = l;
            this.right = r;
        }
    }

    //              여기가 n이면 n ~ n+1사이를 자르는거
    private static HashMap<Integer, Integer> cnt = new HashMap<>();
    private static HashSet<Integer> types = new HashSet<>();

    static  int[] topping = {1, 2, 1, 3, 1, 4, 1, 2};
//    static int[] topping = {1, 2, 3, 1, 4};

    public static void main(String[] args) {
        int answer = 0;
        int len = topping.length;
        for(int i = 0; i < len; i++) {
            cnt.put(topping[i], cnt.getOrDefault(topping[i], 0) + 1);
        }

        for(int num: cnt.keySet()) {
            System.out.println("num: " + num + " cnt: " + cnt.get(num));
        }

        Diff[] slice = new Diff[len];
        for(int i = 0; i < len; i++) {

            System.out.print(topping[i] + " ");

            types.add(topping[i]);

            if(cnt.get(topping[i]) == 1) {
                cnt.remove(topping[i]);
            } else {
                cnt.put(topping[i], cnt.get(topping[i]) -1);
            }

            if(types.size() == cnt.size()) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}