package CodeTree.IntermediateMID.PriorityQueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Small3 {
    private static int n;
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            pq.add(Integer.parseInt(st.nextToken()));
            if(pq.size() >= 3){
                long a = pq.poll();
                long b = pq.poll();
                long c = pq.poll();
                sb.append(a*b*c + "\n");
                pq.add((int)a);
                pq.add((int)b);
                pq.add((int)c);
            }else{
                sb.append("-1\n");
            }
        }
        System.out.println(sb);
    }
}
