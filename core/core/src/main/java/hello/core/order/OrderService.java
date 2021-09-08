package hello.core.order;

import org.springframework.stereotype.Component;

@Component
public interface OrderService {
    //주문 생성하기(주문 결과를 반환)
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
