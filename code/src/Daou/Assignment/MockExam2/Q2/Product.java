package Daou.Assignment.MockExam2.Q2;

public class Product implements Identifiable<Long> {

    private final Long id;
    private final String name;

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}