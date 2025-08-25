package Baekjoon;

import java.time.LocalDate;
import java.time.ZoneId;

public class Q10699 {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Seoul"));
        System.out.println(today);
    }
}
