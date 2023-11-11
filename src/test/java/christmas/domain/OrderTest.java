package christmas.domain;

import static christmas.domain.Menu.CAESAR_SALAD;
import static christmas.domain.Menu.CHOCO_CAKE;
import static christmas.domain.Menu.ICE_CREAM;
import static christmas.domain.Menu.SEAFOOD_PASTA;
import static christmas.domain.Menu.T_BONE_STEAK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    private final List<Menu> dessertList = new ArrayList<>(List.of(CHOCO_CAKE,ICE_CREAM));
    private final List<Menu> mainMenuList = new ArrayList<>(List.of(T_BONE_STEAK,SEAFOOD_PASTA));
    private final List<Menu> appetizerList = new ArrayList<>(List.of(CAESAR_SALAD));

    @Test
    @DisplayName("디저트메뉴 갯수 Count")
    void getNumberOfDesserts() {
        List<Menu> orderList = new ArrayList<>();
        orderList.addAll(dessertList);
        Order order = new Order(orderList);

        assertThat(order.getNumberOfMenuCategory(MenuCategory.DESSERT)).isEqualTo(dessertList.size());
    }

    @Test
    @DisplayName("여러 메뉴들 중 디저트메뉴 갯수만 Count")
    void getNumberOfDessertsOnly() {
        List<Menu> orderList = new ArrayList<>();
        orderList.addAll(dessertList);
        orderList.addAll(mainMenuList);
        orderList.addAll(appetizerList);
        Order order = new Order(orderList);

        assertThat(order.getNumberOfMenuCategory(MenuCategory.DESSERT)).isEqualTo(dessertList.size());
    }
}