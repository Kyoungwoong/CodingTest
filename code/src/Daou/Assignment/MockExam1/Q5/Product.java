package Daou.Assignment.MockExam1.Q5;

public class Product implements Identifiable<String> {

    private final String id;
    private final String productName;

    public Product(String id, String productName) {
        // id, productName 모두 null 또는 공백이면 안 된다.
        validate(id);
        validate(productName);

        this.id = id;
        this.productName = productName;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public String getProductName() {
        return productName;
    }

    /**
     * String field에 대해 null 또는 공백 확인
     * @param field
     */
    private void validate(String field) {
        if (field == null || field.isBlank()) {
            throw new IllegalArgumentException(
                    "field must not be null"
            );
        }
    }
}
