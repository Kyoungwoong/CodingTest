package SWExpert.Heap;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Comparator;

public class MidValue {

    private static int n, a, num, MOD = 20171109;
    private static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    private static PriorityQueue<Integer> minHeap = new PriorityQueue<>();


    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("SW_Testcase/Heap/mid_value.txt"));
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++){
            num = 0;
            maxHeap.clear();
            minHeap.clear();
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            maxHeap.offer(a);
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                int num1 = Integer.parseInt(st.nextToken());
                int num2 = Integer.parseInt(st.nextToken());
                minHeap.offer(num1);
                maxHeap.offer(num2);

                if (!minHeap.isEmpty()) {
                    if (maxHeap.peek() > minHeap.peek()) {
                        int t1 = maxHeap.poll();
                        int t2 = minHeap.poll();

                        maxHeap.offer(t2);
                        minHeap.offer(t1);
                    }
                }
                num += maxHeap.peek();
                num %= MOD;
            }
            sb.append("#" + test_case+" " + num + "\n");
        }
        System.out.println(sb);
    }
}