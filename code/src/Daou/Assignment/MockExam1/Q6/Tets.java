package Daou.Assignment.MockExam1.Q6;

public class Tets {
    public static void main(String[] args) {
        // 정상 주문
        caseA();

        // 결제 실패
        caseB();

        // 재고 부족
        caseC();

        // 중복 주문
        caseD();

        // 오버플로 방지
        caseE();

        // 100% 할인
        caseF();
    }

    private static void caseA() {
        Product product = new Product("P001", "Keyboard", 10000, 5);
        DiscountPolicy discountPolicy = new DiscountPolicy(10);

        PaymentProcessor payment = amount -> true;

        OrderService service = new OrderService(payment, discountPolicy);

        long result = service.placeOrder("ORDER-001", product, 2);

        System.out.println(result);
        System.out.println(product.getStock());
        System.out.println(service.isCompleted("ORDER-001"));
    }

    private static void caseB() {
        Product product = new Product("P001", "Keyboard", 10000, 5);

        PaymentProcessor payment = amount -> false;

        OrderService service = new OrderService(
                payment,
                new DiscountPolicy(10)
        );

        service.placeOrder("ORDER-002", product, 2);
    }

    private static void caseC() {
        Product product = new Product("P001", "Keyboard", 10000, 1);

        PaymentProcessor payment = amount -> {
            System.out.println("PAYMENT CALLED");
            return true;
        };

        OrderService service = new OrderService(
                payment,
                new DiscountPolicy(10)
        );

        service.placeOrder("ORDER-003", product, 2);
    }

    private static void caseD() {
        Product product = new Product("P001", "Keyboard", 10000, 5);

        OrderService service = new OrderService(
                amount -> true,
                new DiscountPolicy(0)
        );

        service.placeOrder("ORDER-004", product, 1);
        service.placeOrder("ORDER-004", product, 1);
    }

    private static void caseE() {
        Product product = new Product(
                "P999", "Expensive Item", Long.MAX_VALUE, 2
        );

        OrderService service = new OrderService(
                amount -> true,
                new DiscountPolicy(10)
        );

        service.placeOrder("ORDER-005", product, 2);
    }

    private static void caseF() {
        Product product = new Product("P001", "Keyboard", 10000, 3);

        PaymentProcessor payment = amount -> {
            System.out.println("PAY: " + amount);
            return true;
        };

        OrderService service = new OrderService(
                payment,
                new DiscountPolicy(100)
        );

        System.out.println(service.placeOrder("ORDER-006", product, 1));
    }
}
