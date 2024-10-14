package ThisIsCodingTest.Binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Find {
    public static int N, M, Target;
    public static Integer[] display, request;

    public static boolean search(){
        int s = 0, e = N-1;

        while(s <= e){
            int m = (s+e)/2;
            if(display[m] < Target){
                s = m+1;
                continue;
            }else if(display[m] > Target){
                e = m-1;
            }else{
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
//        prev();
        oct14();
    }

    private static void oct14() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        display = new Integer[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            display[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        request = new Integer[M];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            request[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(display);

        for (int target : request) {
            System.out.print(find(target) + " ");
        }
    }

    private static String find(int target) {
        int left = 0;
        int right = display.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (display[mid] < target) {
                left = mid + 1;
            } else if (display[mid] > target) {
                right = mid - 1;
            } else {
                return "yes";
            }
        }
        return "no";
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        display = new Integer[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            display[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        request = new Integer[M];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            request[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(display);

        for(int i = 0; i < M; i++){
            Target = request[i];
            if(search()){
                System.out.print("Yes ");
            }else{
                System.out.print("No ");
            }

        }
    }
}
/*
5
8 3 7 9 2
3
5 7 9
 */
