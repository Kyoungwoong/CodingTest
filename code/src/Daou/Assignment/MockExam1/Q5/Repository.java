package Daou.Assignment.MockExam1.Q5;

import java.util.List;
import java.util.Optional;

/**
 * 식별자를 가진 엔티티를 저장하고 조회하는 저장소.
 *
 * @param <T> 저장할 엔티티 타입
 * @param <ID> 엔티티의 식별자 타입
 */
public interface Repository<T extends Identifiable<ID>, ID> {

    /**
     * 엔티티를 저장한다.
     * 동일한 ID가 이미 존재하면 기존 데이터를 덮어쓰지 않는다.
     *
     * @throws IllegalArgumentException 엔티티 또는 ID가 null인 경우
     * @throws IllegalStateException 동일한 ID가 이미 존재하는 경우
     */
    void save(T entity);

    Optional<T> findById(ID id);

    boolean deleteById(ID id);

    List<T> findAll();

    int count();
}
