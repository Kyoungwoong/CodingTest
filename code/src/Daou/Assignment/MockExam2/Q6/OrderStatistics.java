package Daou.Assignment.MockExam2.Q6;

import java.util.*;
import java.util.stream.Collectors;

public class OrderStatistics {

    public Map<String, Long> totalAmountByCustomer(
            List<Order> orders) {
        validateOrders(orders);

        return orders.stream()
                .filter(order -> order.status() == OrderStatus.COMPLETED)
                .collect(Collectors.toMap(
                        Order::customerId,
                        Order::amount,
                        Math::addExact
                ));
    }

    public Optional<String> findTopCustomer(
            List<Order> orders) {
        Map<String, Long> totals = totalAmountByCustomer(orders);

        return totals.entrySet().stream()
                .sorted(
                        Map.Entry
                                .<String, Long>comparingByValue()
                                .reversed()
                                .thenComparing(Map.Entry.comparingByKey())
                )
                .map(Map.Entry::getKey)
                .findFirst();
    }

    public List<String> findCustomersAbove(
            List<Order> orders,
            long minimumAmount) {

        if (minimumAmount < 0) {
            throw new IllegalArgumentException(
                    "minimumAmount must not be negative"
            );
        }

        Map<String, Long> totals =
                totalAmountByCustomer(orders);

        return totals.entrySet().stream()
                .filter(entry ->
                        entry.getValue() >= minimumAmount)
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private void validateOrders(List<Order> orders) {

        if (orders == null) {
            throw new IllegalArgumentException(
                    "orders must not be null"
            );
        }

        for (Order order : orders) {
            if (order == null
                    || order.customerId() == null
                    || order.customerId().isBlank()
                    || order.amount() < 0
                    || order.status() == null) {

                throw new IllegalArgumentException(
                        "Invalid order"
                );
            }
        }
    }
}