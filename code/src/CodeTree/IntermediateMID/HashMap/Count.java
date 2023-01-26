package CodeTree.IntermediateMID.HashMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Count {
    private static int n, m;
    private static HashMap<Integer, Integer> cnt = new HashMap<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int key = Integer.parseInt(st.nextToken());
            if(cnt.containsKey(key)){
                int value = cnt.get(key);
                cnt.put(key, value+1);
            }else{
                cnt.put(key, 1);
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            int key = Integer.parseInt(st.nextToken());
            System.out.print(cnt.getOrDefault(key, 0) + " ");
        }
    }
}