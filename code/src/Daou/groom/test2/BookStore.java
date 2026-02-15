package Daou.groom.test2;

import java.util.*;

interface Book {
    int getRentalPrice(int day);
}

class ComicBook implements Book {   // 빈칸 ①

    @Override
    public int getRentalPrice(int day) {            // 빈칸 ②
        int cost = 500;
        day -= 2;
        if (day > 0) {
            cost += 200 * day;        // 빈칸 ③
        }
        return cost;
    }
}

class Novel implements Book {       // 빈칸 ④

    @Override
    public int getRentalPrice(int day) {            // 빈칸 ⑤
        int cost = 1000;
        day -= 3;
        if (day > 0) {
            cost += 300 * day;        // 빈칸 ⑥
        }
        return cost;
    }
}

public class BookStore {

    public static int solution(String[] bookTypes, int day) {

        List<Book> books = new ArrayList<>();

        for (String type : bookTypes) {
            if (type.equals("comic")) {
                books.add(new ComicBook());
            } else if (type.equals("novel")) {
                books.add(new Novel());
            }
        }

        int totalPrice = 0;
        for (Book book : books) {
            totalPrice += book.getRentalPrice(day);
        }

        return totalPrice;
    }
}
