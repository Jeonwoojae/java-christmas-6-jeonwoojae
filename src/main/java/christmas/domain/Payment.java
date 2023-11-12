package christmas.domain;

import christmas.domain.badge.Badge;
import christmas.domain.badge.BadgeFactory;
import christmas.domain.discount.DateDiscountStrategy;
import christmas.domain.dto.Day;
import christmas.domain.dto.DiscountInfo;
import christmas.domain.giveway.GiveawayEvent;
import java.util.ArrayList;
import java.util.List;

public class Payment {
    private List<DateDiscountStrategy> discountStrategies;
    private List<GiveawayEvent> giveawayEvents;

    public Payment(List<DateDiscountStrategy> discountStrategies, List<GiveawayEvent> giveawayEvents) {
        this.discountStrategies = discountStrategies;
        this.giveawayEvents = giveawayEvents;
    }

    public void process(Order order, Day day) {
        List<DiscountInfo> totalDiscountInfo = processDiscount(order, day);
        List<Menu> freeMenus = processGiveawayEvent(order);
        Badge badge = BadgeFactory.getBadge(totalDiscountInfo);
    }

    private List<Menu> processGiveawayEvent(Order order) {
        List<Menu> freeMenus = new ArrayList<>();
        for (GiveawayEvent giveawayEvent : giveawayEvents) {
            Menu menu = giveawayEvent.getFreeMenu(order);
            freeMenus.add(menu);
        }
        return freeMenus;
    }

    private List<DiscountInfo> processDiscount(Order order, Day day) {
        List<DiscountInfo> totalDiscountInfo = new ArrayList<>();
        for (DateDiscountStrategy strategy : discountStrategies) {
            DiscountInfo discountInfo = strategy.calculateDiscountAmount(order, day);
            totalDiscountInfo.add(discountInfo);
        }
        return totalDiscountInfo;
    }
}
