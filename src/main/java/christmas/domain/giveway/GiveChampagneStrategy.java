package christmas.domain.giveway;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.dto.Money;
import java.util.Optional;

public class GiveChampagneStrategy implements GiveawayEvent{
    private final Menu freeMenu = Menu.CHAMPAGNE;

    @Override
    public Optional<Menu> getFreeMenu(Order order) {
        if (order.getTotalPrice().isGreaterThan(new Money(120_000))) {
            return Optional.of(freeMenu);
        }
        return Optional.empty();
    }
}
