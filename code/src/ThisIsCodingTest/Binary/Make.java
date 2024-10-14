package ThisIsCodingTest.Binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Make {
    public static int N, M;
    public static int[] arr;

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
//        prev();

        oct14();
    }

    private static void oct14() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        System.out.println(solution());
    }

    private static int solution() {
        int len = arr.length;
        int s = arr[0];
        int r = arr[len - 1];
        int ans = -1;
        while (s <= r) {
            int mid = (s + r) / 2;
            int remain = calc(mid);
            System.out.println("mid = " + mid + " remain: " + remain);
            if (remain < M) {
                r = mid - 1;
            } else if (remain >= M) {
                ans = mid;
                s = mid + 1;
            }
        }
        return ans;
    }

    private static int calc(int length) {
        int sum = 0;
        for (int size : arr) {
            if (size >= length) {
                sum += (size - length);
            }
        }

        return sum;
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
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
