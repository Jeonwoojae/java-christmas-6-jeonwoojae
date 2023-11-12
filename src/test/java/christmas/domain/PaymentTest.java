package christmas.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.discount.AlwaysDiscountStrategy;
import christmas.domain.discount.ChristmasDiscountStrategy;
import christmas.domain.discount.DateDiscountStrategy;
import christmas.domain.discount.SpecialDiscountStrategy;
import christmas.dto.Day;
import christmas.dto.Money;
import christmas.dto.TotalBenefit;
import christmas.domain.giveway.GiveChampagneStrategy;
import christmas.domain.giveway.GiveawayEvent;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentTest {
    private final GiveChampagneStrategy giveChampagneStrategy = new GiveChampagneStrategy();
    private final AlwaysDiscountStrategy alwaysDiscountStrategy = new AlwaysDiscountStrategy();
    private final ChristmasDiscountStrategy christmasDiscountStrategy = new ChristmasDiscountStrategy();
    private final SpecialDiscountStrategy specialDiscountStrategy = new SpecialDiscountStrategy();
    private final List<DateDiscountStrategy> discountStrategyList = new ArrayList<>(
            List.of(alwaysDiscountStrategy, christmasDiscountStrategy, specialDiscountStrategy));
    private final List<GiveawayEvent> giveawayEventList = new ArrayList<>(List.of(giveChampagneStrategy));

    private final Payment payment = new Payment(discountStrategyList, giveawayEventList);

    private final String MENU_LIST = "초코케이크-1,아이스크림-1,티본스테이크-1,해산물파스타-1,시저샐러드-1";

    @Test
    @DisplayName("디데이할인 1200원 , 평일할인 4046원, 특별할인 1000원")
    void process() {
        Day day = new Day(3);
        Order order = new Order(MENU_LIST);
        TotalBenefit totalBenefit = payment.process(order,day);
        Money totalDiscountMoney = totalBenefit.getTotalDiscount();

        assertThat(totalDiscountMoney).isEqualTo(new Money(6_246L));
    }
}