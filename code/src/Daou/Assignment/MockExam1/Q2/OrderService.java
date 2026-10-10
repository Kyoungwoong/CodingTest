package Daou.Assignment.MockExam1.Q2;

public class OrderService {

    private final DiscountPolicy discountPolicy;

    public OrderService(DiscountPolicy discountPolicy) {
        // TODO 7: null 검증
        if (discountPolicy == null) {
            throw new IllegalArgumentException();
        }
        this.discountPolicy = discountPolicy;
    }

    public long calculateFinalPrice(long price) {
        // TODO 8: 상품 가격 검증
        if (price < 0) {
            throw new IllegalArgumentException();
        }

        // TODO 9: 할인 금액 계산
        long finalPrice = discountPolicy.calculateDiscount(price);

        // TODO 10: 할인 결과 검증
        if (finalPrice < 0 || finalPrice >= price) {
            return 0;
        }

        // TODO 11: 최종 결제 금액 반환
        return finalPrice;
    }
}
