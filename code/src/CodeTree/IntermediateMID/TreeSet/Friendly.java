package CodeTree.IntermediateMID.TreeSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;
// import java.util.Comparable;

class Dot implements Comparable<Dot>{
    int x, y;
    public Dot(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Dot dot){
        if(this.x == dot.x){
            return (this.y-dot.y);
        }else{
            return (this.x-dot.x);
        }
    }
}

public class Friendly {
    private static int n, m;
    private static TreeSet<Dot> s = new TreeSet<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            s.add(new Dot(x, y));
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if(s.ceiling(new Dot(x, y)) == null){
                sb.append("-1 -1\n");
            }else{
                Dot friendlyDot = s.ceiling(new Dot(x, y));
                sb.append(friendlyDot.x + " " + friendlyDot.y + "\n");
            }
        }
        System.out.println(sb);
    }
}
