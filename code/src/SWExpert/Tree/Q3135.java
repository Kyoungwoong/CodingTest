package SWExpert.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q3135 {
    static class Node {
        String word;
        Map<Character, Node> next = new HashMap<>();
        int count = 0; // 경로상에 몇 개의 단어가 지나갔는지

        public Node(String word) {
            this.word = word;
        }
    }

    private static int tc, N;
    private static Node root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        StringBuilder ans = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= tc; t++) {
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(t).append(" ");
            N = Integer.parseInt(br.readLine());
            root = new Node(null); // 트라이 초기화

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int command = Integer.parseInt(st.nextToken());
                String query = st.nextToken();
                if (command == 1) {
                    insert(query);
                } else {
                    sb.append(getQuery(query)).append(" ");
                }
            }
            sb.append("\n");
            ans.append(sb);
        }
        System.out.println(ans);
    }

    // 문자열 삽입
    private static void insert(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            cur.next.putIfAbsent(c, new Node(String.valueOf(c)));
            cur = cur.next.get(c);
            cur.count++; // 해당 경로를 지나가는 문자열 수 증가
        }
    }

    // 쿼리 길이 or 개수 반환 (문제에 따라 수정 가능)
    private static int getQuery(String query) {
        Node cur = root;
        for (int i = 0; i < query.length(); i++) {
            char c = query.charAt(i);
            if (!cur.next.containsKey(c)) {
                return 0;
            }
            cur = cur.next.get(c);
        }
        return cur.count; // 또는 cur.count, 문제 요구에 맞게
    }
}
