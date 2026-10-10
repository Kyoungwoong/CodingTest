package Daou.Assignment.MockExam1.Q2;

public interface DiscountPolicy {

    /**
     * 상품 가격에 적용할 할인 금액을 계산한다.
     *
     * @param price 할인 전 상품 가격
     * @return 실제 할인 금액 (최종 결제 금액이 아님)
     */
    long calculateDiscount(long price);
}