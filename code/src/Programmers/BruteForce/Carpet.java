package Programmers.BruteForce;

public class Carpet {
//    private static int brown = 10;
//    private static int yellow = 2; // [4, 3]

//    private static int brown = 8;
//    private static int yellow = 1; // [3, 3]

    private static int brown = 24;
    private static int yellow = 24; // [8, 6]

    public static void main(String[] args) {
        int[] answer = new int[2];

        for (int v = 3; ; v++) {
            for (int h = 3; ; h++) {
                int brownCnt = 2 * v + 2 * (h - 2);
                int yellowCnt = v * h - brownCnt;
                if (brownCnt == brown && yellowCnt == yellow) {
                    answer[0] = h;
                    answer[1] = v;
                    System.out.println("v = " + v);
                    System.out.println("h = " + h);
                    return;
                } else if (brownCnt > brown) {
                    break;
                }
            }
        }
    }
}
