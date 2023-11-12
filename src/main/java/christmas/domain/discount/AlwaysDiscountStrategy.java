package christmas.domain.discount;

import christmas.domain.MenuCategory;
import christmas.domain.Order;
import christmas.dto.Day;
import christmas.dto.DiscountInfo;
import christmas.dto.Money;

public class AlwaysDiscountStrategy implements DateDiscountStrategy{
    @Override
    public DiscountInfo calculateDiscountAmount(Order order, Day day) {
        long count = 0;
        if (day.isWeekDay()) {
            count = order.getNumberOfMenuCategory(MenuCategory.DESSERT);

        }
        if (!day.isWeekDay()) {
            count = order.getNumberOfMenuCategory(MenuCategory.MAIN);
        }

        return new DiscountInfo(Event.ALWAYS_DISCOUNT, new Money(count*2023));
    }
}
