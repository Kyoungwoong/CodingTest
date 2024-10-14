package ThisIsCodingTest.Sort;

public class Insertion {
    public static void main(String[] args) {
        int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};
        prev(arr);
        System.out.println();
        oct14(arr);
    }

    private static void oct14(int[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            int cur = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[j] > cur) {
                    arr[i] = arr[j];
                    arr[j] = cur;
                }
            }
        }
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }

    private static void prev(int[] arr) {

        for(int i = 0; i < arr.length; i++){
            for(int j = i; j > 0; j--){
                if(arr[j] < arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }else{
                    break;
                }
            }
        }
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
}
