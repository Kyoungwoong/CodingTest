# Java Day 12 복습 노트 — I/O, Files API, 직렬화

## 학습 결과
- 기초 퀴즈 Q1~Q10: **7/10**
- 심화 퀴즈 Q11~Q20: **4/10**
- `Map.merge()` 확인 문제: **1/1**
- 전체: **12/21 (57.1%)**
- 상태: **이론 진도 완료 / 종합 구현은 해설 학습 완료, 독립 구현 미검증**

## 1. I/O 스트림 개념
- **Input**: 외부 → Java 프로그램, **Output**: Java 프로그램 → 외부.
- `java.util.stream.Stream`: 컬렉션 데이터 가공 (`map`, `filter`, `collect`).
- `java.io` 스트림: 파일·네트워크 등에서 바이트/문자 입출력 (`read`, `write`, `close`).
- **바이트 스트림**: `InputStream`, `OutputStream` — 이미지·압축 파일 등.
- **문자 스트림**: `Reader`, `Writer` — 인코딩을 고려한 텍스트 처리.
- `InputStreamReader`는 바이트를 지정 문자셋으로 **디코딩**하는 다리 역할.
- `Reader.read()`의 반환 타입도 `int`이며 UTF-16 코드 단위를 읽는다. `-1`은 EOF.

## 2. read() 반환값 — 반드시 구분

| 호출 | 반환 의미 | EOF |
|---|---|---|
| `InputStream.read()` | 읽은 1바이트의 값 (0~255) | `-1` |
| `InputStream.read(byte[])` | 실제 읽은 바이트 개수 | `-1` |
| `Reader.read()` | 읽은 UTF-16 코드 단위 값 | `-1` |
| `BufferedReader.readLine()` | 줄바꿈 제외한 한 줄의 문자열 | `null` |

```java
byte[] data = {65, 66, 67};
ByteArrayInputStream in = new ByteArrayInputStream(data);
byte[] buffer = new byte[2];
System.out.println(in.read(buffer)); // 2 (버퍼에 65, 66 저장)
System.out.println(in.read());       // 67
System.out.println(in.read());       // -1
```

- `read(byte[])`의 반환값은 **배열 자체가 아니다**.
- 버퍼 복사 시 `out.write(buffer, 0, count)` 사용: 실제 읽은 크기만 기록.
- `StringReader("Java").read()`의 첫 결과는 `74` (`'J'`의 코드 값). `(char)` 캐스팅하면 `J`.

## 3. BufferedReader / BufferedWriter

```java
try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
}
```

- `readLine()`은 줄바꿈 문자를 반환 문자열에 포함하지 않는다.
- **빈 줄은 `""`**, 파일 끝은 **`null`**.
- `BufferedWriter.write()`는 자동 줄바꿈하지 않음. `newLine()`을 명시적으로 호출.
- 버퍼링은 작은 I/O 작업의 오버헤드를 줄이는 데 도움이 된다.

## 4. try-with-resources

```java
try (InputStream in = new FileInputStream("source.bin");
     OutputStream out = new FileOutputStream("target.bin")) {
    byte[] buffer = new byte[8192];
    int count;
    while ((count = in.read(buffer)) != -1) {
        out.write(buffer, 0, count);
    }
}
```

- `AutoCloseable`을 구현한 자원을 자동으로 닫는다.
- 여러 자원은 **선언/생성 역순으로 닫힌다**. `A` → `B` 열면 `B` → `A` 닫힘.
- `IOException`은 검사 예외이므로 처리하거나 `throws`로 선언해야 한다.
- `NumberFormatException`은 `IOException`의 하위 타입이 아니므로 별도 처리 필요.

## 5. File, Path, Files

```java
File f = new File("missing.txt");
System.out.println(f.exists()); // 파일이 없다면 false; new File은 생성 X

Path path = Path.of("data").resolve("test.txt"); // data/test.txt (Unix 기준)
Files.writeString(path, "Hello", StandardCharsets.UTF_8);
String text = Files.readString(path, StandardCharsets.UTF_8);
```

- `new File(...)` / `Path.of(...)`: 경로 객체만 만들며 실제 파일을 만들지 않는다.
- `Files.writeString()` 기본 동작: 파일이 없으면 생성, 있으면 **덮어쓰기**.
- `StandardOpenOption.CREATE`, `APPEND`: 없으면 생성하고 기존 파일 끝에 추가.
- `Files.readAllLines()`는 모든 줄을 `List<String>`으로 읽는다.
- `Files.lines()`는 `Stream<String>`을 반환하고 **try-with-resources**로 닫는 것이 좋다.
- 경로의 상위 디렉터리가 없으면 쓰기 작업이 실패할 수 있다.

## 6. 직렬화

```java
class User implements Serializable {
    private static final long serialVersionUID = 1L;
    String name = "Kim";
    transient int age = 25;
}
```

