package ThisIsCodingTest.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Rank {
    private static int N, M;
    private static boolean[] visited;
    private static ArrayList<ArrayList<Integer>> smaller = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> larger = new ArrayList<>();

    public static boolean isPrecise(int target) {
        visited[target] = true;
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < smaller.get(target).size(); i++) {
            int next = smaller.get(target).get(i);
            q.add(next);

            while (!q.isEmpty()) {
                int cur = q.poll(); //4
                visited[cur] = true;
                for (int j = 0; j < smaller.get(cur).size(); j++) {
                    q.add(smaller.get(cur).get(j));
                }
            }
        }

        for (int i = 0; i < larger.get(target).size(); i++) {
            int next = larger.get(target).get(i);
            q.add(next);

            while (!q.isEmpty()) {
                int cur = q.poll();
                visited[cur] = true;
                for (int j = 0; j < larger.get(cur).size(); j++) {
                    q.add(larger.get(cur).get(j));
                }
            }
        }


        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            smaller.add(new ArrayList<>());
            larger.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            larger.get(a).add(b);
            smaller.get(b).add(a);
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            if (isPrecise(i)) {
                System.out.println("i = " + i);
                count++;
            }
        }
        System.out.println("count = " + count);

    }
}
/*
6 6
1 5
3 4 
4 2
4 6
5 2
5 4
--- 1
 */