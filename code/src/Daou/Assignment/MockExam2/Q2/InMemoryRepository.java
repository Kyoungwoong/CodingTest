package Daou.Assignment.MockExam2.Q2;

import java.util.*;

/**
 * 메모리에 저장하는 Repository
 * InMemoryRepository
 * @param <T>
 * @param <ID>
 */
public class InMemoryRepository<T extends Identifiable<ID>, ID>
        implements Repository<T, ID> {

    private final Map<ID, T> storage = new HashMap<>();

    /**
     * entity 저장
     * 중복된 id는 @IllegalStateException을 던진다.
     */
    @Override
    public void save(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException(
                "entity must not be null"
            );
        }

        ID id = entity.getId();
        validateId(id);

        if (storage.containsKey(id)) {
            throw new IllegalStateException("entity already registered");
        }

        storage.put(id, entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        validateId(id);
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public boolean deleteById(ID id) {
        validateId(id);
        return storage.remove(id) != null;
    }

    @Override
    public long count() {
        return storage.size();
    }

    private void validateId(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("entity's id must not be null");
        }
    }
}