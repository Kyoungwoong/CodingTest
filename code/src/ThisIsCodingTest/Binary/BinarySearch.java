package ThisIsCodingTest.Binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BinarySearch {
    public static int N, Target;
    public static Integer[] arr;

    public static int search(int s, int e){
        int m = (s+e)/2;
        if(s > e){
            return -1;
        }
        System.out.println("arr[m] = " + arr[m]);
        if(arr[m] < Target){
            return search(m+1, e);
        }else if(arr[m] > Target){
            return search(s, m-1);
        }else{
            return m;
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        prev();
        oct14();
    }

    private static void oct14() throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Target = Integer.parseInt(st.nextToken());

        arr = new Integer[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) { // 정렬된 값이 들어온다고 가정
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = N;
        int ans = 0;
        boolean ansFlag = false;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < Target) {
                left = mid + 1;
            } else if (arr[mid] > Target) {
                right = mid - 1;
            } else {
                ansFlag = true;
                System.out.println("mid = " + mid);
                break;
            }
            System.out.println("mid: " + mid + " arr[mid]: " + arr[mid] + " left: " + left + " right: " + right);
            ans++;
            Thread.sleep(1000);
        }

        if (ansFlag) {
            System.out.println(ans);
        }
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Target = Integer.parseInt(st.nextToken());

        arr = new Integer[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        System.out.println(search(0, N-1));
    }
}
/*
10 7
1 3 5 7 9 11 13 15 17 19
>> 4
1 3 5 6 9 11 13 15 17 19
 */
