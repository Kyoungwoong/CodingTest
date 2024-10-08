package Programmers.BFSDFS;

import java.util.*;

public class Travel {
//    private static String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}}; // ["ICN", "JFK", "HND", "IAD"]
    private static String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}}; // ["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"]

    private static Map<String, PriorityQueue<String>> airport = new HashMap<>();
    private static ArrayList<String> arr = new ArrayList<>();
    private static int n;

    public static void main(String[] args) {
        n = tickets.length;

        for (String[] ticket : tickets) {
            String src = ticket[0];
            String desc = ticket[1];
            airport.computeIfAbsent(src, k -> new PriorityQueue<>()).add(desc);
        }
        arr.add("ICN");
        dfs("ICN");

//        System.out.println(arr);
    }

    public static void dfs(String start) {
        if (arr.size() == n + 1) {
            for (String s : arr) {
                System.out.print(s + " ");
            }
            System.out.println();
            System.out.println(arr.toString());
            System.exit(0);
        }

        if (airport.containsKey(start)) {
            PriorityQueue<String> pq = airport.get(start);
            while (!pq.isEmpty()) {
                String desc = pq.poll();
                arr.add(desc);
                dfs(desc);
                arr.remove(arr.size() - 1);
                pq.add(desc);
            }
        }
    }
}
