package SWExpert.LinkedList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class fifth {
    private static int t, n, m, l;
    private static ArrayList<Integer> seq = new ArrayList<>();
    private static BufferedReader br;

    public static void findL(int t){
        if(l >= (int)seq.size()){
            System.out.println("#" + t + " " + (-1));
        }else{
            System.out.println("#" + t + " " + (seq.get(l)));
        }
    }

    public static void main(String[] args) throws IOException{
        System.setIn(new java.io.FileInputStream("SW_Testcase/LinkedList/crypto.txt"));

        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= t; test_case++){
            seq.clear();
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                seq.add(Integer.parseInt(st.nextToken()));
            }
            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());

                char cmd = st.nextToken().charAt(0);
                if(cmd == 'I'){
                    seq.add(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                }else if(cmd == 'D'){
                    seq.remove(Integer.parseInt(st.nextToken()));
                }else if(cmd == 'C'){
                    seq.set(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                }
            }

            findL(test_case);
        }



    }
}
