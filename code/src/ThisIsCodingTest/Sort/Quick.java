package ThisIsCodingTest.Sort;

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
            if(left > right){
                int temp = arr[right];
                arr[right] = arr[pivot];
                arr[pivot] = temp;
            }else{
                int temp = arr[right];
                arr[right] = arr[left];
                arr[left] = temp;
            }

            quick_sort(s, right-1);
            quick_sort(right+1, e);
        }
    }

    public static void main(String[] args) {
        quick_sort(0, 9);

        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
}
