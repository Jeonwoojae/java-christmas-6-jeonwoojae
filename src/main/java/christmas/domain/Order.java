package christmas.domain;

import christmas.domain.dto.Money;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 주문한 내용을 저장
public class Order {
    private Map<Menu,Integer> orderMenus = new HashMap<>();

    public Order(List<Menu> orderMenus) {
        // TODO 주문 예외사항 확인

    }

    // 주문 완료 -> 이벤트 확인

    // 총 주문 금액
    public Money getTotalPrice() {
        // TODO 주문의 총 합
        return null;
    }
}
