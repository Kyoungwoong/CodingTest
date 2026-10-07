package Daou.Assignment.Day6;

public class ExceptionTest {
    public static void main(String[] args) {
        try {

        } catch (PaymentService ex) {

        } catch (IllegalArgumentException ie) { 
            /**
             * Exception 상속관계를 통해 
             * 해당 catch는 절대 실행될 수 없기 때문에
             * Compile Error 발생
             **/ 
            
        }
    }

    int age;

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("age는 0 이상이어야 합니다.");
        }

        System.out.println("B");

        this.age = age;
    }
}
