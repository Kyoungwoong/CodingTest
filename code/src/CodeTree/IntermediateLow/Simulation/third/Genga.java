package CodeTree.IntermediateLow.Simulation.third;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Genga {
    private static int MAXSIZE = 100;
    private static int n, s1, e1, s2, e2;
    private static int[] arr = new int[MAXSIZE];

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        s1 = Integer.parseInt(st.nextToken());
        e1 = Integer.parseInt(st.nextToken());
        int[] temp = new int[n];
        int tempRow = 0;
        for(int i = 0; i < n; i++) {
            if(i >= s1-1 && i <= e1-1){
                continue;
            }else{
                temp[tempRow++] = arr[i];
            }
        }
        for(int i = 0; i < tempRow; i++) {
            arr[i] = temp[i];
        }

        st = new StringTokenizer(br.readLine());
        s2 = Integer.parseInt(st.nextToken());
        e2 = Integer.parseInt(st.nextToken());
        int tempRow2 = 0;
        for(int i = 0; i < tempRow; i++) {
            if(i >= s2-1 && i <= e2-1){
                continue;
            }else{
                temp[tempRow2++] = arr[i];
            }
        }
        for(int i = 0; i < tempRow2; i++) {
            arr[i] = temp[i];
        }

        System.out.println(tempRow2);
        for(int i = 0; i < tempRow2; i++) {
            System.out.println(arr[i]);
        }


    }
}