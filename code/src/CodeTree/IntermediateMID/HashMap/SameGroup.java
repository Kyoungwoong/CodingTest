package CodeTree.IntermediateMID.HashMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Arrays;

public class SameGroup {
    private static int n;
    private static HashMap<String, Integer> group = new HashMap<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            char[] str = br.readLine().toCharArray();
            Arrays.sort(str);
            String word = new String(str);

            group.put(word, group.getOrDefault(word, 0)+1);
        }

        int max = -1;
        for(String key: group.keySet()){
            max = Math.max(max, group.get(key));
        }
        System.out.println(max);
    }
}