package Daou.Assignment.MockExam1.Q4;

import java.util.Objects;

public final class Member {

    private final String userId;
    private final String name;

    public Member(String userId, String name) {
        // TODO 1: userId, name 유효성 검증
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException(
                    "userId must not be null or blank"
            );
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "name must not be null or blank"
            );
        }
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

        return userId.equals(member.userId);
    }

    // TODO 3: hashCode() 재정의
    @Override
    public int hashCode() {
        return this.userId.hashCode();
    }
}