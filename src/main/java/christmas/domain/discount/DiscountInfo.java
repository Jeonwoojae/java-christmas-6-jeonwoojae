package christmas.domain.discount;

import christmas.domain.dto.Money;

public record DiscountInfo(Event eventName, Money amount) {
}
