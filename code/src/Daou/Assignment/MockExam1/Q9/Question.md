# 문제
다음 Java 코드를 분석하고 실행 결과와 타입 안정성에 대한 설명이 모두 올바른 선택지 하나를 고르시오.

---

A. 컴파일 오류가 발생한다.
List<? extends Number>에서는 요소를 읽을 수 없고, List<? super Integer>에는 Integer를 추가할 수 없기 때문이다.



B. [10, 20] → 10 → 20 → TYPE ERROR
Raw Type을 통해 String이 List<Integer>에 들어갈 수 있으며, 반복문에서 해당 요소를 Integer로 변환하는 과정에서 ClassCastException이 발생한다.



C. [10, 20, 30] → 10 → 20 → 30
Raw Type을 통해 추가한 문자열 "30"은 Integer로 자동 변환되므로 정상적으로 출력된다.



D. [10, 20] → TYPE ERROR
Raw Type에 String을 추가하는 순간 ClassCastException이 발생하므로 반복문은 실행되지 않는다.



E. [10, 20] → 10 → 20 → 30
Java의 Type Erasure로 인해 제네릭 타입 정보가 제거되므로 런타임에는 ClassCastException이 발생하지 않는다.