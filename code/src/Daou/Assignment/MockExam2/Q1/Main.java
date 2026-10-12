package Daou.Assignment.MockExam2.Q1;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory("P001", 10);

        inventory.reserve(4);

        System.out.println(inventory.getAvailableStock());
        System.out.println(inventory.getReservedStock());

        inventory.cancel(2);

        System.out.println(inventory.getAvailableStock());
        System.out.println(inventory.getReservedStock());
    }
}
