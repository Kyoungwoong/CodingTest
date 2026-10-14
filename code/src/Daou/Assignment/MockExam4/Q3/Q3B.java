package Daou.Assignment.MockExam4.Q3;

public class Q3B {

    static int calculate() {
        int value = 5;

        try {
            value *= 2;
            return value;
        } finally {
            value += 20;
            System.out.println("finally: " + value);
        }
    }

    public static void main(String[] args) {
        System.out.println("result: " + calculate());
    }
}