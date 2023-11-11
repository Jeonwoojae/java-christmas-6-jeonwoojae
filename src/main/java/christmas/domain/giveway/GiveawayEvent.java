package christmas.domain.giveway;

import christmas.domain.Menu;
import christmas.domain.Order;

public interface GiveawayEvent {
    Menu getFreeMenu(Order order);
}
