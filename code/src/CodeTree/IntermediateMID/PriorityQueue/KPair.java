package CodeTree.IntermediateMID.PriorityQueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Arrays;

class Info implements Comparable<Info>{
    int sum, AIdx, BIdx;
    public Info(int sum, int AIdx, int BIdx){
        this.sum = sum;
        this.AIdx = AIdx;
        this.BIdx = BIdx;
    }

    @Override
    public int compareTo(Info i){
        return this.sum - i.sum;
    }
}

public class KPair {
    private static int n, m, k;
    private static int[] A = new int[100000];
    private static int[] B = new int[100000];
    private static PriorityQueue<Info> ans = new PriorityQueue<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A, 0, n);

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            B[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(B, 0, m);

        for(int i = 0; i < n; i++){
            ans.add(new Info(A[i] + B[0], i, 0));
        }

        for(int i = 0; i < k-1; i++){
            Info smallest = ans.poll();
            // System.out.println(smallest.sum + " " + smallest.AIdx + " " + smallest.BIdx);

            int idx1 = smallest.AIdx;
            int idx2 = smallest.BIdx;
            idx2++;
            if(idx2 < m){
                ans.add(new Info(A[idx1]+B[idx2], idx1, idx2));
            }
        }
        // System.out.println(ans.peek().sum + " " + ans.peek().AIdx + " " + ans.peek().BIdx);
        System.out.println(ans.poll().sum);
    }
}
