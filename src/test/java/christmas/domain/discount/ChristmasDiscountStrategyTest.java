package christmas.domain.discount;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.Order;
import christmas.domain.dto.Day;
import christmas.domain.dto.DiscountInfo;
import christmas.domain.dto.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChristmasDiscountStrategyTest {
    private final String BBQ_RIBS = "바비큐립-1";
    private final ChristmasDiscountStrategy christmasDiscountStrategy = new ChristmasDiscountStrategy();

    @Test
    @DisplayName("크리스마스가 지난 후 예약 시 할인은 없다.")
    void calculateDiscountAmountAfterChristmas() {
        Day day = new Day(27);
        Order order = new Order(BBQ_RIBS);
        DiscountInfo discountInfo = christmasDiscountStrategy.calculateDiscountAmount(order, day);

        assertThat(discountInfo.amount()).isEqualTo(new Money(0));
        assertThat(discountInfo.eventName()).isEqualTo(Event.CHRISTMAS_DISCOUNT);
    }

    @Test
    @DisplayName("크리스마스 전 예약 시 할인 가격 확인.")
    void calculateDiscountAmountBeforeChristmas() {
        Day day = new Day(3);
        Order order = new Order(BBQ_RIBS);
        DiscountInfo discountInfo = christmasDiscountStrategy.calculateDiscountAmount(order, day);

        assertThat(discountInfo.amount()).isEqualTo(new Money(1200));
        assertThat(discountInfo.eventName()).isEqualTo(Event.CHRISTMAS_DISCOUNT);
    }

}