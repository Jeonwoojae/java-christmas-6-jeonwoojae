package christmas.domain;

import static christmas.domain.Menu.CAESAR_SALAD;
import static christmas.domain.Menu.CHOCO_CAKE;
import static christmas.domain.Menu.ICE_CREAM;
import static christmas.domain.Menu.SEAFOOD_PASTA;
import static christmas.domain.Menu.T_BONE_STEAK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.discount.AlwaysDiscountStrategy;
import christmas.domain.discount.ChristmasDiscountStrategy;
import christmas.domain.discount.DateDiscountStrategy;
import christmas.domain.discount.SpecialDiscountStrategy;
import christmas.domain.dto.Day;
import christmas.domain.dto.Money;
import christmas.domain.dto.TotalBenefit;
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

    private final List<Menu> MENU_LIST = new ArrayList<>(
            List.of(CHOCO_CAKE, ICE_CREAM, T_BONE_STEAK, SEAFOOD_PASTA, CAESAR_SALAD));

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