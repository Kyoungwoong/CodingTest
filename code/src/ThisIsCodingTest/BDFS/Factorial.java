package ThisIsCodingTest.BDFS;

public class Factorial {

    public static int factorial_iterative(int n){
        int ans = 1;

        for(int i = 1; i <= n; i++){
            ans *= i;
        }

        return ans;
    }

    public static int factorial_recursive(int n){
        if(n == 1) return 1;

        return n * factorial_recursive(n-1);
    }

    public static void main(String[] args) {
        System.out.println("implement of iterative: " + factorial_iterative(5));
        System.out.println("implement of recursive: " + factorial_recursive(5));
    }
}
