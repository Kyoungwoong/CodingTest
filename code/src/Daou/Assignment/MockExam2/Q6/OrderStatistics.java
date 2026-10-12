package Daou.Assignment.MockExam2.Q6;

import java.util.*;
import java.util.stream.Collectors;

public class OrderStatistics {

    // TODO
    public Map<String, Long> totalAmountByCustomer(
            List<Order> orders) {
        validateOrders(orders);

        return orders.stream()
                .filter(order -> order.status() == OrderStatus.COMPLETED)
                .collect(Collectors.partitioningBy(Order::customerId, Order::amount));
    }

    public Optional<String> findTopCustomer(
            List<Order> orders){
        validateOrders(orders);
        long count = orders.stream()
                .filter(order -> order.status() == OrderStatus.COMPLETED)
                .count();
        if (count == 0) {
            return Optional.empty();
        }

        return orders.stream()
                .filter(order -> order.status() == OrderStatus.COMPLETED)
                .sorted(Comparator.comparingInt(Order::amount)
                        .thenComparing(Order::customerId).reversed())
                .findFirst();
    }
    public List<String> findCustomersAbove(
            List<Order> orders,
            long minimumAmount){

    }

    private void validateOrders(List<Order> orders) {
        if (orders == null) {
            throw new IllegalArgumentException(
                    "orders must not be null"
            );
        }
    }
}