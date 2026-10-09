package Daou.Assignment.Day1;

public class User {

    String name;

    User(String name) {
        name = name;
    }

    public static void main(String[] args) {
        User user = new User("Kim");

        System.out.println(user.name);
    }
}
