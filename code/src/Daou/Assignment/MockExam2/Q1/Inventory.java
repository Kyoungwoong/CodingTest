package Daou.Assignment.MockExam2.Q1;

/**
 * 상품 재고 클래스
 * availableStock+reservedStock=initialStock
 * 은 항상 유지 되어야한다.
 * Inventory
 */
public class Inventory {

    private final String productId;
    private int availableStock;
    private int reservedStock;


    public Inventory(String productId, int initialStock) {
        validateInventory(productId, initialStock);

        this.productId = productId;
        this.availableStock = initialStock;
        this.reservedStock = 0;
    }

    public String getProductId() {
        return productId;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public int getReservedStock() {
        return reservedStock;
    }

    /**
     * 재고 예약.
     * quantity는 0보다 커야 하며, availableStcok보다 커야한다.
     * @param quantity: 요청 재고량
     */
    public void reserve(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive number");
        }

        if (quantity > availableStock) {
            throw new IllegalStateException("available Stock less than requested quantity");
        }

        availableStock -= quantity;
        reservedStock += quantity;
    }

    /**
     * 예약 취소
     * quantity는 0보다 커야하며, reservedStock보다 커야한다.
     * @param quantity: 취소 요청 량
     */
    public void cancel(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive number");
        }

        if (quantity > reservedStock) {
            throw new IllegalStateException("reserved Stock less than requested quantity");
        }

        availableStock += quantity;
        reservedStock -= quantity;
    }

    private void validateInventory(String productId, int initialStock) {
        if (productId == null || productId.isBlank()) {
            throw new IllegalArgumentException("ProductId must not be null");
        }

        if (initialStock < 0) {
            throw new IllegalArgumentException("initialStock must be zero or positive number");
        }
    }
    
}