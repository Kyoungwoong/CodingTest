package CodeTree.IntermediateMID.PriorityQueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

class Dot implements Comparable<Dot>{
    int x, y;
    public Dot(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Dot dot){
        if((Math.abs(this.x) + Math.abs(this.y)) == (Math.abs(dot.x) + Math.abs(dot.y))){
            if(this.x == dot.x){
                return this.y - dot.y;
            }else{
                return this.x - dot.x;
            }
        }else{
            return (Math.abs(this.x) + Math.abs(this.y)) - (Math.abs(dot.x) + Math.abs(dot.y));
        }
    }
}

public class MySort {
    private static int n, m;
    private static PriorityQueue<Dot> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            Dot newDot = new Dot(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            pq.add(newDot);
        }

        for(int i = 0; i < m; i++){
            Dot smallest = pq.poll();
            Dot addDot = new Dot(smallest.x+2, smallest.y+2);
            pq.add(addDot);
        }
        Dot smallest = pq.poll();
        System.out.println(smallest.x + " " + smallest.y);
    }
}
