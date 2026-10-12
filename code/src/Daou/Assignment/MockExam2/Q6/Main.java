package Daou.Assignment.MockExam2.Q6;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = List.of(
            new Order("C001", 10000, OrderStatus.COMPLETED),
            new Order("C002", 20000, OrderStatus.COMPLETED),
            new Order("C001", 15000, OrderStatus.COMPLETED),
            new Order("C003", 50000, OrderStatus.CANCELED),
            new Order("C002", 5000, OrderStatus.COMPLETED),
            new Order("C004", 25000, OrderStatus.COMPLETED)
        );

        OrderStatistics orderStatistics = new OrderStatistics();
        orderStatistics.totalAmountByCustomer(orders);

        orderStatistics.findTopCustomer(orders);

        orderStatistics.findCustomersAbove(orders, 25000);

        orderStatistics.findCustomersAbove(orders, 30000);
    }
}
