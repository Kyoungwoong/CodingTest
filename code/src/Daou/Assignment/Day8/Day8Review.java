import java.util.*;

/** Day 8: Collections, Generics, Map views, shallow copy, Optional. */
public class Day8Review {
    static final class Member {
        private final long id;
        private final String name;
        Member(long id, String name) { this.id = id; this.name = name; }
        long getId() { return id; }
        String getName() { return name; }
        @Override public boolean equals(Object obj) {
            if (this == obj) return true;
            return obj instanceof Member other && id == other.id;
        }
        @Override public int hashCode() { return Long.hashCode(id); }
        @Override public String toString() { return "Member{id=" + id + ", name='" + name + "'}"; }
    }

    static final class MemberRepository {
        private final Map<Long, Member> members = new HashMap<>();
        void add(Member member) {
            Objects.requireNonNull(member, "member");
            if (members.containsKey(member.getId())) throw new IllegalArgumentException("Duplicate member ID");
            members.put(member.getId(), member);
        }
        Optional<Member> findById(long id) { return Optional.ofNullable(members.get(id)); }
        void remove(long id) { members.remove(id); }
        List<Member> findAll() { return new ArrayList<>(members.values()); }
        int count() { return members.size(); }
    }

    static class Animal {}
    static class Dog extends Animal {}
    static class Cat extends Animal {}
    static void readAnimals(List<? extends Animal> list) {
        for (Animal animal : list) System.out.println(animal.getClass().getSimpleName());
        // list.add(new Dog()); // Compile error: actual element type could be Cat.
    }
    static void addDog(List<? super Dog> list) {
        list.add(new Dog());
        Object item = list.get(0); // Reading as Dog is not guaranteed.
        System.out.println(item.getClass().getSimpleName());
    }
    static class Box<T> {
        private T value;
        void set(T value) { this.value = value; }
        T get() { return value; }
    }

    public static void main(String[] args) {
        System.out.println("=== List / Set / Map ===");
        List<String> list = new ArrayList<>(List.of("A", "B", "A"));
        System.out.println(list); // [A, B, A]
        System.out.println(new HashSet<>(list).size()); // 2
        Map<String, Integer> counts = new HashMap<>();
        for (String name : list) counts.merge(name, 1, Integer::sum);
        System.out.println(counts.get("A")); // 2
        Map<String, List<Integer>> grouped = new HashMap<>();
        grouped.computeIfAbsent("A", k -> new ArrayList<>()).add(10);
        grouped.computeIfAbsent("A", k -> new ArrayList<>()).add(20);
        System.out.println(grouped.get("A")); // [10, 20]

        System.out.println("=== MemberRepository / Optional ===");
        MemberRepository repo = new MemberRepository();
        repo.add(new Member(1, "Kim"));
        repo.add(new Member(2, "Lee"));
        System.out.println(repo.findById(1).orElseThrow());
        System.out.println(repo.findById(99).isPresent()); // false
        System.out.println(repo.findAll().size()); // 2
        try { repo.add(new Member(1, "Park")); }
        catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
        repo.remove(2);
        System.out.println(repo.count()); // 1
        System.out.println(new Member(1, "Kim").equals(new Member(1, "Lee"))); // true

        System.out.println("=== View vs shallow copy ===");
        Map<Integer, StringBuilder> map = new HashMap<>();
        map.put(1, new StringBuilder("A"));
        Collection<StringBuilder> view = map.values();
        List<StringBuilder> copy = new ArrayList<>(map.values());
        map.put(2, new StringBuilder("B"));
        copy.get(0).append("C");
        System.out.println(view.size()); // 2
        System.out.println(copy.size()); // 1
        System.out.println(map.get(1)); // AC
        System.out.println(copy.get(0) == map.get(1)); // true

        System.out.println("=== Generics ===");
        Box<String> box = new Box<>();
        box.set("Java");
        System.out.println(box.get());
        List<Dog> dogs = new ArrayList<>(List.of(new Dog()));
        readAnimals(dogs);
        List<Animal> animals = new ArrayList<>();
        addDog(animals);
        System.out.println(new ArrayList<String>().getClass() == new ArrayList<Integer>().getClass()); // true
    }
}
