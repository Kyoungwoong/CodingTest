package SWExpert.DP;

import java.io.*;
import java.util.*;

public class Q1259 {
    static int T, N;
    static Map<Integer, Integer> chain;
    static Map<Integer, Integer> revChain;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            chain = new HashMap<>();
            revChain = new HashMap<>();

            for (int i = 0; i < N; i++) {
                int male = Integer.parseInt(st.nextToken());
                int female = Integer.parseInt(st.nextToken());
                chain.put(male, female);
                revChain.put(female, male);  // 역방향도 저장
            }

            // 시작점 찾기: 수나사인데, 누구의 암나사에도 안 쓰인 것
            int start = -1;
            for (int key : chain.keySet()) {
                if (!revChain.containsKey(key)) {
                    start = key;
                    break;
                }
            }

            sb.append("#").append(t);

            // 체인 순서대로 출력
            while (chain.containsKey(start)) {
                int end = chain.get(start);
                sb.append(" ").append(start).append(" ").append(end);
                start = end;
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}

/**
 * 수나사가 중복이 있고, 연결될 수 있는 나사의 최대 길이 구하는 코드
 */

/*
static class Bolt {
    int male, female;
    Bolt(int m, int f) {
        male = m; female = f;
    }
}

static List<Bolt> bolts;
static List<Integer>[] graph;
static int[] dp;

public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());

    bolts = new ArrayList<>();
    for (int i = 0; i < N; i++) {
        int m = Integer.parseInt(st.nextToken());
        int f = Integer.parseInt(st.nextToken());
        bolts.add(new Bolt(m, f));
    }

    graph = new ArrayList[N];
    for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (i != j && bolts.get(i).female == bolts.get(j).male) {
                graph[i].add(j);
            }
        }
    }

    dp = new int[N];
    Arrays.fill(dp, -1);
    int ans = 0;
    for (int i = 0; i < N; i++) {
        ans = Math.max(ans, dfs(i));
    }

    System.out.println("최대 체인 길이: " + ans);
}

static int dfs(int now) {
    if (dp[now] != -1) return dp[now];
    int max = 1;
    for (int next : graph[now]) {
        max = Math.max(max, 1 + dfs(next));
    }
    return dp[now] = max;
}

 */