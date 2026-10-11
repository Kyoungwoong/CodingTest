# 문제
다음은 계좌 간 이체를 처리하는 Java 코드야.
코드를 분석하고 실행 결과와 설계상의 문제점을 모두 올바르게 설명한 선택지 하나를 고르시오.

---

1. `FAILED: Transfer limit exceeded`와 `TRANSFER FINISHED`가 출력되고, A는 400원, B는 500원이다. 출금 후 예외가 발생했지만 롤백하지 않아 상태 불일치가 발생한다.
2. `FAILED: Transfer limit exceeded`와 `TRANSFER FINISHED`가 출력되고, A는 1,000원, B는 500원이다. RuntimeException이 발생하면 Java가 이전 필드 변경을 자동으로 롤백한다.
3. `TRANSFER FINISHED`만 출력되고, A는 400원, B는 1,100원이다. RuntimeException은 catch에서 처리할 수 없으므로 정상적인 이체가 완료된다.
4. `FAILED: Transfer limit exceeded`만 출력되고, A는 400원, B는 500원이다. catch에서 예외를 처리했기 때문에 finally 블록은 실행되지 않는다. 
5. 컴파일 오류가 발생한다. TransferException은 RuntimeException을 상속하므로 반드시 throws를 선언해야 한다.