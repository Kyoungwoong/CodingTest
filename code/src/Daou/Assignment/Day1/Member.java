package Daou.Assignment.Day1;

public class Member {
    // 2. 모든 필드는 외부에서 직접 접근할 수 없어야 한다.
    // 7. id는 생성 이후 외부에서 변경할 수 없다.
    private final long id;
    private String name;
    private String email;
    private boolean active;

    public Member(long id, String name) {
        // 4. 첫 번째 생성자는 두 번째 생성자를 this()로 호출해야 한다.
        // 5. email을 전달하지 않은 경우 null로 초기화한다.
        this(id, name, null);
    }

    public Member(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        // 6. Member가 생성되면 active는 true여야 한다.
        this.active = true;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // 11. deactivate()를 호출하면 active를 false로 변경한다.
    public void deactivate() {
        this.active = false;
    }

    // 12. changeName(String name)을 구현한다.
    public void changeName(String name) {
        if (name == null || "".equals(name)) {
            return;
        }
        this.name = name;
    }

    public boolean hasSameId(Member other) {
        if (other == null || this.id != other.id) {
            return false;
        }

        return true;
    }

    public void copyEmailFrom(Member other) {
        if (other != null) {
            this.setEmail(other.getEmail());
        }
    }

    public Member updateEmail(String email) {
        setEmail(email);
        return this;
    }

}
