package CodeTree.IntermediateMID.PriorityQueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class RemoveFront {
    private static int n;
    private static int[] arr = new int[100000];
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        pq.add(arr[n-1]);
        double avg = 0;
        int num = arr[n-1];
        for(int i = n-2; i >= 1; i--){
            pq.add(arr[i]);
            num += arr[i];

            avg = Math.max(avg, (double)(num - pq.peek()) / (n - i - 1));
        }
        System.out.printf("%.2f", avg);
    }
}