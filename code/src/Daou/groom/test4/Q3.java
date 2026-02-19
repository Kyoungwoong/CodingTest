package Daou.groom.test4;

public class Q3 {
    public static int solution(int one_day_price, int multi_day, int multi_day_price, int n) {
        if (one_day_price * multi_day <= multi_day_price) {
            return n * one_day_price;
        } else {
            return (n % multi_day) * one_day_price
                    + (n / multi_day) * multi_day_price;
        }
    }

    public static void main(String[] args) {
        int one_day_price1 = 3;
        int multi_day1 = 5;
        int multi_day_price1 = 14;
        int n1 = 6;
        int ret1 = solution(one_day_price1, multi_day1, multi_day_price1, n1);
        System.out.println("solution 함수의 반환 값은 " + ret1 + " 입니다.");

        int one_day_price2 = 2;
        int multi_day2 = 3;
        int multi_day_price2 = 5;
        int n2 = 11;
        int ret2 = solution(one_day_price2, multi_day2, multi_day_price2, n2);
        System.out.println("solution 함수의 반환 값은 " + ret2 + " 입니다.");
    }
}
