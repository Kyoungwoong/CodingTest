package CodeTree.IntermediateMID.HashMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Frequent {
    private static int n, ans = Integer.MIN_VALUE;
    private static HashMap<String, Integer> color = new HashMap<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            String key = br.readLine();
            color.put(key, color.getOrDefault(key, 0) + 1);
            ans = Math.max(ans, color.get(key));
        }
        System.out.println(ans);
    }
}