package ThisIsCodingTest.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class UpDown {
//    3
//            15
//            27
//            12
//            ans: 27 15 12
    public static int N;
    public static Integer[] arr;

    public static void main(String[] args) throws IOException {
//        prev();

        oct14();
    }

    private static void oct14() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new Integer[N];

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr, Collections.reverseOrder());

        for(int i = 0; i < N; i++){
            System.out.print(arr[i] + " ");
        }
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new Integer[N];

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 이거 사용하려면 primitive type으로 해야함
        Arrays.sort(arr, Collections.reverseOrder());

        for(int i = 0; i < N; i++){
            System.out.print(arr[i] + " ");
        }
    }
}
