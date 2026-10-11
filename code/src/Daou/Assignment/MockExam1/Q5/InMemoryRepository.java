package Daou.Assignment.MockExam1.Q5;

import java.util.*;

// Repository 인터페이스 구현
public class InMemoryRepository<T extends Identifiable<ID>, ID>
        implements Repository<T, ID> {

    private final Map<ID, T> storeMap = new HashMap<>();

    @Override
    public void save(T entity) {
        validateNotNull(entity, "entity");

        ID id = entity.getId();
        validateNotNull(id, "id");

        if (storeMap.containsKey(id)) {
            throw new IllegalStateException(
                    "Entity already exists. id: " + id
            );
        }

        storeMap.put(id, entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        validateNotNull(id, "id");

        // return Optional.of(storeMap.getOrDefault(id, null));
        // Optional 처리는 이렇게 함.
        return Optional.ofNullable(storeMap.get(id));
    }

    @Override
    public boolean deleteById(ID id) {
        validateNotNull(id, "id");

        return storeMap.remove(id) != null;
    }

    @Override
    public List<T> findAll() {
//        List<T> result = new ArrayList<>();
//        storeMap.forEach((k, v) -> result.add(v));
//        return result;
        // 조회 결과를 독립적인 리스트로 반환하여
        // 외부에서 저장소의 컬렉션 구조를 변경하지 못하도록 한다.
        return new ArrayList<>(storeMap.values());
    }

    @Override
    public int count() {
        return storeMap.size();
    }

    private static void validateNotNull(
            Object value,
            String parameterName
    ) {
        if (value == null) {
            throw new IllegalArgumentException(
                    parameterName + " must not be null"
            );
        }
    }
}
