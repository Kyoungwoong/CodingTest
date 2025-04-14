package Baekjoon.Sort;

public class QuickSort {

    private static int[] arr = {5, 2, 23, 1, 30, 21, 50, 6, 18}; // 1 2 5 6 18 21 23 30 50

    public static void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static int partition_rvalue(int s, int e) {
        int left = s, right = e - 1, pivot = arr[e];

        while (left < right) {
            while (left < right && arr[left] < pivot) {
                left++;
            }
            while (left < right && arr[right] > pivot) {
                right--;
            }
            swap(left, right);
        }
        swap(right, e);
        return right;
    }

    public static int partition_lvalue(int s, int e) {
        int left = s + 1, right = e, pivot = arr[s];

        while (left <= right) {
            while (left <= right && arr[left] <= pivot) {
                left++;
            }
            while (left <= right && arr[right] >= pivot) {
                right--;
            }

            if (left < right) {
                swap(left, right);
            }
        }

        swap(s, right); // 피벗과 right 위치 교환 (right이 새로운 피벗 위치)
        return right; // 새 피벗 위치 반환
    }

    public static void quickSort(int s, int e) {
        if (s < e) {
//            int idx = partition_rvalue(s, e);
            int idx = partition_lvalue(s, e);

            quickSort(s, idx - 1);
            quickSort(idx + 1, e);
        }
    }

    public static void main(String[] args) {
        int n = arr.length;
        quickSort(0, n - 1);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
