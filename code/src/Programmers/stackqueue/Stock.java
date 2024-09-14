package Programmers.stackqueue;

public class Stock {
    private static int[] prices = {1, 2, 3, 2, 3}; // return 4,3,1,1,0

    public static void main(String[] args) {
        int len = prices.length;
        int[] answer = new int[len];
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                answer[i]++;
                if (prices[i] > prices[j]) {
                    break;
                }
            }
        }

        for (int i = 0; i < len; i++) {
            System.out.println("answer[i] = " + answer[i]);
        }
    }
}
