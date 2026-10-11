package Daou.Assignment.MockExam1.Q10;

import java.util.*;

public class PointService {

    private final InMemoryMemberRepository repository;

    private final List<PointHistory> histories = new ArrayList<>();

    public PointService(InMemoryMemberRepository repository) {
        this.repository = repository;
    }

    public void earn(String memberId, long amount) {
        Optional<Member> findMember = repository.findById(memberId);

        if (findMember.isEmpty()) {
            throw new NoSuchElementException("no member");
        }

        Member member = findMember.get();

        member.earn(amount);

        histories.add(new PointHistory(
                memberId,
                PointType.EARN,
                amount,
                member.getPoints()
        ));
    }

    public void use(String memberId, long amount) {
        Optional<Member> findMember = repository.findById(memberId);

        if (findMember.isEmpty()) {
            throw new NoSuchElementException("no member");
        }

        Member member = findMember.get();

        member.use(amount);

        histories.add(new PointHistory(
                memberId,
                PointType.USE,
                amount,
                member.getPoints()
        ));
    }

    public long getBalance(String memberId) {
        return repository.findById(memberId).get().getPoints();
    }

    public List<PointHistory> getHistories(String memberId) {
        return histories;
    }
}
