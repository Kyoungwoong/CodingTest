다음 중 올바른 실행 결과는?

A.
WITHDRAW FAILED
FINALLY: -100
RESULT: -1
BALANCE: -100

finally는 실행되지만 기존 잔액을 복구하지는 않는다.



B.
WITHDRAW FAILED
FINALLY: 600
RESULT: -1
BALANCE: 600

예외가 발생하면 balance -= amount가 자동으로 롤백된다.



C.
WITHDRAW FAILED
FINALLY: -100
RESULT: -100
BALANCE: -100

finally에서 변경한 balance가 return 값을 덮어쓴다.



D.
WITHDRAW FAILED
RESULT: -1
BALANCE: -200

catch에서 return을 실행했으므로 finally는 실행되지 않는다.



E.
WITHDRAW FAILED
FINALLY: -100
RESULT: -1
BALANCE: 500

finally 실행 이후 Java가 메서드 진입 시점의 객체 상태를 복구한다.