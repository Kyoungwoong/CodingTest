package Daou.Assignment.MockExam1.Q10;

import java.util.*;

public class PointService {

    private final MemberRepository repository;
    private final List<PointHistory> histories = new ArrayList<>();

    public PointService(MemberRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    public void earn(String memberId, long amount) {
        Member member = findMemberOrThrow(memberId);

        member.earn(amount);

        histories.add(new PointHistory(
                memberId,
                PointType.EARN,
                amount,
                member.getPoints()
        ));
    }

    public void use(String memberId, long amount) {
        Member member = findMemberOrThrow(memberId);

        member.use(amount);

        histories.add(new PointHistory(
                memberId,
                PointType.USE,
                amount,
                member.getPoints()
        ));
    }

    public long getBalance(String memberId) {
        return findMemberOrThrow(memberId).getPoints();
    }

    public List<PointHistory> getHistories(String memberId) {
        findMemberOrThrow(memberId);

        return histories.stream()
                .filter(history -> history.getMemberId().equals(memberId))
                .toList();
    }

    private Member findMemberOrThrow(String memberId) {
        return repository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException(
                        "Member not found: " + memberId
                ));
    }
}
