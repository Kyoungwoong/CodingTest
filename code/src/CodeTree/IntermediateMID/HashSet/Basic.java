package CodeTree.IntermediateMID.HashSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashSet;

public class Basic {
    private static int n;
    private static HashSet<Integer> sequence = new HashSet<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        for(int i = 0; i< n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int key = Integer.parseInt(st.nextToken());
            switch(cmd){
                case "add":
                    sequence.add(key);
                    break;

                case "find":
                    if(sequence.contains(key)){
                        sb.append("true");
                        sb.append("\n");
                    }else{
                        sb.append("false\n");
                    }
                    break;

                case "remove":
                    sequence.remove(key);
                    break;
            }
        }
        System.out.println(sb);
    }
}