package ThisIsCodingTest.Sort;

import java.util.Arrays;

public class Quick {

    public static int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};

    public static void quick_sort(int s, int e){
        if(s >= e){
            return;
        }

        int pivot = s;
        int left = s+1;
        int right = e;

        while(left <= right){
            while(left <= e && arr[left] <= arr[pivot]){
                left++;
            }
            while(right > s && arr[right] >= arr[pivot]){
                right--;
            }
            if(left > right){ // 엇갈렸다면
                int temp = arr[right];
                arr[right] = arr[pivot];
                arr[pivot] = temp;
            }else{ // 엇갈리지 않았다면
                int temp = arr[right];
                arr[right] = arr[left];
                arr[left] = temp;
            }

            quick_sort(s, right-1);
            quick_sort(right+1, e);
        }
    }

    public static void main(String[] args) {
//        quick_sort(0, 9);
        devQuick_sort(0, 9);

        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }

    private static void devQuick_sort(int s, int e) {
        if (s >= e) {
            return;
        }
        int left = s + 1;
        int right = e;
        int pivot = arr[s]; // 첫 번째 원소를 피벗으로 선택

        while (left <= right) {
            // 피벗보다 큰 값을 찾음
            while (left <= e && arr[left] <= pivot) {
                left++;
            }
            // 피벗보다 작은 값을 찾음
            while (right > s && arr[right] >= pivot) {
                right--;
            }
            // 엇갈렸다면
            if (left > right) {
                // 피벗과 arr[right]를 교환
                arr[s] = arr[right];
                arr[right] = pivot;
            } else {
                // 엇갈리지 않았다면 left와 right를 교환
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        // 피벗을 기준으로 분할된 두 부분에 대해 재귀 호출
        devQuick_sort(s, right - 1);
        devQuick_sort(right + 1, e);

        Arrays.sort(arr);
    }
}
