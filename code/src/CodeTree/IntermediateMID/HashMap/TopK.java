package CodeTree.IntermediateMID.HashMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

class Rank implements Comparable<Rank>{
    int cnt, value;
    public Rank(int cnt, int value){
        this.cnt = cnt;
        this.value = value;
    }

    @Override
    public int compareTo(Rank rank){
        if(rank.cnt < cnt){
            return 1;
        }else if(rank.cnt > cnt){
            return -1;
        }else{
            if(rank.value < value){
                return 1;
            }else if(rank.value > value){
                return -1;
            }else{
                return 0;
            }
        }
    }
}

public class TopK {
    private static int n, k;
    private static HashMap<Integer, Integer> freq = new HashMap<>();
    private static ArrayList<Rank> ans = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(st.nextToken());

            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        for(Integer key : freq.keySet()){
            ans.add(new Rank(freq.get(key), key));
        }

        Collections.sort(ans, Collections.reverseOrder());

        for(int i = 0; i < k; i++){
            System.out.print(ans.get(i).value + " ");
        }

    }
}