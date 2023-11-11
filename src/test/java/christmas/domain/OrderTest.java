package christmas.domain;

import static christmas.domain.Menu.CAESAR_SALAD;
import static christmas.domain.Menu.CHOCO_CAKE;
import static christmas.domain.Menu.ICE_CREAM;
import static christmas.domain.Menu.SEAFOOD_PASTA;
import static christmas.domain.Menu.T_BONE_STEAK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.dto.Money;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    private final List<Menu> DESSERT_LIST = new ArrayList<>(List.of(CHOCO_CAKE,ICE_CREAM));
    private final Money DESSERT_PRICE = new Money(20_000L);
    private final List<Menu> MAIN_MENU_LIST = new ArrayList<>(List.of(T_BONE_STEAK,SEAFOOD_PASTA));
    private final Money MAIN_MENU_PRICE = new Money(90_000L);
    private final List<Menu> APPETIZER_LIST = new ArrayList<>(List.of(CAESAR_SALAD));
    private final Money APPETIZER_PRICE = new Money(8_000L);

    @Test
    @DisplayName("디저트메뉴 갯수 Count")
    void getNumberOfDesserts() {
        List<Menu> orderList = new ArrayList<>();
        orderList.addAll(DESSERT_LIST);
        Order order = new Order(orderList);

        assertThat(order.getNumberOfMenuCategory(MenuCategory.DESSERT)).isEqualTo(DESSERT_LIST.size());
    }

    @Test
    @DisplayName("여러 메뉴들 중 디저트메뉴 갯수만 Count")
    void getNumberOfDessertsOnly() {
        List<Menu> orderList = new ArrayList<>();
        orderList.addAll(DESSERT_LIST);
        orderList.addAll(MAIN_MENU_LIST);
        orderList.addAll(APPETIZER_LIST);
        Order order = new Order(orderList);

        assertThat(order.getNumberOfMenuCategory(MenuCategory.DESSERT)).isEqualTo(DESSERT_LIST.size());
    }

    @Test
    @DisplayName("주문한 음식 가격의 총합을 정확히 반환한다.")
    void getTotalPrice() {
        List<Menu> orderList = new ArrayList<>();
        orderList.addAll(DESSERT_LIST);
        Order order = new Order(orderList);

        assertThat(order.getTotalPrice()).isEqualTo(DESSERT_PRICE);
    }
}