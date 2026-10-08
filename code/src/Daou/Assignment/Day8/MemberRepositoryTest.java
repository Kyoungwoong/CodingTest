package Daou.Assignment.Day8;

import java.util.*;

class Member {
    private final long id;
    private final String name;

    public Member(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override 
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Member member)) {
            return false;
        }

        return member.getId() == this.getId();
    }

    @Override 
    public int hashCode() {
        return Long.hashCode(getId());
    }

    @Override 
    public String toString() {
        return "Member{id=" + getId() 
        + ", name=\'" + getName() + "\'}";
    }
}

class MemberRepository {
    private final Map<Long, Member> members = new HashMap<>();

    public void add(Member member) {
        if (members.containsKey(member.getId())) {
            throw new IllegalArgumentException("이미 등록된 member입니다.");
        }
        members.put(member.getId(), member);
    }

    public Optional<Member> findById(long id) {
        if (members.containsKey(id)) {
            return Optional.of(members.get(id));
        }
        return Optional.of(null);
    }

    public void remove(long id) {
        members.remove(id);
    }

    public List<Member> findAll() {
        List<Member> memberList = new ArrayList<>();
        
        for (Long id: members.keySet()) {
            memberList.add(members.get(id));
        }

        return memberList;

        // 이걸로 한꺼번에 처리할 수 있음
        // return new ArrayList<>(members.values());
    }

    public int count() {
        return members.size();
    }

}

public class MemberRepositoryTest {
    public static void main(String[] args) {
        MemberRepository repo = new MemberRepository();

        repo.add(new Member(1, "Kim"));
        repo.add(new Member(2, "Lee"));

        System.out.println(repo.count());
        System.out.println(repo.findById(1));

        try {
            repo.add(new Member(1, "Park"));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(repo.findAll().size());

        repo.remove(2);
        System.out.println(repo.count());
    }
}
