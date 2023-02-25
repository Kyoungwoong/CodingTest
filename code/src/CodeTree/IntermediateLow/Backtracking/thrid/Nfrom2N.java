package CodeTree.IntermediateLow.Backtracking.thrid;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Nfrom2N {
    private static int n, min = 1000;
    private static ArrayList<Integer> A = new ArrayList<>();
    private static ArrayList<Integer> AIdx = new ArrayList<>();
    private static ArrayList<Integer> B;
    private static int[] arr = new int[20];

    public static int calc(ArrayList<Integer> B){
        int sumA = 0, sumB = 0;
        for(int i = 0; i < n; i++) {
            sumA += A.get(i);
            sumB += B.get(i);
        }

        return sumA > sumB ? sumA - sumB : sumB - sumA;
    }

    public static void findGroup(int idx){

        if(idx == 2*n){
            return;
        }

        if(A.size() == n) {
            // System.out.println("A");
            // for(int i = 0; i <n; i++){
            //     System.out.print(A.get(i) + " ");
            // }
            // System.out.println();

            int sumA = 0, sumB = 0;
            for(int i = 0; i <n; i++){
                sumA += A.get(i);
            }
            for(int i = 0; i < 2*n; i++) {
                boolean check = false;
                for(int j = 0; j < n; j++) {
                    if(i == AIdx.get(j)){
                        check = true;
                        break;
                    }
                }
                if(check){
                    continue;
                }else{
                    sumB += arr[i];
                }

            }
            // System.out.println("sumA: " + sumA + " sumB: " + sumB);
            if(sumA > sumB){
                sumA -= sumB;
            }else{
                sumA = sumB-sumA;
            }

            min = Math.min(min, sumA);

            return;
        }

        if(A.size() > n){
            return;
        }

        A.add(arr[idx]);
        AIdx.add(idx);

        findGroup(idx + 1);

        A.remove(A.size()-1);
        AIdx.remove(AIdx.size()-1);

        findGroup(idx+1);
    }


    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 2*n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        findGroup(0);

        System.out.println(min);

    }
}
