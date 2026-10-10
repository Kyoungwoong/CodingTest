package Daou.Assignment.MockExam1.Q2;

public class Test {
    public static void main(String[] args) {
        System.out.println("=========CASE A==========");
        caseA();
        System.out.println("=========CASE B==========");
        caseB();
        System.out.println("=========CASE C==========");
        caseC();
    }

    // 정액 할인
    private static void caseA() {
        DiscountPolicy policy = new FixedDiscountPolicy(3000);
        OrderService service = new OrderService(policy);

        System.out.println(service.calculateFinalPrice(10000));
        System.out.println(service.calculateFinalPrice(2000));
    }

    // 정률 할인
    private static void caseB() {
        DiscountPolicy policy = new RateDiscountPolicy(15);
        OrderService service = new OrderService(policy);

        System.out.println(service.calculateFinalPrice(10000));
        System.out.println(service.calculateFinalPrice(9999));
    }

    // 할인 정책 교체
    private static void caseC() {
        OrderService fixed = new OrderService(
                new FixedDiscountPolicy(2000)
        );

        OrderService rate = new OrderService(
                new RateDiscountPolicy(20)
        );

        System.out.println(fixed.calculateFinalPrice(15000));
        System.out.println(rate.calculateFinalPrice(15000));
    }
}
