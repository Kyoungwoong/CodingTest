package Daou.groom.test3;

import java.util.*;

public class Q9 {

    static class Customer {
        int id;
        int time;
        int num_of_people;

        Customer(int id, int time, int num_of_people) {
            this.id = id;
            this.time = time;
            this.num_of_people = num_of_people;
        }
    }

    static class Shop {
        List<Customer> reserve_list;

        Shop() {
            this.reserve_list = new ArrayList<>();
        }

        boolean reserve(Customer customer) {
            reserve_list.add(customer);
            return true;
        }
    }

    static class HairShop extends Shop {

        boolean reserve(Customer customer) {
            if (customer.num_of_people != 1) {
                return false;
            }
            for (Customer r : this.reserve_list) {
                if (r.time == customer.time) {
                    return false;
                }
            }
            this.reserve_list.add(customer);
            return true;
        }
    }

    static class Restaurant extends Shop {

        boolean reserve(Customer customer) {
            if (customer.num_of_people >= 2 && customer.num_of_people <= 8) {
                return false;
            }
            int count = 0;
            for (Customer r : this.reserve_list) {
                if (r.time == customer.time) {
                    count += 1;
                }
            }
            if (count >= 2) {
                return false;
            }
            this.reserve_list.add(customer);
            return true;
        }
    }

    static int solution(int[][] customers, String[] shops) {
        HairShop hairshop = new HairShop();
        Restaurant restaurant = new Restaurant();

        int count = 0;
        int len = Math.min(customers.length, shops.length);

        for (int i = 0; i < len; i++) {
            int[] customer = customers[i];
            String shop = shops[i];

            if (shop.equals("hairshop")) {
                if (hairshop.reserve(new Customer(customer[0], customer[1], customer[2]))) {
                    count += 1;
                }
            } else if (shop.equals("restaurant")) {
                if (restaurant.reserve(new Customer(customer[0], customer[1], customer[2]))) {
                    count += 1;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] customers = {
                {1000, 2, 1},
                {2000, 2, 4},
                {1234, 5, 1},
                {4321, 2, 1},
                {1111, 3, 10}
        };
        String[] shops = {"hairshop", "restaurant", "hairshop", "hairshop", "restaurant"};
        int ret = solution(customers, shops);

        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");
    }
}
