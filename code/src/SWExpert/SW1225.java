package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class SW1225 {
    private static int step, tc = 10, cryptoSize = 8, codeSize = 5;
    private static int[] code = {1,2,3,4,5};
    private static Queue<Integer> crypto = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int t = 0; t < tc; t++){
            step = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < cryptoSize; i++){
                crypto.add(Integer.parseInt(st.nextToken()));
            }

            int cycle = 0;
            while(true){
                int num = crypto.poll();

                if(num - code[cycle] < 0){
                    num = 0;
                }else{
                    num = num-code[cycle];
                }

                crypto.add(num);
                if(num == 0){
                    break;
                }

                cycle = (cycle + 1) % codeSize;
            }

            System.out.print("#" + step + " ");
            for(int i = 0; i < cryptoSize; i++){
                System.out.print(crypto.poll() + " ");
            }
            System.out.println();

        }
    }
}
