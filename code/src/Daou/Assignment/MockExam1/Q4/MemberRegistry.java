package Daou.Assignment.MockExam1.Q4;

import java.util.HashSet;
import java.util.Set;

public class MemberRegistry {

    private final Set<Member> members = new HashSet<>();

    public boolean register(Member member) {
        // TODO 4: null 검증
        if (member == null) {
            throw new IllegalArgumentException(
                    "member can not be null"
            );
        }
        // TODO 5: 회원 등록 및 결과 반환
        if (contains(member)) {
            return false;
        }
        members.add(member);

        return true;
    }

    public boolean contains(Member member) {
        // TODO 6: null 검증
        if (member == null) {
            throw new IllegalArgumentException(
                    "member can not be null"
            );
        }
        // TODO 7: 회원 존재 여부 반환
        return members.contains(member);
    }

    public boolean remove(Member member) {
        // TODO 8: null 검증
        if (member == null) {
            throw new IllegalArgumentException(
                    "member can not be null"
            );
        }
        // TODO 9: 회원 삭제 및 결과 반환
        if (contains(member)) {
            members.remove(member);
            return true;
        }
        return false;
    }

    public int size() {
        // TODO 10: 등록된 회원 수 반환
        return members.size();
    }
}
