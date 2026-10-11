package Daou.Assignment.MockExam1.Q10;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    void save(Member member);

    Optional<Member> findById(String id);

    List<Member> findAll();
}