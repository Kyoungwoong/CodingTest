package CodeTree.IntermediateMID.HashMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Match {
    private static int n, m;
    private static HashMap<String, Integer> StoI = new HashMap<>();
    private static HashMap<String, String> ItoS = new HashMap<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= n; i++){
            String key = br.readLine();
            StoI.put(key, i);
            ItoS.put(Integer.toString(i), key);
        }

        for(int i = 0; i < m; i++){
            String key = br.readLine();
            if(StoI.containsKey(key)){
                System.out.println(StoI.get(key));
            }else{
                System.out.println(ItoS.get(key));
            }
        }
    }
}
