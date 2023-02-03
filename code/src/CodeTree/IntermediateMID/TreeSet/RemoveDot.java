package CodeTree.IntermediateMID.TreeSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;
// import java.util.Comparable;

class Pair implements Comparable<Pair>{
    int x, y;
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Pair pair){
        if(this.x == pair.x){
            return this.y - pair.y;
        }else{
            return this.x - pair.x;
        }
    }
}
public class RemoveDot {
    private static int n, m;
    private static TreeSet<Pair> treeSet = new TreeSet<>();

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
            treeSet.add(new Pair(x, y));
        }

        Pair empty = new Pair(-1, -1);
        for(int i = 0; i < m; i++){
            int q = Integer.parseInt(br.readLine());
            Pair find = treeSet.ceiling(new Pair(q, 1));
            if(find == null){
                sb.append(empty.x+ " " + empty.y+"\n");
            }else{
                sb.append(find.x + " "+ find.y +"\n");
                treeSet.remove(find);
            }
        }

        System.out.println(sb);
    }
}