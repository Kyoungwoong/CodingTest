package ThisIsCodingTest.Sort;

public class Selection {
    public static void main(String[] args) {
        int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};

//        prev(arr);
        oct14(arr);
    }

    private static void oct14(int[] arr) {
        int len = arr.length;
        int minValue = Integer.MAX_VALUE;
        int tempIdx = -1;
        for (int i = 0; i < len; i++) {
            minValue = Integer.MAX_VALUE;
            tempIdx = -1;
            for (int j = i; j < len; j++) {
                if (minValue > arr[j]) {
                    minValue = Math.min(minValue, arr[j]);
                    tempIdx = j;
                }
            }
            if (tempIdx != -1) {
                arr[tempIdx] = arr[i];
                arr[i] = minValue;
            }
        }

        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }

    private static void prev(int[] arr) {
        for(int i = 0; i < arr.length-1; i++){
            int minValue = arr[i];
            int minIdx = i;
            for(int j = i+1; j < arr.length; j++){
                if(minValue < arr[j]){
                    minValue = arr[j];
                    minIdx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;

        }

        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
}
