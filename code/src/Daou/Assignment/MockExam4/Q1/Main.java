package Daou.Assignment.MockExam4.Q1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> seats = new ArrayList<>(
            List.of("A3", "A1", "A2")
        );

        Concert concert = new Concert("C001", seats);

        System.out.println(concert.reserve("A1", "user1"));
        System.out.println(concert.reserve("A1", "user2"));
        System.out.println(concert.reserve("A2", "user2"));

        System.out.println(concert.getReservedCount());

        System.out.println(concert.cancel("A1", "user2"));
        System.out.println(concert.cancel("A1", "user1"));

        System.out.println(concert.getAvailableSeats());

        seats.add("A4");

        System.out.println(concert.getReservedCount());
    }
}
