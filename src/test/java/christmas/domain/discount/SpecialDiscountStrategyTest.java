package christmas.domain.discount;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.Order;
import christmas.domain.dto.Day;
import christmas.domain.dto.DiscountInfo;
import christmas.domain.dto.Money;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpecialDiscountStrategyTest {
    private final SpecialDiscountStrategy specialDiscountStrategy = new SpecialDiscountStrategy();
    private final Day ONE_SPECIAL_DAY = new Day(3);
    private final Day ONE_NOT_SPECIAL_DAY = new Day(4);
    private final String DESSERT_LIST = "초코케이크-1,아이스크림-1";
    private final String MAIN_MENU_LIST = "티본스테이크-1,해산물파스타-1";
    private final String APPETIZER_LIST = "시저샐러드-1";


    @Test
    @DisplayName("별이 있는 날짜를 예약한 경우 1000원 할인")
    void calculateDiscountAmount() {
        List<String> menus = new ArrayList<>();
        menus.add(DESSERT_LIST);
        menus.add(MAIN_MENU_LIST);
        menus.add(APPETIZER_LIST);
        Order order = new Order(String.join(",", menus));
        DiscountInfo discountInfo = specialDiscountStrategy.calculateDiscountAmount(order, ONE_SPECIAL_DAY);

        assertThat(discountInfo.amount()).isEqualTo(new Money(1000L));
        assertThat(discountInfo.eventName()).isEqualTo(Event.SPECIAL_DISCOUNT);
    }

    @Test
    @DisplayName("별이 있는 날짜를 예약한 경우 0원 할인")
    void calculateDiscountAmount_NotSpecialDay() {
        List<String> menus = new ArrayList<>();
        menus.add(DESSERT_LIST);
        menus.add(MAIN_MENU_LIST);
        menus.add(APPETIZER_LIST);
        Order order = new Order(String.join(",", menus));
        DiscountInfo discountInfo = specialDiscountStrategy.calculateDiscountAmount(order, ONE_NOT_SPECIAL_DAY);

        assertThat(discountInfo.amount()).isEqualTo(new Money(0));
        assertThat(discountInfo.eventName()).isEqualTo(Event.SPECIAL_DISCOUNT);
    }
}