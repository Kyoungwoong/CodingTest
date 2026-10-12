package Daou.Assignment.MockExam2.Q2;

import java.util.*;

/**
 * Identifiable을 상속하는 데이터를 저장하는 공통 Repository Interface
 * Repository
 * @param <T>
 * @param <ID>
 */
public interface Repository<T extends Identifiable<ID>, ID> {

    void save(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    boolean deleteById(ID id);

    long count();
}