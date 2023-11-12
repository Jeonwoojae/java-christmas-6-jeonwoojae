package christmas.domain.discount;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.Order;
import christmas.dto.Day;
import christmas.dto.DiscountInfo;
import christmas.dto.Money;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AlwaysDiscountStrategyTest {
    private final AlwaysDiscountStrategy alwaysDiscountStrategy = new AlwaysDiscountStrategy();
    private final Day ONE_WEEK_DAY = new Day(3);
    private final Day ONE_WEEKEND_DAY = new Day(10);
    private final String DESSERT_LIST = "초코케이크-1,아이스크림-1";
    private final String MAIN_MENU_LIST = "티본스테이크-1,해산물파스타-1";
    private final String APPETIZER_LIST = "시저샐러드-1";

    @Test
    @DisplayName("평일이면 디저트 메뉴당 2023원 할인")
    void calculateDiscountAmount_WeekDays() {
        List<String> menus = new ArrayList<>();
        menus.add(DESSERT_LIST);
        menus.add(MAIN_MENU_LIST);
        menus.add(APPETIZER_LIST);
        Order order = new Order(String.join(",", menus));
        DiscountInfo discountInfo = alwaysDiscountStrategy.calculateDiscountAmount(order, ONE_WEEK_DAY);

        assertThat(discountInfo.amount()).isEqualTo(new Money(2 * 2023L));
        assertThat(discountInfo.eventName()).isEqualTo(Event.ALWAYS_DISCOUNT);
    }


    @Test
    @DisplayName("주말이면 메인 메뉴당 2023원 할인")
    void calculateDiscountAmount_WeekendDays() {
        List<String> menus = new ArrayList<>();
        menus.add(DESSERT_LIST);
        menus.add(MAIN_MENU_LIST);
        menus.add(APPETIZER_LIST);
        Order order = new Order(String.join(",", menus));
        DiscountInfo discountInfo = alwaysDiscountStrategy.calculateDiscountAmount(order, ONE_WEEKEND_DAY);

        assertThat(discountInfo.amount()).isEqualTo(new Money(2 * 2023L));
        assertThat(discountInfo.eventName()).isEqualTo(Event.ALWAYS_DISCOUNT);
    }
}