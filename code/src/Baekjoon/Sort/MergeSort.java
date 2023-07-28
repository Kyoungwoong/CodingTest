package Baekjoon.Sort;

public class MergeSort {

    private static int[] arr = {5, 2, 23, 1, 30, 21, 50, 6, 18};

    public static void merge(int s, int e) {
        if (s < e) {
            int mid = (s + e) / 2;
            merge(s, mid);
            merge(mid + 1, e);
            mergeSort(s, e, mid);
        }
    }

    public static void mergeSort(int s, int e, int mid) {
        int left = s, right = mid + 1, k = s;
        int[] sorted = new int[100];
        while (left <= mid && right <= e) {
            if (arr[left] < arr[right]) {
                sorted[k++] = arr[left++];
            } else {
                sorted[k++] = arr[right++];
            }
        }

        if (left <= mid) {
            while (left <= mid) {
                sorted[k++] = arr[left++];
            }
        } else {
            while (right <= e) {
                sorted[k++] = arr[right++];
            }
        }

        for (int i = s; i <= e; i++) {
            arr[i] = sorted[i];
        }
    }

    public static void main(String[] args) {
        int n = arr.length;
        merge(0, n-1);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
