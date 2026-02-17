package Daou.groom.test3;

public class Q10 {
    public static int solution(int[] revenue, int k) {
        int answer = 0;

        int rsum = 0;
        for (int i = 0; i < k; i++) {
            rsum += revenue[i];
        }
        answer = rsum;

        for (int i = k; i < revenue.length; i++) {
            rsum = rsum - revenue[i - k] + revenue[i];
            if (answer < rsum) {
                answer = rsum;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] revenue1 = {1, 1, 9, 3, 7, 6, 5, 10};
        int k1 = 4;
        int ret1 = solution(revenue1, k1);
        System.out.println("solution 함수의 반환 값은 " + ret1 + " 입니다.");

        int[] revenue2 = {1, 1, 5, 1, 1};
        int k2 = 1;
        int ret2 = solution(revenue2, k2);
        System.out.println("solution 함수의 반환 값은 " + ret2 + " 입니다.");
    }
}
