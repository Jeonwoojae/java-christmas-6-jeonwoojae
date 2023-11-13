package christmas.dto;

import christmas.domain.discount.Event;

public record DiscountInfo(Event eventName, Money amount) {
    @Override
    public String toString() {
        return eventName + ": -" + amount.toString();
    }
}
