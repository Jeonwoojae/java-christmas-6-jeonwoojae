package christmas.domain.dto;

import christmas.domain.discount.Event;

public record DiscountInfo(Event eventName, Money amount) {
}
