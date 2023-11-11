package christmas.domain.discount;

import christmas.domain.Order;
import christmas.domain.dto.Day;

public interface DateDiscountStrategy {
    DiscountInfo calculateDiscountAmount(Order order, Day day);
}

