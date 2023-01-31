package CodeTree.IntermediateMID.HashSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashSet;

public class FindNum {
    private static int n, m;
    private static HashSet<Integer> match = new HashSet<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            match.add(Integer.parseInt(st.nextToken()));
        }

        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            int key = Integer.parseInt(st.nextToken());
            if(match.contains(key)){
                sb.append("1\n");
            }else{
                sb.append("0\n");
            }
        }
        System.out.println(sb);
    }
}
