package Daou.Wikidocs;

import java.util.*;

public class Q9 {

    // (한 줄을 수정하세요)
    public static int func(int record) {
        if (record == 0) {
            return 1;
        } else if (record == 1) {
            return 2;
        }
        return 0;
    }

    public static int solution(int[] recordA, int[] recordB) {
        int cnt = 0;

        for (int i = 0; i < recordA.length; i++) {
            if (recordA[i] == recordB[i]) {
                continue;
            } else if (recordA[i] == func(recordB[i])) {
                cnt = cnt + 3;
            } else {
                if (i == 0) continue;
                cnt = cnt - 1;
            }
        }

        return cnt;
    }

    public static void main(String[] args) {

        int[] recordA = {2,0,0,0,0,0,1,1,0,0};
        int[] recordB = {0,0,0,0,2,2,0,2,2,2};

        int ret = solution(recordA, recordB);

        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");
    }
}
