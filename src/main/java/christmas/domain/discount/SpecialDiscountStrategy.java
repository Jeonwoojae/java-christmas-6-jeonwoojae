package christmas.domain.discount;

import christmas.domain.Order;
import christmas.constants.Config;
import christmas.dto.Day;
import christmas.dto.DiscountInfo;
import christmas.dto.Money;

public class SpecialDiscountStrategy implements DateDiscountStrategy{
    @Override
    public DiscountInfo calculateDiscountAmount(Order order, Day day) {
        Money discountAmount = new Money(0);
        if (Config.SPECIAL_DAYS.contains(day)) {
            discountAmount = new Money(1_000L);
        }
        return new DiscountInfo(Event.SPECIAL_DISCOUNT, discountAmount);
    }
}
