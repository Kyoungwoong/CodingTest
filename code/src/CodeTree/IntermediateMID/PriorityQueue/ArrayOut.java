package CodeTree.IntermediateMID.PriorityQueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class ArrayOut {
    private static int n, cmd;
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            cmd = Integer.parseInt(br.readLine());
            if(cmd > 0){
                pq.add(-1*cmd);
            }else{
                if(pq.isEmpty()){
                    sb.append(0 + "\n");
                }else{
                    sb.append(-1*pq.poll() + "\n");
                }

            }
        }
        System.out.println(sb);
    }
}