- `Serializable`: 메서드 구현이 필요 없는 마커 인터페이스.
- `ObjectOutputStream.writeObject()` / `ObjectInputStream.readObject()`.
- `transient`는 기본 직렬화에서 제외. 역직렬화된 `int age`는 **0**, 참조형 필드는 **null**.
- 기본 역직렬화에서는 직렬화 가능한 클래스의 일반 필드 초기화 코드가 다시 실행되지 않는다.
- **신뢰할 수 없는 바이트를 Java 기본 역직렬화하지 않는다**. 실무에서는 JSON 등 명시적 형식 고려.

## 7. Day 12 오답 총정리

| 문항 | 내가 답한 내용 | 정답 | 원인 |
|---|---|---|---|
| Q2 | `[10,20,30]` | `3` | `read(byte[])`는 읽은 개수를 반환 |
| Q3 | `A,B,C` | `A` | `read()` 한 번은 바이트 하나 |
| Q8 | `Java` | `74` | `Reader.read()`도 int 반환 |
| Q11 | `65,66,67` | `2`, `67` | 버퍼 읽기 반환값 혼동 |
| Q15 | `FileNotExistsException` | `false` | `new File()`은 파일을 열지 않음 |
| Q16 | `?` | `data/test.txt` | `Path.resolve()` 경로 결합 |
| Q17 | `Kim,null` | `Kim,0` | transient int의 기본값은 0 |
| Q19 | `0,false` | `0,true` | 빈 줄 `""`와 EOF `null` 구별 |
| Q20 | `A,E,G` | `A,B,D,G` | Files.lines/Serializable/transient/File 복습 |

> Q6 `ACB`, Q7 `XBA`, Q18 `021` 등 자원 종료 순서 문제는 맞혔다.

## 8. 종합 과제 — EmployeeFileProcessor

**문제 요약:** `employees.csv`를 만들고, `BufferedReader`로 읽어 `Employee` 목록을 생성한 다음, 전체 평균 급여 및 부서별 인원을 계산해 `report.txt`로 저장한다.

**입력:**
```text
1001,Kim,IT,5000
1002,Lee,HR,4000
1003,Park,IT,6000
1004,Choi,Finance,5500
1005,Jung,HR,4500
```

**기대 결과:** 총 5명, 평균 5000.0, IT 2명, HR 2명, Finance 1명.

### 핵심 메서드 구현 패턴

```java
static void writeSampleFile(Path path) throws IOException {
    Files.write(path, List.of("1001,Kim,IT,5000", "1002,Lee,HR,4000",
        "1003,Park,IT,6000", "1004,Choi,Finance,5500", "1005,Jung,HR,4500"),
        StandardCharsets.UTF_8);
}

static List<Employee> readEmployees(Path path) throws IOException {
    List<Employee> result = new ArrayList<>();
    try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] p = line.split(",");
            result.add(new Employee(Integer.parseInt(p[0]), p[1], p[2],
                Integer.parseInt(p[3])));
        }
    }
    return result;
}

static double calculateAverageSalary(List<Employee> employees) {
    return employees.stream().mapToInt(Employee::getSalary).average().orElse(0.0);
}

static Map<String, Integer> countByDepartment(List<Employee> employees) {
    Map<String, Integer> result = new HashMap<>();
    for (Employee e : employees) {
        result.merge(e.getDepartment(), 1, Integer::sum);
    }
    return result;
}

static void writeReport(Path path, List<Employee> employees) throws IOException {
    Map<String, Integer> counts = countByDepartment(employees);
    try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
        bw.write("=== Employee Report ==="); bw.newLine();
        bw.write("Total Employees: " + employees.size()); bw.newLine();
        bw.write("Average Salary: " + calculateAverageSalary(employees)); bw.newLine();
        bw.newLine();
        bw.write("Department Count:"); bw.newLine();
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            bw.write(entry.getKey() + ": " + entry.getValue()); bw.newLine();
        }
    }
}
```

**주의:** 위 코드는 `Employee` 클래스의 생성자와 getter가 정의되어 있다는 전제의 핵심 메서드 발췌이다. 실제 CSV가 복잡해지면 빈 줄, 잘못된 열 개수, 따옴표/쉼표 이스케이프 등 추가 처리가 필요하다.

## 9. 스스로 다시 풀기 — 실기 체크리스트

- [ ] `read()` / `read(byte[])` / `readLine()`의 반환값과 EOF 설명하기
- [ ] `BufferedReader`로 파일을 끝까지 읽는 while문 작성하기
- [ ] `BufferedWriter`로 여러 줄 기록하기
- [ ] `try-with-resources`로 자원 자동 종료 구현하기
- [ ] `Files.writeString` 덮어쓰기 vs `APPEND` 구분하기
- [ ] `Path.resolve()`로 하위 파일 경로 구성하기
- [ ] `Map.merge()`로 부서별 인원 집계하기
- [ ] `transient int`와 `transient String` 역직렬화 기본값 설명하기
- [ ] `EmployeeFileProcessor`를 해설 없이 한 번 구현하기

**다음 진도:** Day 13 — Thread, Runnable, synchronized, volatile, ExecutorService.
