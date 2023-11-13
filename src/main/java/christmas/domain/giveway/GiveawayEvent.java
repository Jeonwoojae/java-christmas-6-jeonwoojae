package christmas.domain.giveway;

import christmas.domain.Menu;
import christmas.domain.Order;
import java.util.Optional;

public interface GiveawayEvent {
    Optional<Menu> getFreeMenu(Order order);
}
