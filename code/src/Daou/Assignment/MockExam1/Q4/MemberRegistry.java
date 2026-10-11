package Daou.Assignment.MockExam1.Q4;

import java.util.HashSet;
import java.util.Set;

public class MemberRegistry {

    private final Set<Member> members = new HashSet<>();

    public boolean register(Member member) {
        // TODO 4: null 검증
        validateMember(member);
        // boolean을 반환함
        return members.add(member);
    }

    public boolean contains(Member member) {
        // TODO 6: null 검증
        validateMember(member);
        // TODO 7: 회원 존재 여부 반환
        return members.contains(member);
    }

    public boolean remove(Member member) {
        // TODO 8: null 검증
        validateMember(member);
        // TODO 9: 회원 삭제 및 결과 반환
        // boolean을 반환함
        return members.remove(member);
    }

    public int size() {
        // TODO 10: 등록된 회원 수 반환
        return members.size();
    }

    private static void validateMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException(
                    "Member must not be null"
            );
        }
    }
}
