package CodeTree.IntermediateMID.TreeMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Ratio {
    private static int n;
    private static String color;
    private static TreeMap<String, Integer> crayon = new TreeMap<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            color = br.readLine();
            if(crayon.containsKey(color)){
                crayon.put(color, crayon.get(color)+1);
            }else{
                crayon.put(color, 1);
            }
        }

        Iterator<Entry<String, Integer>> it = crayon.entrySet().iterator();
        while(it.hasNext()){
            Entry<String, Integer> entry = it.next();
            System.out.printf("%s %.4f", entry.getKey(), ((float)entry.getValue()/n)*100);
            System.out.println();
        }
    }
}