package ThisIsCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UnionFind {
    private static int[] parent;

    private static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if(a < b){
            parent[b] = a;
        }else{
            parent[a] = b;
        }
    }

    private static int findParent(int n) {
        if(parent[n] != n){
            parent[n] = findParent(parent[n]);
        }
        return parent[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        System.out.println("루트 테이블 내용 출력: ");
        for(int i = 1; i <= n; i++){
            System.out.print(findParent(i) + " ");
        }
        System.out.println();
        System.out.println("부모 테이블 내용 출력: ");
        for(int i = 1; i <= n; i++){
            System.out.print(parent[i] + " ");
        }

    }
}
/*
6 4
1 4
2 3
2 4
5 6
 */
