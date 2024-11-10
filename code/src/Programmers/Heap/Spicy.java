package Programmers.Heap;

import java.util.PriorityQueue;

public class Spicy {
//    private static int[] scoville = {1, 2, 3, 9, 10, 12};
//    private static int K = 7; // 2
    private static int[] scoville = {1, 1, 1, 1};
    private static int K = 10; // -1

    public static void main(String[] args) {
//        prev();
        System.out.println(nov10());
    }

    private static int nov10() {
        PriorityQueue<Integer> scovilles = new PriorityQueue<>();

        for (int level: scoville) {
            scovilles.add(level);
        }

        int mix = -1;
        int ans = 0;
        while (scovilles.peek() < K) {
            if (scovilles.size() < 2) {
                return -1;
            }
            int first = scovilles.poll();
            int second = scovilles.poll();
            mix = first + second * 2;
            scovilles.add(mix);
            ans++;
        }

        return ans;
    }

    private static void prev() {
        int len = scoville.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int score : scoville) {
            pq.add(score);
        }

        int mixCnt = 0;
        while (pq.peek() < K) {
            if (pq.size() >= 2) {
                int one = pq.poll();
                int two = pq.poll();
                int mix = one + two * 2;
                pq.add(mix);
                mixCnt++;
            } else {
                System.out.println("-1 = " + -1);
            }
        }

        System.out.println("mixCnt = " + mixCnt);
    }
}
