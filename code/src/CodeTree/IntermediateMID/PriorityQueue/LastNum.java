package CodeTree.IntermediateMID.PriorityQueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class LastNum {
    private static int n;
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            pq.add(-1 * Integer.parseInt(st.nextToken()));
        }

        while(pq.size() >= 2){
            int first = pq.poll();
            int second = pq.poll();
            if(first != second){
                pq.add(-1 * Math.abs(first - second));
            }
        }

        if(pq.isEmpty()){
            System.out.println(-1);
        }else{
            System.out.println(-1*pq.poll());
        }
    }
}
