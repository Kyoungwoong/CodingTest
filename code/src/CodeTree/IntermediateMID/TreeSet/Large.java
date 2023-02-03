package CodeTree.IntermediateMID.TreeSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Large {
    private static int n, m;
    private static TreeSet<Integer> ball = new TreeSet<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= m; i++){
            ball.add(i);
        }

        for(int i = 0; i < n; i++){
            ball.remove(Integer.parseInt(st.nextToken()));
            sb.append(ball.last()+"\n");
        }
        System.out.println(sb);
    }
}
