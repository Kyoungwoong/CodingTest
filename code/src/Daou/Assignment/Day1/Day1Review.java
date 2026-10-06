/**
 * Day 1 Review
 *
 * 핵심 주제
 * - Class / Object
 * - Constructor
 * - this / this()
 * - Access Modifier
 * - Overloading
 * - Reference / Pass By Value
 * - Array
 * - Varargs
 * - == / equals 기초
 */
public class Day1Review {

    public static void main(String[] args) {

        // 1. 객체 생성과 생성자
        Member member1 = new Member(1L, "Kim");
        Member member2 = new Member(2L, "Lee", "lee@test.com");

        System.out.println(member1.getName());
        System.out.println(member2.getEmail());

        // 2. 같은 객체를 참조
        Member member3 = member1;
        member3.changeName("Park");

        // 같은 객체이므로 Park
        System.out.println(member1.getName());

        // 3. 참조값 재할당
        member3 = new Member(3L, "Choi");

        // member1에는 영향 없음
        System.out.println(member1.getName()); // Park
        System.out.println(member3.getName()); // Choi

        // 4. 객체 전달 - 객체 상태 변경
        changeName(member1);
        System.out.println(member1.getName()); // Kang

        // 5. 객체 전달 - parameter 재할당
        replaceMember(member1);

        // caller의 member1은 변경되지 않음
        System.out.println(member1.getName()); // Kang

        // 6. 객체 동일성
        Member sameReference = member1;

        System.out.println(member1 == sameReference); // true
        System.out.println(member1 == member2);       // false

        // 7. 객체 배열
        Member[] members = new Member[2];

        // 현재 [null, null]
        members[0] = member1;
        members[1] = member2;

        System.out.println(members[0].getName());

        // 8. 배열 역시 객체
        int[] numbers = {1, 2, 3};
        changeArray(numbers);

        System.out.println(numbers[0]); // 100

        // 9. Varargs
        printNumbers(10);
        printNumbers(10, 20, 30);

        // 10. Overloading
        print(10);      // int
        print(10L);     // long

        Object value = "hello";

        // 실제 객체는 String이지만
        // Overloading은 compile time의 선언 타입을 기준으로 결정
        print(value);   // Object

        // 11. Method Chaining
        member1.updateEmail("first@test.com")
               .updateEmail("second@test.com");

        System.out.println(member1.getEmail());
    }

    static void changeName(Member member) {
        // 전달받은 참조가 가리키는 객체의 상태 변경
        member.changeName("Kang");
    }

    static void replaceMember(Member member) {
        // Java는 Pass By Value.
        // 지역 parameter의 참조값만 변경된다.
        member = new Member(100L, "New Member");
    }

    static void changeArray(int[] values) {
        values[0] = 100;
    }

    static void printNumbers(int... values) {
        for (int value : values) {
            System.out.println(value);
        }
    }

    static void print(int value) {
        System.out.println("int");
    }

    static void print(long value) {
        System.out.println("long");
    }

    static void print(Object value) {
        System.out.println("Object");
    }

    static void print(String value) {
        System.out.println("String");
    }
}


class Member {

    private final long id;
    private String name;
    private String email;
    private boolean active;

    public Member(long id, String name) {
        this(id, name, null);
    }

    public Member(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.active = true;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    public void changeName(String name) {
        if (name == null || name.isEmpty()) {
            return;
        }

        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void deactivate() {
        this.active = false;
    }

    public boolean hasSameId(Member other) {
        return other != null && this.id == other.id;
    }

    public void copyEmailFrom(Member other) {
        if (other != null) {
            // private은 객체 기준이 아니라 class 기준으로 접근을 제한한다.
            this.email = other.email;
        }
    }

    public Member updateEmail(String email) {
        this.email = email;
        return this;
    }
}