package CodeTree.IntermediateLow.Backtracking.second;

import java.util.Scanner;

public class Yut {
    public static final int MAX_K = 4;
    public static final int MAX_N = 12;

    public static int n, m, k;
    public static int[] nums = new int[MAX_N];
    public static int[] pieces = new int[MAX_K];

    public static int ans;

    // 점수를 계산합니다.
    public static int calc() {
        int score = 0;
        for(int i = 0; i < k; i++)
            score += (pieces[i] >= m ? 1 : 0);

        return score;
    }

    public static void findMax(int cnt) {
        // 말을 직접 n번 움직이지 않아도
        // 최대가 될 수 있으므로 항상 답을 갱신합니다.
        ans = Math.max(ans, calc());

        // 더 이상 움직일 수 없으면 종료합니다.
        if(cnt == n)
            return;

        for(int i = 0; i < k; i++) {
            // 움직여서 더 이득이 되지 않는
            // 말은 더 이상 움직이지 않습니다.
            if(pieces[i] >= m)
                continue;

            pieces[i] += nums[cnt];
            findMax(cnt + 1);
            pieces[i] -= nums[cnt];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        for(int i = 0; i < n; i++)
            nums[i] = sc.nextInt();

        for(int i = 0; i < k; i++)
            pieces[i] = 1;

        findMax(0);

        System.out.print(ans);
    }
}