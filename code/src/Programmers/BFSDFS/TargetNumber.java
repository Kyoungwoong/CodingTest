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
//        prev();
        int ans = nov20();
    }

    private static int[] arr;
    private static int targetNumber;

    public static void findTargetNum(int num, int cnt) {
        if (num == targetNumber && cnt == arr.length) {
            answer++;
            return;
        }
        if (cnt >= arr.length) return;

        findTargetNum(num - arr[cnt], cnt + 1);
        findTargetNum(num + arr[cnt], cnt + 1);
    }
    private static int nov20() {
        int n = numbers.length;
        arr = new int[n];
        targetNumber = target;

        for(int i = 0; i < n; i++) {
            arr[i] = numbers[i];
        }

        findTargetNum(-1 * arr[0], 1);
        findTargetNum(arr[0], 1);

        return answer;
    }

    private static void prev() {
        int len = numbers.length;
        findNumber(-1 * numbers[0], 1, len, target);
        findNumber(1 * numbers[0], 1, len, target);
        System.out.println("answer = " + answer);
    }
}
