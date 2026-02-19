package Daou.groom.test4;

import java.util.ArrayList;
import java.util.List;

public class Q4 {
    public static List<Integer> func_a(int[][] matrix) {
        int n = 4;
        List<Integer> ret = new ArrayList<>();
        boolean[] exist = new boolean[n * n + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                exist[matrix[i][j]] = true;
            }
        }

        for (int i = 1; i <= n * n; i++) {
            if (exist[i] == false) {
                ret.add(i);
            }
        }

        return ret;
    }

    public static List<int[]> func_b(int[][] matrix) {
        int n = 4;
        List<int[]> ret = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    ret.add(new int[]{i, j});
                }
            }
        }

        return ret;
    }

    public static boolean func_c(int[][] matrix) {
        int n = 4;
        int goal_sum = 0;
        for (int i = 1; i <= n * n; i++) goal_sum += i;
        goal_sum /= n;

        for (int i = 0; i < n; i++) {
            int row_sum = 0;
            int col_sum = 0;
            for (int j = 0; j < n; j++) {
                row_sum += matrix[i][j];
                col_sum += matrix[j][i];
            }
            if (row_sum != goal_sum || col_sum != goal_sum) {
                return false;
            }
        }

        int main_diagonal_sum = 0;
        int skew_diagonal_sum = 0;
        for (int i = 0; i < n; i++) {
            main_diagonal_sum += matrix[i][i];
            skew_diagonal_sum += matrix[i][n - 1 - i];
        }
        if (main_diagonal_sum != goal_sum || skew_diagonal_sum != goal_sum) {
            return false;
        }
        return true;
    }

    public static List<Integer> solution(int[][] matrix) {
        List<Integer> answer = new ArrayList<>();

        List<int[]> coords = func_b(matrix);
        List<Integer> nums = func_a(matrix);

        matrix[coords.get(0)[0]][coords.get(0)[1]] = nums.get(0);
        matrix[coords.get(1)[0]][coords.get(1)[1]] = nums.get(1);

        if (func_c(matrix)) {
            for (int i = 0; i < 2; i++) {
                answer.add(coords.get(i)[0] + 1);
                answer.add(coords.get(i)[1] + 1);
                answer.add(nums.get(i));
            }
        } else {
            matrix[coords.get(0)[0]][coords.get(0)[1]] = nums.get(1);
            matrix[coords.get(1)[0]][coords.get(1)[1]] = nums.get(0);
            for (int i = 0; i < 2; i++) {
                answer.add(coords.get(1 - i)[0] + 1);
                answer.add(coords.get(1 - i)[1] + 1);
                answer.add(nums.get(i));
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {16, 2, 3, 13},
                {5, 11, 10, 0},
                {9, 7, 6, 12},
                {0, 14, 15, 1}
        };

        List<Integer> ret = solution(matrix);
        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");
    }
}
