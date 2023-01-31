package CodeTree.IntermediateMID.TreeMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Basic {
    private static int n, key, value;
    private static String cmd;
    private static TreeMap<Integer, Integer> tree = new TreeMap<>();

    public static void add(int k, int v){
        tree.put(k, v);
    }

    public static void find(int k){
        if(tree.containsKey(k)){
            System.out.println(tree.get(k));
        }else{
            System.out.println("None");
        }
    }

    public static void remove(int k){
        tree.remove(k);
    }

    public static void print(){
        if(tree.isEmpty()){
            System.out.println("None");
            System.exit(0);
        }

        Iterator<Entry<Integer, Integer>> it = tree.entrySet().iterator();
        while(it.hasNext()){
            Entry<Integer, Integer> entry = it.next();
            System.out.print(entry.getValue()+ " ");
        }
        System.out.println();
    }

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
                    add(key, value);
                    break;

                case "find":
                    key = Integer.parseInt(st.nextToken());
                    find(key);
                    break;

                case "remove":
                    key = Integer.parseInt(st.nextToken());
                    remove(key);
                    break;

                case "print_list":
                    print();
                    break;
            }
        }
    }
}