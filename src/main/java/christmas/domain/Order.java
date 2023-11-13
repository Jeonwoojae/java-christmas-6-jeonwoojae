package christmas.domain;

import christmas.dto.Money;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

public class Order {
    private final Map<Menu, Integer> orderMenus;

    public Order(String orderInput) {
        Map<Menu, Integer> parsedOrder = parseOrderInput(orderInput);

        checkOrder(parsedOrder);

        this.orderMenus = parsedOrder;
    }

    private Map<Menu, Integer> parseOrderInput(String orderInput) {
        Map<Menu, Integer> parsedOrder = new HashMap<>();
        String[] orders = orderInput.split(",");

        for (String order : orders) {
            Map.Entry<Menu, Integer> menuAndQuantity = parseOrder(order);
            checkDuplicateMenu(parsedOrder, menuAndQuantity);
            parsedOrder.put(menuAndQuantity.getKey(), menuAndQuantity.getValue());
        }

        return parsedOrder;
    }

    private void checkDuplicateMenu(Map<Menu, Integer> parsedOrder, Entry<Menu, Integer> menuAndQuantity) {
        if (parsedOrder.containsKey(menuAndQuantity.getKey())) {
            throw new IllegalArgumentException("[ERROR] 중복 메뉴를 입력하였습니다.");
        }
    }

    private Map.Entry<Menu, Integer> parseOrder(String order) {
        String[] menuAndQuantity = order.split("-");
        checkParsingError(menuAndQuantity);
        String menuName = menuAndQuantity[0];
        int quantity = Integer.parseInt(menuAndQuantity[1]);
        checkQuantityRange(quantity);

        Menu menu = Menu.findMenuByName(menuName)
                .orElseThrow(() -> new IllegalArgumentException("메뉴에 없는 메뉴입니다."));

        return new AbstractMap.SimpleEntry<>(menu, quantity);
    }

    private static void checkQuantityRange(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("[ERROR] 메뉴의 수량은 1 이상이어야 합니다.");
        }
    }

    private void checkParsingError(String[] menuAndQuantity) {
        if (menuAndQuantity.length != 2) {
            throw new IllegalArgumentException("[ERROR] 주문 형식이 잘못되었습니다.");
        }
    }

    private void checkOrder(Map<Menu, Integer> parsedOrder) {
        checkOrderNotEmpty(parsedOrder);
        checkDuplicatedOrder(parsedOrder);
        checkQuantityLimit(parsedOrder);
        checkOnlyDrinkOrder(parsedOrder);
    }

    private void checkOrderNotEmpty(Map<Menu, Integer> parsedOrder) {
        if (parsedOrder.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 메뉴의 개수는 1개 이상 입력해야합니다.");
        }
    }

    private void checkDuplicatedOrder(Map<Menu, Integer> parsedOrder) {
        if (parsedOrder.size() != new HashSet<>(parsedOrder.keySet()).size()) {
            throw new IllegalArgumentException("[ERROR] 중복 메뉴를 입력하였습니다.");
        }
    }

    private void checkQuantityLimit(Map<Menu, Integer> parsedOrder) {
        int totalQuantity = parsedOrder.values().stream().mapToInt(Integer::intValue).sum();
        if (totalQuantity > 20) {
            throw new IllegalArgumentException("[ERROR] 최대 20개까지 주문 가능합니다.");
        }
    }

    private void checkOnlyDrinkOrder(Map<Menu, Integer> parsedOrder) {
        boolean isOnlyDrink = parsedOrder.keySet().stream().allMatch(menu -> menu.getCategory() == MenuCategory.DRINK);
        if (isOnlyDrink) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문할 수 없습니다.");
        }
    }

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

    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder();

        for (Map.Entry<Menu, Integer> entry : orderMenus.entrySet()) {
            orderDetails.append(entry.getKey().getName())
                    .append(" ")
                    .append(entry.getValue())
                    .append("개\n");
        }

        return orderDetails.toString();
    }

}
