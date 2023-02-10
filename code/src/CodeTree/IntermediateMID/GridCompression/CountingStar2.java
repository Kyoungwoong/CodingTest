package CodeTree.IntermediateMID.GridCompression;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.HashMap;

class Dot implements Comparable<Dot>{
    int x, y;
    public Dot(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Dot d){
        if(this.x == d.x){
            return this.x - d.x;
        }else{
            return this.y - d.y;
        }
    }
}

public class CountingStar2 {
    private static int n, q;
    private static Dot[] points = new Dot[1000];
    private static int[][] prefixSum = new int[2002][2002];
    private static TreeSet<Integer> dots = new TreeSet<>();
    private static HashMap<Integer, Integer> mapper = new HashMap<>();

    public static int getLower(int num){
        if(dots.ceiling(num) != null){
            return mapper.get(dots.ceiling(num));
        }
        return dots.size() + 1;
    }

    public static int getUpper(int num){
        if(dots.floor(num) != null){
            return mapper.get(dots.floor(num));
        }
        return 0;
    }

    public static int getAns(int x1, int y1, int x2, int y2){
        return prefixSum[x2][y2] - prefixSum[x1-1][y2] - prefixSum[x2][y1-1] + prefixSum[x1-1][y1-1];
    }

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            points[i] = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            dots.add(points[i].x);
            dots.add(points[i].y);
        }

        int cnt = 1;
        for(Integer dot: dots){
            mapper.put(dot, cnt);
            cnt++;
        }

        for(int i = 0; i < n; i++){
            int x = mapper.get(points[i].x);
            int y = mapper.get(points[i].y);

            prefixSum[x][y]++;
        }

        for(int i = 1; i <= cnt; i++){
            for(int j = 1; j <= cnt; j++){
                prefixSum[i][j] += prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1];
            }
        }

        for(int i = 0; i < q; i++){
            st = new StringTokenizer(br.readLine());
            int sa = Integer.parseInt(st.nextToken());
            int stb = Integer.parseInt(st.nextToken());
            int ea = Integer.parseInt(st.nextToken());
            int eb = Integer.parseInt(st.nextToken());

            sa = getLower(sa);
            stb = getLower(stb);
            ea = getUpper(ea);
            eb = getUpper(eb);

            int ans = getAns(sa, stb, ea, eb);
            sb.append(ans+ "\n");
        }
        System.out.println(sb);
    }
}