package Daou.Assignment.MockExam1.Q5;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        // 회원 저장 및 조회
        caseA();

        // 상품 저장 및 삭제
        caseB();

        // 중복 ID 저장
        caseC();

        // 외부 리스트 수정
        caseD();

        // 타입 안정성
        caseE();
    }

    private static void caseA() {
        Repository<Member, Long> memberRepository =
                new InMemoryRepository<>();

        memberRepository.save(new Member(1L, "Kim"));
        memberRepository.save(new Member(2L, "Lee"));

        System.out.println(memberRepository.count());

        System.out.println(
                memberRepository.findById(1L)
                        .orElseThrow()
                        .getName()
        );

        System.out.println(
                memberRepository.findById(999L).isPresent()
        );
    }

    private static void caseB() {
        Repository<Product, String> productRepository =
                new InMemoryRepository<>();

        productRepository.save(
                new Product("P001", "Keyboard")
        );

        productRepository.save(
                new Product("P002", "Mouse")
        );

        System.out.println(
                productRepository.deleteById("P001")
        );

        System.out.println(
                productRepository.deleteById("P999")
        );

        System.out.println(productRepository.count());
    }

    private static void caseC() {
        Repository<Member, Long> repository =
                new InMemoryRepository<>();

        repository.save(new Member(1L, "Kim"));
        repository.save(new Member(1L, "Park"));
    }

    private static void caseD() {
        Repository<Member, Long> repository =
                new InMemoryRepository<>();

        repository.save(new Member(1L, "Kim"));

        List<Member> members = repository.findAll();

        members.clear();

        System.out.println(members.size());
        System.out.println(repository.count());
    }

    private static void caseE() {
        Repository<Member, Long> repository =
                new InMemoryRepository<>();

        // compile Error 발생확인
//        repository.save(
//                new Product("P001", "Keyboard")
//        );
    }
}
