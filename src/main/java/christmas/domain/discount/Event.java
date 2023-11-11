package christmas.domain.discount;

public enum Event {
    CHRISTMAS_DISCOUNT("크리스마스 할인"),
    ALWAYS_DISCOUNT("상시 할인"),
    SPECIAL_DISCOUNT("특별 할인");
    private final String eventName;

    Event(String event) {
        this.eventName = event;
    }
}
