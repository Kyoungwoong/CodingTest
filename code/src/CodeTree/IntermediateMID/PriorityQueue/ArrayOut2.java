package CodeTree.IntermediateMID.PriorityQueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

class INT implements Comparable<INT>{
    int num, minus;
    public INT(int num, int minus){
        this.num = num;
        this.minus = minus;
    }

    @Override
    public int compareTo(INT i){
        if(this.num == i.num){
            return this.minus - i.minus;
        }else{
            return this.num - i.num;
        }
    }
}

public class ArrayOut2 {
    private static int n, num;
    private static PriorityQueue<INT> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            num = Integer.parseInt(br.readLine());
            if(num > 0){
                pq.add(new INT(num, 1));
            }else if(num < 0){
                pq.add(new INT(Math.abs(num), 0));
            }else{
                if(pq.size() == 0){
                    sb.append(0 + "\n");
                    continue;
                }

                INT p = pq.poll();

                if(p.minus == 0){
                    sb.append(-1*p.num + "\n");
                }else{
                    sb.append(p.num+"\n");
                }

            }
        }
        System.out.println(sb);
    }
}
