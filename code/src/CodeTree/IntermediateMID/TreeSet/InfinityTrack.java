package CodeTree.IntermediateMID.TreeSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Runner implements Comparable<Runner>{
    int start, end, velocity;
    public Runner(int start, int velocity, int end){
        this.start = start;
        this.velocity = velocity;
        this.end = end;
    }

    @Override
    public int compareTo(Runner r){
        if(this.end != r.end){
            return this.start - r.start; // start 기준으로 오름차순
        }else{
            return this.end - r.end;
        }
    }
}

public class InfinityTrack {
    private static int n, t;
    private static TreeSet<Runner> track = new TreeSet<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            //ceiling 크거나 같은 floor 작거나 같은 higher 큰 최초의 수 lower 작은 최초의 수
            Runner add = new Runner(s, v, s+v*t);

            track.add(add);
            // 이미 출발점은 나보다 뒤

            Runner find = track.ceiling(add);
            if(find != null){
                // if(find.end >= add.end){
                //     track.remove(add);
                // }
                if(find.start < add.start){
                    track.remove(add);
                }
            }

        }

        System.out.println(track.size());

        // Runner test = track.first();
        // while(test != null){
        //     System.out.println(test.start + " " + test.velocity + " "+ test.end);
        //     test = track.higher(test);
        // }
    }
}
