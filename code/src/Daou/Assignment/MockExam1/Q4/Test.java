package Daou.Assignment.MockExam1.Q4;

public class Test {
    public static void main(String[] args) {
        // 동일한 userId로 중복 등록
        caseA();

        // 서로 다른 객체의 동등성
        caseB();

        // 회원 조회 및 삭제
        caseC();

        // 대소문자 구분
        caseD();
    }

    private static void caseA() {
        MemberRegistry registry = new MemberRegistry();

        Member member1 = new Member("user01", "Kim");
        Member member2 = new Member("user01", "Lee");

        System.out.println(registry.register(member1));
        System.out.println(registry.register(member2));
        System.out.println(registry.size());
    }

    private static void caseB() {
        Member member1 = new Member("user01", "Kim");
        Member member2 = new Member("user01", "Lee");

        System.out.println(member1 == member2);
        System.out.println(member1.equals(member2));
        System.out.println(
                member1.hashCode() == member2.hashCode()
        );
    }

    private static void caseC() {
        MemberRegistry registry = new MemberRegistry();

        registry.register(new Member("user01", "Kim"));

        System.out.println(
                registry.contains(new Member("user01", "Park"))
        );

        System.out.println(
                registry.remove(new Member("user01", "Lee"))
        );

        System.out.println(registry.size());
    }

    private static void caseD() {
        MemberRegistry registry = new MemberRegistry();

        registry.register(new Member("user01", "Kim"));
        registry.register(new Member("USER01", "Kim"));

        System.out.println(registry.size());
    }
}
