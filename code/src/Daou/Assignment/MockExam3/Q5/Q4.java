package Daou.Assignment.MockExam3.Q5;

public class Q4 {

    static int calculate() {
        int result = 10;

        try {
            result += 5;
            return result;
        } catch (Exception e) {
            return -1;
        } finally {
            result += 100;
            System.out.println("finally: " + result);
        }
    }

    public static void main(String[] args) {
        System.out.println("return: " + calculate());
    }
}

// finally: 115
// return: 115
// -----------------------------------------
/**
 * ANSWER
finally: 115
return: 15

return result가 실행되면 반환할 값 15가 먼저 평가돼. 
그 다음 finally가 실행되어 지역변수 result가 115로 바뀌지만, 이미 평가된 반환값 15는 변경되지 않아.

1. 지역변수 result = 10
2. result += 5 → 15
3. return result → 반환할 값 15 평가
4. finally 실행 → result = 115, 출력
5. 평가해 둔 15를 호출자에게 반환
 */