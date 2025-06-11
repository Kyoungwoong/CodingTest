package SWExpert.DP;

import java.util.*;

public class TSP {
    static int N;
    static int[][] dist;
    static int[][] dp;
    static final int INF = Integer.MAX_VALUE / 2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dist = new int[N][N];
        dp = new int[N][1 << N];

        // 거리 입력
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                dist[i][j] = sc.nextInt();

        // DP 초기화
        for (int i = 0; i < N; i++)
            Arrays.fill(dp[i], -1);

        int result = tsp(0, 1); // 0번 도시부터 시작, 0번 도시만 방문한 상태
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 1 << N; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("최소 비용: " + result);
    }

    // 현재 도시 current, 방문한 도시 상태 visited(bitmask)
    static int tsp(int current, int visited) {
//        System.out.println("current: " + current + " visited: " + visited);
        if (visited == (1 << N) - 1) {
            // 모든 도시 방문 → 시작점으로 돌아가기
            return dist[current][0] == 0 ? INF : dist[current][0];
        }

        if (dp[current][visited] != -1) return dp[current][visited];

        int min = INF;
        for (int next = 0; next < N; next++) {
            if ((visited & (1 << next)) == 0 && dist[current][next] != 0) {
                int cost = dist[current][next] + tsp(next, visited | (1 << next));
                min = Math.min(min, cost);
            }
        }

        return dp[current][visited] = min;
    }
}

/*
4
0 10 15 20
10 0 35 25
15 35 0 30
20 25 30 0
 */

/**
 * 문제 개념 요약
 * 목표:
 * N개의 도시가 있고, 각 도시 사이의 이동 비용이 주어졌을 때,
 * 모든 도시를 한 번씩만 방문하고,
 * 다시 시작한 도시로 돌아오는 최소 비용을 구하는 문제입니다.
 *
 * 예를 들어
 * A → B → C → D → A 로 순회할 때의 최소 비용을 구하는 것입니다.
 * 이건 완전탐색(모든 순열 탐색)으로 풀 수 있지만, 도시 수가 늘어나면 너무 오래 걸립니다.
 * → 그래서 동적계획법 + 비트마스킹을 사용합니다.
 *
 * ✅ 기본 아이디어
 * 우리는 다음 두 정보를 기준으로 중복 계산을 피하면서 경로를 구성합니다.
 *
 * 현재 위치한 도시: current
 *
 * 지금까지 방문한 도시들: visited (bitmask로 표현)
 *
 * → dp[current][visited]를 메모이제이션 테이블로 사용합니다.
 * → dp[i][mask]는:
 *
 * i 도시에서 시작해서,
 * mask로 표현된 도시들을 이미 방문했을 때
 * 남은 모든 도시를 방문하고 시작점으로 돌아가는 최소 비용
 *
 * ✅ 용어 해설
 * 📌 비트마스킹이란?
 * 도시가 4개 있다고 해봅시다.
 *
 * 0001 : 0번 도시만 방문
 *
 * 0011 : 0번, 1번 도시 방문
 *
 * 1111 : 모든 도시 방문 (종료 조건)
 *
 * 📌 점화식 구성
 * java
 * 코드 복사
 * tsp(current, visited)
 * 현재 위치한 도시: current
 *
 * 방문한 도시: visited (bitmask로 표현됨)
 *
 * 다음 도시 next를 정해주고,
 * 이동해서 얻을 수 있는 비용 + 남은 경로의 최소 비용을 구합니다.
 *
 * ✅ 알고리즘 흐름 설명
 * 🔷 1. 입력 및 초기화
 * java
 * 코드 복사
 * N = sc.nextInt();                      // 도시 수
 * dist = new int[N][N];                 // 거리 배열
 * dp = new int[N][1 << N];             // DP 테이블 (도시, 방문 상태)
 *
 * for (int i = 0; i < N; i++)
 *     Arrays.fill(dp[i], -1);          // -1로 초기화 (아직 계산 안 했다는 뜻)
 * 🔷 2. 시작: 도시 0에서 출발
 * java
 * 코드 복사
 * int result = tsp(0, 1); // 0번 도시에서 시작했고, 0번 도시만 방문한 상태
 * 🔷 3. 재귀 함수 tsp(current, visited)
 * java
 * 코드 복사
 * // 모든 도시를 다 방문했다면?
 * if (visited == (1 << N) - 1) {
 *     return dist[current][0]; // 시작 도시로 돌아가는 비용
 * }
 * ✔ 비트마스크 예시 (N = 4):
 * 상태	visited (이진수)	의미
 * 1	0001	0번 도시만 방문
 * 3	0011	0번, 1번 도시 방문
 * 15	1111	0,1,2,3 전부 방문 완료
 *
 * 🔷 4. 다음 도시로 이동
 * java
 * 코드 복사
 * for (int next = 0; next < N; next++) {
 *     // 방문 안 했고, 길이 존재하는 경우
 *     if ((visited & (1 << next)) == 0 && dist[current][next] != 0) {
 *         int cost = dist[current][next] + tsp(next, visited | (1 << next));
 *         min = Math.min(min, cost);
 *     }
 * }
 * 🔸 설명:
 * (visited & (1 << next)) == 0
 * → 아직 next 도시를 방문하지 않았는지 확인
 *
 * visited | (1 << next)
 * → next 도시를 방문했다고 상태 업데이트
 *
 * 🔷 5. 결과 저장 (메모이제이션)
 * java
 * 코드 복사
 * return dp[current][visited] = min;
 * 이미 계산한 값은 다시 계산하지 않고 사용합니다.
 *
 * ✅ 시각화 예시
 * 예: 도시 4개, 거리행렬
 *
 * 코드 복사
 * 0 10 15 20
 * 10 0 35 25
 * 15 35 0 30
 * 20 25 30 0
 * 시작: 0 → 1 → 3 → 2 → 0
 *
 * 최소 비용 경로 중 하나이며, 결과는 80이 됩니다.
 *
 * ✅ 시간 복잡도
 * 도시 개수 N일 때:
 *
 * DP 배열 크기: N * 2^N
 * 각 상태마다 O(N)만큼 탐색 →
 * 총 시간 복잡도: O(N^2 * 2^N)
 *
 * → 보통 N ≤ 16일 때 실용적입니다.
 */