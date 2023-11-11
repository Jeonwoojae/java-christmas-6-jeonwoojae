package christmas.domain.giveway;

import static christmas.domain.Menu.BBQ_RIBS;
import static christmas.domain.Menu.CAESAR_SALAD;
import static christmas.domain.Menu.CHOCO_CAKE;
import static christmas.domain.Menu.CHRISTMAS_PASTA;
import static christmas.domain.Menu.ICE_CREAM;
import static christmas.domain.Menu.SEAFOOD_PASTA;
import static christmas.domain.Menu.T_BONE_STEAK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.Menu;
import christmas.domain.Order;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiveChampagneStrategyTest {
    private GiveChampagneStrategy giveChampagneStrategy = new GiveChampagneStrategy();
    private final List<Menu> DESSERTS = new ArrayList<>(List.of(CHOCO_CAKE, ICE_CREAM));
    private final List<Menu> MAIN_MENUS = new ArrayList<>(List.of(T_BONE_STEAK, BBQ_RIBS, SEAFOOD_PASTA, CHRISTMAS_PASTA));
    private final List<Menu> APPETIZER = new ArrayList<>(List.of(CAESAR_SALAD));

    @Test
    @DisplayName("12만원 이상이면 샴페인을 증정한다.")
    void getFreeMenu() {
        List<Menu> menus = new ArrayList<>(DESSERTS);
        menus.addAll(MAIN_MENUS);
        menus.addAll(APPETIZER);
        Order order = new Order(menus);
        Menu freeMenu = giveChampagneStrategy.getFreeMenu(order);

        assertThat(freeMenu).isEqualTo(Menu.CHAMPAGNE);
    }

    @Test
    @DisplayName("12만원 미만이면 샴페인을 증정하지 않는다.")
    void getNoFreeMenu() {
        List<Menu> menus = new ArrayList<>(DESSERTS);
        Order order = new Order(menus);
        Menu freeMenu = giveChampagneStrategy.getFreeMenu(order);

        assertThat(freeMenu).isNull();
    }
}