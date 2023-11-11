package christmas.domain.discount;

import static christmas.domain.Menu.CAESAR_SALAD;
import static christmas.domain.Menu.CHOCO_CAKE;
import static christmas.domain.Menu.ICE_CREAM;
import static christmas.domain.Menu.SEAFOOD_PASTA;
import static christmas.domain.Menu.T_BONE_STEAK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.dto.Day;
import christmas.domain.dto.DiscountInfo;
import christmas.domain.dto.Money;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AlwaysDiscountStrategyTest {
    private final AlwaysDiscountStrategy alwaysDiscountStrategy = new AlwaysDiscountStrategy();
    private final Day ONE_WEEK_DAY = new Day(3);
    private final Day ONE_WEEKEND_DAY = new Day(10);
    private final List<Menu> DESSERTS = new ArrayList<>(List.of(CHOCO_CAKE,ICE_CREAM));
    private final List<Menu> MAIN_MENUS = new ArrayList<>(List.of(T_BONE_STEAK,SEAFOOD_PASTA));
    private final List<Menu> APPETIZER = new ArrayList<>(List.of(CAESAR_SALAD));

    @Test
    @DisplayName("평일이면 디저트 메뉴당 2023원 할인")
    void calculateDiscountAmount_WeekDays() {
        List<Menu> menus = new ArrayList<>(DESSERTS);
        menus.addAll(MAIN_MENUS);
        menus.addAll(APPETIZER);
        Order order = new Order(menus);
        DiscountInfo discountInfo = alwaysDiscountStrategy.calculateDiscountAmount(order, ONE_WEEK_DAY);

        assertThat(discountInfo.amount()).isEqualTo(new Money(DESSERTS.size()* 2023L));
        assertThat(discountInfo.eventName()).isEqualTo(Event.ALWAYS_DISCOUNT);
    }


    @Test
    @DisplayName("평일이면 디저트 메뉴당 2023원 할인")
    void calculateDiscountAmount_WeekendDays() {
        List<Menu> menus = new ArrayList<>(DESSERTS);
        menus.addAll(MAIN_MENUS);
        menus.addAll(APPETIZER);
        Order order = new Order(menus);
        DiscountInfo discountInfo = alwaysDiscountStrategy.calculateDiscountAmount(order, ONE_WEEKEND_DAY);

        assertThat(discountInfo.amount()).isEqualTo(new Money(MAIN_MENUS.size()*2023L));
        assertThat(discountInfo.eventName()).isEqualTo(Event.ALWAYS_DISCOUNT);
    }
}