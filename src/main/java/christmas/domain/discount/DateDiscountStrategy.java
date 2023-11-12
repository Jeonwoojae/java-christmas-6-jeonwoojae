package christmas.domain.discount;

import christmas.domain.Order;
import christmas.dto.Day;
import christmas.dto.DiscountInfo;

public interface DateDiscountStrategy {
    DiscountInfo calculateDiscountAmount(Order order, Day day);
}

