package Programmers.Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class DoublePQ {
//    private static String[] operations = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"}; // [0, 0]
    private static String[] operations = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"}; // [333, -45]

    public static void main(String[] args) {
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());

        for (String operation : operations) {
            String[] split = operation.split(" ");
            String command = split[0];
            int number = Integer.parseInt(split[1]);

            switch (command) {
                case "I":
                    min.add(number);
                    max.add(number);
                    break;
                case "D":
                    if (number == 1 && !max.isEmpty()) {
                        int maxNum = max.poll();
                        min.remove(maxNum);
                    } else if (number == -1 && !min.isEmpty()) {
                        int minNum = min.poll();
                        max.remove(minNum);
                    }
                    break;
            }
        }

        if (min.isEmpty() && max.isEmpty()) {
            System.out.println("[0,0] ");
        } else {
            System.out.println("min.poll() = " + min.poll());
            System.out.println("max.poll( = " + max.poll());
        }
    }
}
