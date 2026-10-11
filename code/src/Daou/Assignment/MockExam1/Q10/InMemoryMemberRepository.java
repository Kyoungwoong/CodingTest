package Daou.Assignment.MockExam1.Q10;

import java.util.*;

public class InMemoryMemberRepository
        implements MemberRepository {

    private final Map<String, Member> members = new HashMap<>();

    @Override
    public void save(Member member) {
        if (member == null) {
            throw new IllegalArgumentException(
                    "member must not be null"
            );
        }
        if (members.containsKey(member.getId())) {
            throw new IllegalStateException(
                    "member cannot be saved duplicated"
            );
        }

        members.put(member.getId(), member);
    }

    @Override
    public Optional<Member> findById(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(
                    "id must not be null or blank"
            );
        }

        return Optional.ofNullable(members.get(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(members.values());
    }
}
