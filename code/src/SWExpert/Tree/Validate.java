package SWExpert.Tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Validate {
    private static int TC = 10, N;
    private static String[] calc;
    private static ArrayList<String> arrayList = new ArrayList<>();

    public static void inOrder(int idx){
        if(idx > N){
            return;
        }
        inOrder(2*idx);
        arrayList.add(calc[idx]);
        inOrder(2*idx+1);

    }

    public static boolean isValidate(){
        String first = arrayList.get(0);
        String last = arrayList.get(N-1);
        if(first.equals("+") || first.equals("-") || first.equals("*") || first.equals("/")){
            return false;
        }
        if(last.equals("+") || last.equals("-") || last.equals("*") || last.equals("/")){
            return false;
        }

        for(int i = 1; i < (int)arrayList.size(); i++){
            String cur = arrayList.get(i);
            String prev = arrayList.get(i-1);
            if(cur.equals("+") || cur.equals("-") || cur.equals("*") || cur.equals("/")){
                if(prev.equals("+") || prev.equals("-") || prev.equals("*") || prev.equals("/")){
                    return false;
                }
            }
            else{
                if(prev.equals("+") || prev.equals("-") || prev.equals("*") || prev.equals("/")){
                    continue;
                }else{
                    return false;
                }
            }

        }

        return true;
    }

    public static void main(String[] args) throws IOException{
//        System.setIn(new FileInputStream("SW_Testcase/Tree/validate.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int t = 1; t <= TC; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            calc = new String[N+1];
            arrayList.clear();
            for(int i = 1; i <= N; i++){
                st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken());
                String op = st.nextToken();
                calc[idx] = op;
            }

            inOrder(1);

            if(isValidate()){
                System.out.println("#" + t + " " + 1);
            }else{
                System.out.println("#" + t + " " + 0);
            }
        }
    }
}
