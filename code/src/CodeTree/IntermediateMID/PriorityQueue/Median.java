package CodeTree.IntermediateMID.PriorityQueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Median {
    private static int t, m;
    private static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    private static PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++){
            maxHeap.clear();
            minHeap.clear();
            m = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= m; j++){
                int num = Integer.parseInt(st.nextToken());

                if (maxHeap.size() == minHeap.size()) {
                    maxHeap.offer(num);
                } else {
                    minHeap.offer(num);
                }

                // maxHeap은 중앙값 이하의 숫자만 갖도록 조정.
                if (!minHeap.isEmpty()) {
                    if (maxHeap.peek() > minHeap.peek()) {
                        int t1 = maxHeap.poll();
                        int t2 = minHeap.poll();

                        maxHeap.offer(t2);
                        minHeap.offer(t1);
                    }
                }

                if(j%2 == 1){
                    sb.append(maxHeap.peek() + " ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
