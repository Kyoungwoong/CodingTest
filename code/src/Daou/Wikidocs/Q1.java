package Daou.Wikidocs;

import java.util.*;

abstract class DeliveryStore {
    public abstract void setOrderList(List<String> orderList);
    public abstract int getTotalPrice();
}

class Food {
    String name;
    int price;

    public Food(String name, int price) {
        this.name = name;
        this.price = price;
    }
}

class PizzaStore extends DeliveryStore {

    private List<Food> menuList;
    private List<String> orderList;

    public PizzaStore() {
        String[] menuNames = {"Cheese", "Potato", "Shrimp", "Pineapple", "Meatball"};
        int[] menuPrices = {11100, 12600, 13300, 21000, 19500};

        menuList = new ArrayList<>();
        for (int i = 0; i < menuNames.length; i++) {
            menuList.add(new Food(menuNames[i], menuPrices[i]));
        }

        orderList = new ArrayList<>();
    }

    @Override
    public void setOrderList(List<String> orderList) {
        this.orderList.addAll(orderList);
    }

    @Override
    public int getTotalPrice() {
        int totalPrice = 0;

        for (String order : orderList) {
            for (Food menu : menuList) {
                if (order.equals(menu.name)) {
                    totalPrice += menu.price;
                }
            }
        }
        return totalPrice;
    }
}

public class Q1 {

    public static int solution(List<String> orderList) {
        DeliveryStore deliveryStore = new PizzaStore();
        deliveryStore.setOrderList(orderList);
        return deliveryStore.getTotalPrice();
    }

    public static void main(String[] args) {
        List<String> orderList = Arrays.asList("Cheese", "Pineapple", "Meatball");
        int ret = solution(orderList);
        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");
    }
}
