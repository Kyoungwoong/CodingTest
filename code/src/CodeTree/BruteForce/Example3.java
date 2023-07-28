package CodeTree.BruteForce;

import java.util.Iterator;
import java.util.LinkedList;

public class Example3 {
    public static LinkedList<Integer> ll = new LinkedList<>();
    public static Iterator<Integer> it = ll.iterator();
    public static int n = 5;
    public static int[] arr = new int[]{4, 3, 1, 2, 5};

    public static boolean isPossible(int minVal) {
        int []availableIndices = new int[5];
        int cnt = 0;

        for(int i = 0; i < n; i++)
            if(arr[i] >= minVal)
                availableIndices[cnt++] = i;

        for(int i = 1; i < cnt; i++) {
            int dist = availableIndices[i] - availableIndices[i - 1];
            if(dist > 2)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int maximin = 0;
        for(int a = 1; a <= Math.min(arr[0], arr[4]); a++)
            if(isPossible(a))
                maximin = Math.max(maximin, a);

        System.out.println(maximin);

    }

}
