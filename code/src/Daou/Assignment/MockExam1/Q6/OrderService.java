package Daou.Assignment.MockExam1.Q6;

import java.util.HashSet;
import java.util.Set;

public class OrderService {

    private final Set<String> completedOrders = new HashSet<>();

    private final PaymentProcessor paymentProcessor;
    private final DiscountPolicy discountPolicy;

    public OrderService(
            PaymentProcessor paymentProcessor,
            DiscountPolicy discountPolicy
    ) {
        this.paymentProcessor = paymentProcessor;
        this.discountPolicy = discountPolicy;
    }

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

        long totalPrice = product.getPrice() * quantity;

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
