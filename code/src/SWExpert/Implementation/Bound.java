package SWExpert.Implementation;

import java.util.Arrays;

/*
while (s <= e)	s와 e가 같은 위치도 탐색	일반적인 이진탐색 (값을 직접 찾을 때), lower/upper bound
while (s < e)	s와 e가 만나면 탐색 종료	범위 조건에서 경계값만 구할 때 (lower bound 스타일)
 */
public class Bound {
    static class LowerBound {
        public static int lowerBound(int[] arr, int target) { // 작거나 같은 최초의 값
            int s = 0, e = arr.length;
            int ans = Integer.MAX_VALUE;

            while (s <= e) {
                int mid = (s + e) / 2;

                if (arr[mid] < target) {
                    s = mid + 1;
                } else {
                    ans = mid;
                    e = mid - 1;
                }
            }
            return ans;
        }
    }

    static class UpperBound {
        public static int upperBound(int[] arr, int target) {
            int s = 0, e = arr.length;
            int ans = Integer.MIN_VALUE;

            while (s <= e) {
                int mid = (s + e) / 2;

                if (arr[mid] <= target) {
                    s = mid + 1;
                } else {
                    e = mid - 1;
                    ans = mid;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 4, 4, 7, 10};

        int target = 3;
        int lower = LowerBound.lowerBound(arr, target);
        int upper = UpperBound.upperBound(arr, target);

        Arrays.stream(arr).forEach(num -> System.out.print(num + " "));
        System.out.println(); // 줄바꿈
        System.out.println("Target: " + target);
        System.out.println("Lower Bound index (first >= " + target + "): " + lower);
        System.out.println("Upper Bound index (first > " + target + "): " + upper);
    }
}
