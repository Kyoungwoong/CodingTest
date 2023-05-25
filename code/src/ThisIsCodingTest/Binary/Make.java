package ThisIsCodingTest.Binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Make {
    public static int N, M;
    public static Integer[] arr;

    public static int find(){
        int s = 0, e = N-1, result = 0;
        while(s <= e){
            int target = arr[(s+e)/2];
            int total = 0;
            for(int i = 0; i < N; i++){
                if(arr[i] >= target){
                    total += arr[i]-target;
                }
            }

            if(total >= M){
                result = arr[(s+e)/2];
                s = (s+e)/2 +1;
            }else if(total < M){
                e = (s+e)/2 -1;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new Integer[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        System.out.print(find());

    }
}
/*
4 6
19 15 10 17
 */
