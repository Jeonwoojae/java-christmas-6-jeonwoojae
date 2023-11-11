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

class SpecialDiscountStrategyTest {
    private SpecialDiscountStrategy specialDiscountStrategy = new SpecialDiscountStrategy();
    private final Day ONE_SPECIAL_DAY = new Day(3);
    private final Day ONE_NOT_SPECIAL_DAY = new Day(4);
    private final List<Menu> DESSERTS = new ArrayList<>(List.of(CHOCO_CAKE,ICE_CREAM));
    private final List<Menu> MAIN_MENUS = new ArrayList<>(List.of(T_BONE_STEAK,SEAFOOD_PASTA));
    private final List<Menu> APPETIZER = new ArrayList<>(List.of(CAESAR_SALAD));


    @Test
    @DisplayName("별이 있는 날짜를 예약한 경우 1000원 할인")
    void calculateDiscountAmount() {
        List<Menu> menus = new ArrayList<>(DESSERTS);
        menus.addAll(MAIN_MENUS);
        menus.addAll(APPETIZER);
        Order order = new Order(menus);
        DiscountInfo discountInfo = specialDiscountStrategy.calculateDiscountAmount(order, ONE_SPECIAL_DAY);

        assertThat(discountInfo.amount()).isEqualTo(new Money(1000L));
        assertThat(discountInfo.eventName()).isEqualTo(Event.SPECIAL_DISCOUNT);
    }

    @Test
    @DisplayName("별이 있는 날짜를 예약한 경우 0원 할인")
    void calculateDiscountAmount_NotSpecialDay() {
        List<Menu> menus = new ArrayList<>(DESSERTS);
        menus.addAll(MAIN_MENUS);
        menus.addAll(APPETIZER);
        Order order = new Order(menus);
        DiscountInfo discountInfo = specialDiscountStrategy.calculateDiscountAmount(order, ONE_NOT_SPECIAL_DAY);

        assertThat(discountInfo.amount()).isEqualTo(new Money(0));
        assertThat(discountInfo.eventName()).isEqualTo(Event.SPECIAL_DISCOUNT);
    }
}