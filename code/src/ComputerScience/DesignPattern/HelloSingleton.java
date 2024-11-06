package ComputerScience.DesignPattern;

public class HelloSingleton {
    static class Singleton {
        private static class singleInstanceHolder {
            private static final Singleton INSTANCE = new Singleton();
        }

        public static Singleton getInstance() {
            return singleInstanceHolder.INSTANCE;
        }
    }
    public static void main(String[] args) {
        Singleton a = Singleton.getInstance();
        Singleton b = Singleton.getInstance();
        System.out.println("a.hashCode() = " + a.hashCode());
        System.out.println("b.hashCode( = " + b.hashCode());
        System.out.println("a.equals(b) = " + a.equals(b));
    }
}
