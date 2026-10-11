package Daou.Assignment.MockExam1.Q6;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class OrderService {

    private final Set<String> completedOrders = new HashSet<>();

    private final PaymentProcessor paymentProcessor;
    private final DiscountPolicy discountPolicy;

    public OrderService(
            PaymentProcessor paymentProcessor,
            DiscountPolicy discountPolicy
    ) {
        this.paymentProcessor = Objects.requireNonNull(
                paymentProcessor,
                "paymentProcessor must not be null"
        );
        this.discountPolicy = Objects.requireNonNull(
                discountPolicy,
                "discountPolicy must not be null"
        );
    }

    /**
     * 주문을 검증하고 결제를 요청한다.
     * 결제가 성공한 경우에만 재고를 차감하고 주문 완료를 기록한다.
     *
     * @return 실제 결제 금액
     * @throws IllegalArgumentException 주문 인자가 유효하지 않은 경우
     * @throws IllegalStateException 중복 주문, 재고 부족 또는 결제 실패
     * @throws ArithmeticException 총 주문 금액이 long 범위를 초과한 경우
     */
    public long placeOrder(
            String orderId,
            Product product,
            int quantity
    ) {
        validateArgument(orderId, product, quantity);

        if (completedOrders.contains(orderId)) {
            throw new IllegalStateException(
                    "completed Order ID: " + orderId
            );
        }

        if (product.getStock() < quantity) {
            throw new IllegalStateException(
                    "stock is less than quantity"
            );
        }

        long totalPrice = Math.multiplyExact(
                product.getPrice(),
                quantity
        );

        long discountAmount = discountPolicy.calculateDiscount(totalPrice);
        long finalPrice = totalPrice - discountAmount;

        boolean success = paymentProcessor.pay(finalPrice);

        if (!success) {
            throw new IllegalStateException(
                    "payment failed"
            );
        }

        product.decreaseStock(quantity);
        completedOrders.add(orderId);

        return finalPrice;
    }

    public boolean isCompleted(String orderId) {
        return completedOrders.contains(orderId);
    }

    private void validateArgument(String orderId, Product product, int quantity) {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException(
                    "orderId must not be null"
            );
        }

        if (product == null) {
            throw new IllegalArgumentException(
                    "product cannot be null"
            );
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "quantity must be positive number"
            );
        }
    }
}
