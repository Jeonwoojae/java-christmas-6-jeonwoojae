package christmas.domain;

import christmas.domain.dto.Money;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 주문한 내용을 저장
public class Order {
    private Map<Menu, Integer> orderMenus = new HashMap<>();

    // TODO 주문 생성 방식 메뉴와 갯수로 바로 받아야 함.
    public Order(List<Menu> newOrder) {
        // TODO 주문 예외사항 확인
        for (Menu menu : newOrder) {
            orderMenus.put(menu, 1);
        }
    }

    // 주문 완료 -> 이벤트 확인

    // 총 주문 금액
    public Money getTotalPrice() {
        Money total = new Money(0);
        for (Map.Entry<Menu, Integer> entry : orderMenus.entrySet()) {
            Money pricePerItem = entry.getKey().getPrice();
            Integer quantity = entry.getValue();
            total = total.add(pricePerItem.multiply(quantity));
        }
        return total;
    }

    public int getNumberOfMenuCategory(MenuCategory category) {
        int count = 0;
        for (Menu menu : orderMenus.keySet()) {
            if (menu.getCategory() == category) {
                count += orderMenus.get(menu);
            }
        }
        return count;
    }

}
