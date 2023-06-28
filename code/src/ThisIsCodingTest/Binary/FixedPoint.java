package ThisIsCodingTest.Binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FixedPoint {
    private static int N;
    private static int[] arr;

    public static int binary() {
        int s = 0, e = arr.length;

        while (s <= e) {
            int mid = (s + e) / 2;
            if (arr[mid] < mid) {
                s = mid + 1;
            } else if (arr[mid] > mid) {
                e = mid - 1;
            }else{
                return mid;
            }
        }
        return -1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(binary());
    }
    /*
    // 재귀 버전
    // 이진 탐색 소스코드 구현(재귀 함수)
    public static int binarySearch(int[] arr, int start, int end) {
        if (start > end) return -1;
        int mid = (start + end) / 2;
        // 고정점을 찾은 경우 중간점 인덱스 반환
        if (arr[mid] == mid) return mid;
        // 중간점의 값보다 중간점이 작은 경우 왼쪽 확인
        else if (arr[mid] > mid) return binarySearch(arr, start, mid - 1);
        // 중간점의 값보다 중간점이 큰 경우 오른쪽 확인
        else return binarySearch(arr, mid + 1, end);
    }
     */
}

/*
5
-15 -6 1 3 7
--- 3

7
-15 -4 2 8 9 13 15
--- 2

7
-15 -4 3 8 9 13 15
--- -1
 */
