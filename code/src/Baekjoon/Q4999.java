package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q4999 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static String jh, doctor;

    public static void main(String[] args) throws IOException {
        jh = br.readLine();
        doctor = br.readLine();

        if (jh.charAt(0) == doctor.charAt(0) && jh.charAt(0) == 'a') {
            if (jh.charAt(jh.length() - 1) == doctor.charAt(doctor.length() - 1) &&
                    jh.charAt(jh.length() - 1) == 'h') {
                if (jh.length() >= doctor.length()) {
                    System.out.println("go");
                } else {
                    System.out.println("no");
                }
            }
        }
    }
}
