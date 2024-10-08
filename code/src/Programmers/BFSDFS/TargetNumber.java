package Programmers.BFSDFS;

public class TargetNumber {
//    private static int[] numbers = {1, 1, 1, 1, 1};
//    private static int target = 3; // 5

    private static int[] numbers = {4, 1, 2, 1};
    private static int target = 4; // 2

    private static int answer = 0;

    public static void findNumber(int num, int size, int len, int target) {
        if (size == len && num == target) {
            answer++;
            return;
        }

        if (size >= len) {
            return;
        }

        findNumber(num + -1 * numbers[size], size+1, len, target);
        findNumber(num + 1 * numbers[size], size+1, len, target);
    }

    public static void main(String[] args) {
        int len = numbers.length;
        findNumber(-1 * numbers[0], 1, len, target);
        findNumber(1 * numbers[0], 1, len, target);
        System.out.println("answer = " + answer);
    }
}
