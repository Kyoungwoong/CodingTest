package CodeTree.IntermediateMID.TreeSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class IntCMD {
    private static int t, k, n;
    private static String cmd;
    private static TreeSet<Integer> sample;

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++){
            k = Integer.parseInt(br.readLine());
            sample = new TreeSet<>();
            for(int j = 0; j < k; j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                cmd = st.nextToken();
                n = Integer.parseInt(st.nextToken());
                switch(cmd){
                    case "I":
                        sample.add(n);
                        break;

                    case "D":
                        if(sample.isEmpty()){
                            break;
                        }
                        if(n == 1){
                            sample.remove(sample.last());
                        }else{
                            sample.remove(sample.first());
                        }

                        break;
                }
            }
            if(sample.isEmpty()){
                sb.append("EMPTY\n");
            }else{
                sb.append(sample.last() + " " + sample.first() + "\n");
            }
        }

        System.out.print(sb);
    }
}
