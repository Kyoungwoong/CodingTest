package CodeTree.IntermediateMID.HashSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashSet;

public class Exist {
    private static int n, m;
    private static HashSet<Integer> a1 = new HashSet<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            a1.add(Integer.parseInt(st.nextToken()));
        }

        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            int key = Integer.parseInt(st.nextToken());
            if(a1.contains(key)){
                sb.append(1 + " ");
            }else{
                sb.append(0 + " ");
            }
        }
        System.out.println(sb);
    }
}