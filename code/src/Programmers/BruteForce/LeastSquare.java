package Programmers.BruteForce;

public class LeastSquare {
//    private static int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}}; // 4000
    private static int[][] sizes = {{10, 7}, {12, 3}, {8, 15}, {14, 7}, {5, 15}}; // 120
//    private static int[][] sizes = {{14, 4}, {19, 6}, {6, 16}, {18, 7}, {7, 11}}; // 133

    public static void main(String[] args) {
        int maxNum = Integer.MIN_VALUE, col = Integer.MIN_VALUE;

        for (int[] card : sizes) {
            if (card[0] < card[1]) {
                int temp = card[0];
                card[0] = card[1];
                card[1] = temp;
            }

            maxNum = Math.max(maxNum, card[0]);
            col = Math.max(col, card[1]);
        }

        System.out.println("answer = " + (maxNum * col));

    }
}