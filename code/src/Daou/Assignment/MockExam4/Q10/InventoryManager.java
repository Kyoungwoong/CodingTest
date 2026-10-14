package Daou.Assignment.MockExam4.Q10;

import java.util.*;

public class InventoryManager {

    private final Map<String, Integer> stocks = new HashMap<>();

    public void addStock(String productId, int quantity) {
        if (productId == null || productId.isBlank()) {
            throw new IllegalArgumentException("Invalid productId");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Invalid quantity");
        }

        int current = stocks.getOrDefault(productId, 0);
        stocks.put(productId, current + quantity);
    }

    public boolean removeStock(String productId, int quantity) {
        if (productId == null || productId.isBlank()) {
            throw new IllegalArgumentException("Invalid productId");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Invalid quantity");
        }

        int current = stocks.getOrDefault(productId, 0);

        stocks.put(productId, current - quantity);

        if (current < quantity) {
            return false;
        }

        return true;
    }

    public int getStock(String productId) {
        return stocks.getOrDefault(productId, 0);
    }

    public boolean containsProduct(String productId) {
        return stocks.containsKey(productId);
    }
}