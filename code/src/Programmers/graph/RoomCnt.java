package Programmers.graph;

import java.util.*;

public class RoomCnt {

    static class Solution {
        static class Pair {
            int x, y;

            public Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                Pair pair = (Pair) obj;
                return x == pair.x && y == pair.y;
            }

            @Override
            public int hashCode() {
                return Objects.hash(x, y);
            }
        }

        public int solution(int[] arrows) {
            int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
            int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};

            // 방문 여부 확인용 자료구조
            Set<Pair> visitedNodes = new HashSet<>();
            Set<String> visitedEdges = new HashSet<>();

            // 시작점 설정
            Pair current = new Pair(0, 0);
            visitedNodes.add(current);
            int rooms = 0;

            for (int arrow : arrows) {
                // 각 방향으로 2번 이동해 교차 간선 처리
                for (int step = 0; step < 2; step++) {
                    Pair next = new Pair(current.x + dx[arrow], current.y + dy[arrow]);

                    // 간선 방문 여부 체크
                    String edge1 = current.x + "," + current.y + "->" + next.x + "," + next.y;
                    String edge2 = next.x + "," + next.y + "->" + current.x + "," + current.y;

                    if (visitedNodes.contains(next)) {
                        if (!visitedEdges.contains(edge1)) {
                            rooms++;
                        }
                    }

                    // 정점과 간선 방문 처리
                    visitedNodes.add(next);
                    visitedEdges.add(edge1);
                    visitedEdges.add(edge2);

                    // 다음 이동
                    current = next;
                }
            }

            return rooms;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[]{6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0}) == 3);
    }
}
