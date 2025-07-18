package SWExpert.BackTracking;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DiscreteMathTools {

    static class Util {
        private static void printList(List<Integer> list) {
            for (int num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }
    }
    static class Combination {
        private static List<Integer> list = new ArrayList<>();
        public static void comb(int n, int r) { // nCr
            System.out.println("================== 조합코드 ================");
            list = new ArrayList<>();
            dfs(0, 1, r, n);
        }

        private static void dfs(int depth, int pos, int limit, int n) {
            if (depth == limit) {
                Util.printList(list);
                return;
            }

            for (int i = pos; i <= n; i++) {
                list.add(i);
                dfs(depth + 1, i + 1, limit, n);
                list.remove(list.size() - 1);
            }
        }

        public static void multiComb(int n, int r) { // 중복 조합
            System.out.println("================== 중복 조합코드 ================");
            list = new ArrayList<>();
            multiDFS(0, 1, r, n);
        }

        private static void multiDFS(int depth, int pos, int limit, int n) {
            if (depth == limit) {
                Util.printList(list);
                return;
            }

            for (int i = pos; i <= n; i++) {
                list.add(i);
                multiDFS(depth + 1, i, limit, n);
                list.remove(list.size() - 1);
            }
        }
    }

    static class Permutation {
        private static List<Integer> list = new ArrayList<>();
        private static boolean[] visited;
        public static void permute(int n, int r) { // nPr
            System.out.println("================== 순열코드 ================");
            list = new ArrayList<>();
            visited = new boolean[n + 1];
            dfs(0, n, r, visited);
        }

        private static void dfs(int depth, int n, int limit, boolean[] visited) {
            if (depth == limit) {
                Util.printList(list);
                return;
            }

            for (int i = 1; i <= n; i++) {
                if (visited[i]) continue;
                visited[i] = true;
                list.add(i);
                dfs(depth + 1, n, limit, visited);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }

        public static void multiPermute(int n, int c) { // 중복 조합
            System.out.println("================== 중복 순열코드 ================");
            list = new ArrayList<>();
            multiDFS(0, 1, c, n);
        }

        private static void multiDFS(int depth, int pos, int limit, int n) {
            if (depth == limit) {
                Util.printList(list);
                return;
            }

            for (int i = 1; i <= n; i++) {
                list.add(i);
                multiDFS(depth + 1, pos, limit, n);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Combination.comb(4, 3);
        Combination.multiComb(4, 3);
        Permutation.permute(4, 3);
        Permutation.multiPermute(4, 3);
    }
}
