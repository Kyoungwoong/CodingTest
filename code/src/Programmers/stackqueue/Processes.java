package Programmers.stackqueue;

import java.util.*;

public class Processes {
//    private static int[] priorities = {2, 1, 3, 2};
//    private static int location = 2;

    private static int[] priorities = {1, 1, 9, 1, 1, 1};
    private static int location = 0;

    static class Process {
        int priority, idx;

        public Process(int idx, int priority) {
            this.idx = idx;
            this.priority = priority;
        }
    }

    private static TreeMap<Integer, Integer> queue = new TreeMap<>(Comparator.reverseOrder());
    private static List<Process> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
//        prev();

    }

    private static int nov8() {
        Queue<Process> processes = new LinkedList<>();
        PriorityQueue<Integer> remainPriority = new PriorityQueue<>(Collections.reverseOrder());

        int len = priorities.length;
        for(int i = 0; i < len; i++) {
            int priority = priorities[i];
            remainPriority.add(priority);
            processes.add(new Process(priority, i));
        }

        int order = 1;
        while(true) {
            Process ready = processes.poll();
            int maxPriority = remainPriority.poll();
            if (maxPriority == ready.priority) {
                if (location == ready.idx) {
                    return order;
                } else {
                    order++;
                }
            } else {
                remainPriority.add(maxPriority);
                processes.add(ready);
            }
        }
    }
    private static void prev() {
        int len = priorities.length;
        for (int i = 0; i < len; i++) {
            list.add(new Process(priorities[i], i));
            queue.put(priorities[i], queue.getOrDefault(priorities[i], 0) + 1);
        }

        int answer = 1;
        while (true) {
            Process p = list.get(0);
            if (queue.firstKey() == p.priority) {
                if (location == p.idx) {
                    System.out.println("answer = " + answer);
                    return;
                } else {
                    list.remove(0);
                    answer++;
                    if (queue.get(p.priority) == 1) {
                        queue.remove(p.priority);
                    } else {
                        queue.put(p.priority, queue.get(p.priority) - 1);
                    }
                }
            } else if (queue.firstKey() > p.priority) {
                list.remove(0);
                list.add(p);
            } else { // (queue.firstKey() < p.priority)

            }
        }
    }
}
