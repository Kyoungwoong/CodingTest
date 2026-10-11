package Daou.Assignment.MockExam1.Q5;

import java.util.List;
import java.util.Optional;

// TODO 1: T가 Identifiable<ID>를 구현하도록
// 제네릭 타입 제약 조건 선언
public interface Repository<T extends Identifiable, ID> {

    void save(T entity);

    Optional<T> findById(ID id);

    boolean deleteById(ID id);

    List<T> findAll();

    int count();
}
