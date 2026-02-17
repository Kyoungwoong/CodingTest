package Daou.groom.test3;

public class Q8 {
    public static int solution(int k, int[] student) {
        int answer = 0;

        for (int s : student) {
            System.out.print(s + " \t");
            s -= 4 * k;
            if (s <= 0) {
                continue;
            }
            answer += (s + k - 1) / k;
//            answer += s % k == 0 ? s / k : s / k + 1;
            System.out.println(answer);
        }

        return answer;
    }

    public static void main(String[] args) {
        int k1 = 1;
        int[] student1 = {4, 4, 4, 4};
        int ret1 = solution(k1, student1);
        System.out.println("solution 함수의 반환 값은 " + ret1 + " 입니다.");

        int k2 = 3;
        int[] student2 = {15, 17, 19, 10, 23};
        int ret2 = solution(k2, student2);
        System.out.println("solution 함수의 반환 값은 " + ret2 + " 입니다.");
    }
}
