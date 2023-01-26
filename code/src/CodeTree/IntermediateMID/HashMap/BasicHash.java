package CodeTree.IntermediateMID.HashMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;

public class BasicHash {
    private static int n, key, value;
    private static String cmd;
    private static HashMap<Integer, Integer> hashMap = new HashMap<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            cmd = st.nextToken();
            switch(cmd){
                case "add":
                    key = Integer.parseInt(st.nextToken());
                    value = Integer.parseInt(st.nextToken());
                    hashMap.put(key, value);
                    break;
                case "find":
                    key = Integer.parseInt(st.nextToken());
                    if(hashMap.containsKey(key)){
                        System.out.println(hashMap.get(key));
                    }else{
                        System.out.println("None");
                    }
                    break;
                case "remove":
                    key = Integer.parseInt(st.nextToken());
                    hashMap.remove(key);
                    break;
            }
        }
    }
}