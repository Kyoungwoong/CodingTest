package Daou.Assignment.MockExam1.Q5;

import java.util.*;

// TODO 2: 제네릭 타입 제약 조건 및
// Repository 인터페이스 구현
public class InMemoryRepository<T extends Identifiable, ID>
        implements Repository<T, ID> {

    // TODO 3: HashMap을 이용한 저장소 선언
    private final Map<ID, T> storeMap = new HashMap<>();

    @Override
    public void save(T entity) {
        // TODO 4: null 객체 검증
        checkedNull(entity);

        // TODO 5: 객체 ID 검증
        checkedNull(entity.getId());

        // TODO 6: 중복 ID 검증
        if (storeMap.containsKey(entity.getId())) {
            throw new IllegalStateException(
                    "entity can not be duplicated id"
            );
        }

        // TODO 7: 객체 저장
        storeMap.put((ID) entity.getId(), entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        // TODO 8: null ID 검증
        checkedNull(id);

        // TODO 9: 객체 조회
        return Optional.of(storeMap.getOrDefault(id, null));
    }

    @Override
    public boolean deleteById(ID id) {
        // TODO 10: null ID 검증
        checkedNull(id);

        // TODO 11: 객체 삭제 및 결과 반환
        return false;
    }

    @Override
    public List<T> findAll() {
        // TODO 12: 모든 객체를 새로운 리스트로 반환
        List<T> result = new ArrayList<>();
        storeMap.forEach((k, v) -> result.add(v));
        return result;
    }

    @Override
    public int count() {
        // TODO 13: 저장된 객체 수 반환
        return storeMap.size();
    }

    private void checkedNull(Object entity) {
        if (entity == null) {
            throw new IllegalArgumentException(
                    "entity must not be null"
            );
        }
    }
}
