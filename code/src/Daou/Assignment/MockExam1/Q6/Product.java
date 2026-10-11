package Daou.Assignment.MockExam1.Q6;

public class Product {

    private final String id;
    private final String name;
    private final long price;
    private int stock;

    public Product(String id, String name, long price, int stock) {
        validateProduct(id, name, price, stock);

        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void decreaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "quantity must be positive number"
            );
        }

        if (stock < quantity) {
            throw new IllegalStateException(
                    "stock less than quantity"
            );
        }
        stock -= quantity;
    }

    /**
     * 필드 검증
     * id: null또는 공백 불가
     * name: null또는 공백 불가
     * price: 0이상
     * stock: 0이상
     */
    private static void validateProduct(String id, String name, long price, int stock) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(
                    "id must not be null or empty"
            );
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "name must not be null or empty"
            );
        }

        if (price < 0) {
            throw new IllegalArgumentException(
                    "price must be zero or positive number"
            );
        }

        if (stock < 0) {
            throw new IllegalArgumentException(
                    "stock must be zero or positive number"
            );
        }
    }
}