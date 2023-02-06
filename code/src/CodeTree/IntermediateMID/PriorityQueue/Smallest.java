package CodeTree.IntermediateMID.PriorityQueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Smallest {
    private static int n, cmd;
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            cmd = Integer.parseInt(br.readLine());
            if(cmd == 0){
                if(pq.isEmpty()){
                    sb.append(0+"\n");
                }else{
                    sb.append(pq.poll() + "\n");
                }

            }else{
                pq.add(cmd);
            }
        }
        System.out.println(sb);
    }
}
