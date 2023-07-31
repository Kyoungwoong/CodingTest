package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pos {
    int pos, cnt;

    public Pos(int pos, int cnt) {
        this.pos = pos;
        this.cnt = cnt;
    }
}

public class Q16928 {
    private static int N, M;
    private static int[] dice = {1, 2, 3, 4, 5, 6};
    private static HashMap<Integer, Integer> ladder = new HashMap<>();
    private static HashSet<Integer> visited = new HashSet<>();

    public static void bfs(){
        Queue<Pos> q = new LinkedList<>();
        for (int i = 2; i <= 7; i++) {
            if (ladder.containsKey(i)) {
                q.add(new Pos(ladder.get(i), 1));
            } else {
                q.add(new Pos(i, 1));
            }
        }

        while (!q.isEmpty()) {
            Pos cur = q.poll();
            for (int i = 0; i < 6; i++) {
                int nextP = cur.pos + dice[i];
                int nextC = cur.cnt + 1;
                if (nextP == 100) {
                    System.out.println(nextC);
                    System.exit(0);
                } else if (nextP > 100) {
                    continue;
                }
                if (ladder.containsKey(nextP)) {
                    q.add(new Pos(ladder.get(nextP), nextC));
                } else {
                    if(!visited.contains(nextP)){
                        q.add(new Pos(nextP, nextC));
                        visited.add(nextP);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            ladder.put(x, y);
        }

        bfs();

    }
}
