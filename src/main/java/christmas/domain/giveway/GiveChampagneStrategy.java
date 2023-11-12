package christmas.domain.giveway;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.dto.Money;

public class GiveChampagneStrategy implements GiveawayEvent{
    private final Menu freeMenu = Menu.CHAMPAGNE;

    @Override
    public Menu getFreeMenu(Order order) {
        if (order.getTotalPrice().compareTo(new Money(120_000)) == 1) {
            return freeMenu;
        }
        // TODO null을 반환하는게 맞을까?
        return null;
    }
}
