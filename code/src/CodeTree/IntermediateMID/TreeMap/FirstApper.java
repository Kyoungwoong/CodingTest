package CodeTree.IntermediateMID.TreeMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class FirstApper {
    private static int n;
    private static TreeMap<Integer, Integer> tree = new TreeMap<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            int key = Integer.parseInt(st.nextToken());
            if(tree.containsKey(key)){
                continue;
            }else{
                tree.put(key, i);
            }
        }

        Iterator<Entry<Integer, Integer>> it = tree.entrySet().iterator();
        while(it.hasNext()){
            Entry<Integer, Integer> entry = it.next();
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}