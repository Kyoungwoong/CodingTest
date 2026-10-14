package Daou.Assignment.MockExam4.Q2;

import java.util.*;
import java.util.stream.Collectors;

public class OrderAnalyzer {

    private final List<Order> orders;
    private final Set<String> orderSet = new HashSet<>();

    public OrderAnalyzer(List<Order> orders) {
        if (orders == null) {
            throw new IllegalArgumentException("orders must not be null");
        }

        for (Order order: orders) {
            if (order == null) {
                throw new IllegalArgumentException("order must not be null");
            }

            if (order.quantity() <= 0) {
                throw new IllegalArgumentException("quantity must be positive number");
            }

            if (order.unitPrice() < 0) {
                throw new IllegalArgumentException("unit price must be zero or positive number");
            }

            if (orderSet.contains(order.orderId())) {
                throw new IllegalArgumentException("order Id cannot be registered");
            }
            orderSet.add(order.orderId());
        }

        // 외부에서 원본 리스트를 변경하여도 영향 없도록.
        this.orders = List.copyOf(orders);
    }

    public Map<String, Long> getTotalAmountByCustomer() {
        return orders.stream()
                    .collect(Collectors.toMap(Order::orderId, 
                                            Math.multiplyExact(Order::quantity, Order::unitPrice),
                                            Math::addExact));
    }

    public Map<String, Integer> getQuantityByProduct() {
        return orders.stream()
                    .collect(Collectors.toMap(Order::productId,
                                            Order::quantity,
                                            Math::addExact));
    }

    public List<String> getTopCustomers(int limit) {
        return getTotalAmountByCustomer().entrySet()
                                        .stream()
                                        .sorted()
                                        .limit(limit)
                                        .toList();
    }

    public Map<String, List<Order>> groupOrdersByCustomer() {
        // TODO
    }

    public List<Order> findOrdersByMinAmount(long minAmount) {
        // TODO
    }
}
