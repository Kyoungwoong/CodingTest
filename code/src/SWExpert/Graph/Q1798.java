package SWExpert.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1798 {
    static class Point {
        String type;
        int during, satisfy;

        public Point(String type) {
            this.type = type;
        }
    }

    static class Edge {
        int dest, cost;

        public Edge(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    static int tc, N, M, maxSatisfaction, airportId;
    static Map<Integer, Point> pointMap;
    static List<List<Edge>> adjList;
    static List<Integer> hotels, sights;
    static List<Integer> bestPath;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            adjList = new ArrayList<>();
            for (int i = 0; i < N; i++) adjList.add(new ArrayList<>());

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = i + 1; j < N; j++) {
                    int d = Integer.parseInt(st.nextToken());
                    adjList.get(i).add(new Edge(j, d));
                    adjList.get(j).add(new Edge(i, d));
                }
            }

            pointMap = new HashMap<>();
            hotels = new ArrayList<>();
            sights = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String type = st.nextToken();
                Point p = new Point(type);
                if (type.equals("P")) {
                    p.during = Integer.parseInt(st.nextToken());
                    p.satisfy = Integer.parseInt(st.nextToken());
                    sights.add(i);
                } else if (type.equals("H")) {
                    hotels.add(i);
                } else {
                    airportId = i;
                }
                pointMap.put(i, p);
            }

            maxSatisfaction = 0;
            bestPath = new ArrayList<>();
            dfs(airportId, new boolean[N], 0, 0, 1, new ArrayList<>());

            sb.append(maxSatisfaction).append(" ").append(bestPath).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int pos, boolean[] visited, int timeSpent, int satisfaction, int day, List<Integer> path) {
        if (day == M && pos == airportId) {
            if (satisfaction > maxSatisfaction) {
                maxSatisfaction = satisfaction;
                bestPath = new ArrayList<>(path);
            }
            return;
        }

        for (int sight : sights) {
            if (visited[sight]) continue;
            int moveTime = getCost(pos, sight);
            int playTime = pointMap.get(sight).during;
            int toAirport = getCost(sight, 0);

            if (day == M && timeSpent + moveTime + playTime + toAirport > 540) continue;

            int nextTime = timeSpent + moveTime + playTime;
            if (nextTime <= 540) {
                visited[sight] = true;
                path.add(sight + 1);
                dfs(sight, visited, nextTime, satisfaction + pointMap.get(sight).satisfy, day, path);
                path.remove(path.size() - 1);
                visited[sight] = false;
            }
        }

        if (day < M) {
            for (int hotel : hotels) {
                int moveToHotel = getCost(pos, hotel);
                if (timeSpent + moveToHotel <= 540) {
                    path.add(hotel + 1);
                    dfs(hotel, visited, 0, satisfaction, day + 1, path);
                    path.remove(path.size() - 1);
                }
            }
        } else if (day == M && pos != airportId) {
            int backToAirport = getCost(pos, airportId);
            if (timeSpent + backToAirport <= 540) {
                path.add(airportId + 1);
                dfs(airportId, visited, timeSpent + backToAirport, satisfaction, day, path);
                path.remove(path.size() - 1);
            }
        }
    }

    static int getCost(int from, int to) {
        for (Edge edge : adjList.get(from)) {
            if (edge.dest == to) return edge.cost;
        }
        return Integer.MAX_VALUE;
    }
}

/*
1
10 3
60 90 100 110 240 40 30 60 30
60 120 140 200 120 100 60 60
90 110 170 100 100 30 90
50 110 40 80 90 90
70 30 50 90 90
100 140 180 120
40 90 40
100 20
70
A
P 240 6
P 120 4
P 240 5
P 60 4
P 200 6
P 180 1
P 180 1
H
H
 */