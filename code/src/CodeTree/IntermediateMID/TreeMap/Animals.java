package CodeTree.IntermediateMID.TreeMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Animals {
    private static int n;
    private static TreeMap<String, Integer> animals = new TreeMap<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i =0; i < n; i++){
            String key = br.readLine();
            if(animals.containsKey(key)){
                animals.put(key, animals.get(key)+1);
            }else{
                animals.put(key, 1);
            }
        }

        Iterator<Entry<String, Integer>> it = animals.entrySet().iterator();
        while(it.hasNext()){
            Entry<String, Integer> entry = it.next();
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}