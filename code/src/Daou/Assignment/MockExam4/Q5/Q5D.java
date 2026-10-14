package Daou.Assignment.MockExam4.Q5;

import java.util.*;

public class Q5D<T> {

    private final Map<String, T> storage = new HashMap<>();

    public void save(String id, T value) {
        storage.put(id, value);
    }

    public T find(String id) {
        return storage.get(id);
    }
}

/*
Repository<Number> repository = new Repository<>();

repository.save("A", 10);
repository.save("B", 3.5);

Number a = repository.find("A");
Number b = repository.find("B");

System.out.println(a.intValue());
System.out.println(b.intValue());
System.out.println(repository.find("C"));

*/