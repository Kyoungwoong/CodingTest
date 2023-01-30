package CodeTree.IntermediateMID.HashMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;


public class LowPoint {
    private static int n;
    private static HashMap<Integer, Integer> point = new HashMap<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if(!point.containsKey(x)){
                point.put(x, y);
            }else{
                int existY = point.get(x);
                if(existY > y){
                    point.put(x, y);
                }
                // point.put(x, Math.min(point.get(x), y));
            }
        }

        long ans = 0;
        for(Integer key : point.keySet()){
            ans += point.get(key);
        }
        System.out.println(ans);
    }
}