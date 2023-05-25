package ThisIsCodingTest.Sort;

public class Count {
    public static void main(String[] args) {
        int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8, 3, 4, 3,1 , 4, 0, 4, 0, 6, 4, 2, 1, 0};
        int[] sort = new int[100];
        int l = arr.length;

        for(int i = 0; i < l; i++){
            sort[arr[i]]++;
        }

        for(int i = 0; i < 100; i++){
            if(sort[i] == 0) continue;
            else{
                for(int j = 0; j < sort[i]; j++){
                    System.out.print(i+" ");
                }

            }
        }

    }
}
