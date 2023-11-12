package christmas.domain.discount;

import christmas.domain.Order;
import christmas.dto.Day;
import christmas.dto.DiscountInfo;
import christmas.dto.Money;

public class ChristmasDiscountStrategy implements DateDiscountStrategy {
    private final Day CHRISTMAS_DAY = new Day(25);
    private final Money discountADay = new Money(100);
    private final Money discountStart = new Money(1000);

    @Override
    public DiscountInfo calculateDiscountAmount(Order order, Day day) {
        Money discountAmount = new Money(0);
        int leftDay = day.getLeftDay(CHRISTMAS_DAY);
        if (leftDay > 0) {
            // TODO 계산 과정 리팩토링 필요
            discountAmount = discountStart.add(discountADay.multiply(day.day())).minus(discountADay);
        }
        return new DiscountInfo(Event.CHRISTMAS_DISCOUNT, discountAmount);
    }
}
