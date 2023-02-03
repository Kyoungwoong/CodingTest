package CodeTree.IntermediateMID.TreeSet;

import java.util.Scanner;
import java.util.TreeSet;

public class Close {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final int MAX_N = 100000;

    // 변수 선언
    public static int n;
    public static int[] queries = new int[MAX_N];
    public static TreeSet<Integer> s = new TreeSet<>();

    public static int ans = INT_MAX;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        for(int i = 0; i < n; i++)
            queries[i] = sc.nextInt();

        // x = 0 위치에 점을 놓고 시작합니다.
        s.add(0);

        for(int i = 0; i < n; i++) {
            // 가장 근처에 있는 오른쪽 점을 찾습니다.
            // 존재한다면, 거리 중 최솟값을 갱신합니다.
            if(s.higher(queries[i]) != null)
                ans = Math.min(ans, s.higher(queries[i]) - queries[i]);

            // 가장 근처에 있는 왼쪽 점을 찾습니다.
            // 거리 중 최솟값을 갱신합니다.
            ans = Math.min(ans, queries[i] - s.lower(queries[i]));

            // 해당 점을 treeset에 추가합니다.
            s.add(queries[i]);
            System.out.println(ans);
        }
    }
}
