package SWExpert.LinkedList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class fourth {
    private static int TC = 10, N, M;
    private static ArrayList<Integer> crypto;
    private static String cmd;
    private static BufferedReader br;

    public static void editCrypto() throws IOException{
        int cnt = 0, x, y, s, idx;
        M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        while(cnt != M){
            cnt++;
            cmd = st.nextToken();
            switch(cmd){
                case "I":
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                    for(int i = 0; i < y; i++){
                        crypto.add(x + i, Integer.parseInt(st.nextToken()));
                    }
                    break;
                case "D":
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                    for(int i = 0; i < y; i++){
                        if(x-i >= 0){
                            crypto.remove(x-i);
                        }
                    }
                    break;
                case "A":
                    y = Integer.parseInt(st.nextToken());
                    for(int i = 0; i < y; i++){
                        int num = Integer.parseInt(st.nextToken());
                        crypto.add(num);
                    }
                    break;
            }
        }
    }

    public static void print(int t){
        System.out.print("#" + t + " ");
        for(int i = 0; i < TC; i++){
            System.out.print(crypto.get(i) + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) throws IOException{
        System.setIn(new java.io.FileInputStream("SW_Testcase/LinkedList/crypto4.txt"));

        br = new BufferedReader(new InputStreamReader(System.in));

        for(int t = 1; t <= TC; t++){
            crypto = new ArrayList<>();
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                crypto.add(Integer.parseInt(st.nextToken()));
            }
            editCrypto();
            print(t);
        }
    }
}
