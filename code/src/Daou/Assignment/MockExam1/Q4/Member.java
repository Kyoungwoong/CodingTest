package Daou.Assignment.MockExam1.Q4;

import java.util.Objects;

public final class Member {

    private final String userId;
    private final String name;

    public Member(String userId, String name) {
        // TODO 1: userId, name 유효성 검증
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    // TODO 2: equals() 재정의
    /**
     * userId를 기준으로만 동일성을 비교한다.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Member member)) {
            return false;
        }

        return !member.getUserId().isEmpty()
                && this.getUserId().equals(member.getUserId());
    }

    // TODO 3: hashCode() 재정의
    @Override
    public int hashCode() {
        return Objects.hashCode(this.getUserId());
    }
}