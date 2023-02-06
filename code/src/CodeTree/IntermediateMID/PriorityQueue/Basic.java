package CodeTree.IntermediateMID.PriorityQueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Basic {
    private static int n, num;
    private static String cmd;
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            cmd = st.nextToken();
            switch(cmd){
                case "push":
                    num = Integer.parseInt(st.nextToken());
                    pq.add(-num);
                    break;

                case "size":
                    sb.append(pq.size() + "\n");
                    break;

                case "empty":
                    if(pq.isEmpty()){
                        sb.append(1 + "\n");
                    }else{
                        sb.append(0 + "\n");
                    }
                    break;

                case "pop":
                    sb.append(-pq.poll() + "\n");
                    break;

                case "top":
                    sb.append(-pq.peek() + "\n");
                    break;
            }
        }
        System.out.println(sb);
    }
}
