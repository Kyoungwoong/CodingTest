package CodeTree.IntermediateMID.HashMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;

public class Spec {

    private static HashMap<Character, Integer> ans = new HashMap<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int len = str.length();

        for(int i = 0; i < len; i++){
            ans.put(str.charAt(i), ans.getOrDefault(str.charAt(i), 0)+1);
        }

        for(Character key: ans.keySet()){
            if(ans.get(key) == 1){
                System.out.println(key);
                return;
            }
        }
        System.out.println("None");
    }
}