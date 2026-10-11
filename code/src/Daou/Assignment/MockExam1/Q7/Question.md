# 1. 문제

다음 Java 코드를 분석하고, 실행 결과와 설계 관점에서 모두 올바른 설명 하나를 선택하시오.

---

A. Card-Object → Payment-String → Payment-String
오버로딩은 컴파일 시점의 참조 타입과 인자 타입을 기준으로 결정되며, 오버라이딩된 메서드는 런타임 객체를 기준으로 실행된다.



B. Card-Object → Payment-String → Card-Integer
null 인자는 런타임 객체인 CardPayment에 선언된 Integer 오버로드를 우선 선택한다.



C. Payment-Object → Payment-String → Payment-String
변수 payment의 선언 타입이 Payment이므로 모든 메서드는 Payment 클래스에서 실행된다.



D. Card-Object → Payment-String → 컴파일 오류
null은 String과 Integer 모두에 전달할 수 있으므로 세 번째 호출은 모호하다.



E. Card-Object → Card-Object → Payment-String
오버라이딩이 발생하면 자식 클래스의 process(Object)가 다른 오버로드보다 항상 우선한다.