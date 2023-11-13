package christmas.domain.giveway;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.Menu;
import christmas.domain.Order;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiveChampagneStrategyTest {
    private final GiveChampagneStrategy giveChampagneStrategy = new GiveChampagneStrategy();
    private final String DESSERT_LIST = "초코케이크-1,아이스크림-1";
    private final String MAIN_MENU_LIST = "티본스테이크-1,해산물파스타-1,바비큐립-1,크리스마스파스타-1";
    private final String APPETIZER_LIST = "시저샐러드-1";

    @Test
    @DisplayName("12만원 이상이면 샴페인을 증정한다.")
    void getFreeMenu() {
        List<String> menus = new ArrayList<>();
        menus.add(DESSERT_LIST);
        menus.add(MAIN_MENU_LIST);
        menus.add(APPETIZER_LIST);
        Order order = new Order(String.join(",", menus));
        Menu freeMenu = giveChampagneStrategy.getFreeMenu(order).get();

        assertThat(freeMenu).isEqualTo(Menu.CHAMPAGNE);
    }

    @Test
    @DisplayName("12만원 미만이면 샴페인을 증정하지 않는다.")
    void getNoFreeMenu() {
        Order order = new Order(DESSERT_LIST);

        assertTrue(giveChampagneStrategy.getFreeMenu(order).isEmpty());
    }
}