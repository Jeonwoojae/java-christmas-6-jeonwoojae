package christmas.domain;

import christmas.domain.badge.Badge;
import christmas.domain.badge.BadgeFactory;
import christmas.domain.discount.DateDiscountStrategy;
import christmas.dto.Day;
import christmas.dto.DiscountInfo;
import christmas.dto.Money;
import christmas.dto.TotalBenefit;
import christmas.domain.giveway.GiveawayEvent;
import java.util.ArrayList;
import java.util.List;

public class Payment {
    private final List<DateDiscountStrategy> discountStrategies;
    private final List<GiveawayEvent> giveawayEvents;

    public Payment(List<DateDiscountStrategy> discountStrategies, List<GiveawayEvent> giveawayEvents) {
        this.discountStrategies = discountStrategies;
        this.giveawayEvents = giveawayEvents;
    }

    public TotalBenefit process(Order order, Day day) {
        List<DiscountInfo> totalDiscountInfo = new ArrayList<>();
        List<Menu> freeMenus = new ArrayList<>();
        if (isEventTarget(order)) {
            totalDiscountInfo = processDiscount(order, day);
            freeMenus = processGiveawayEvent(order);
        }
        Badge badge = BadgeFactory.getBadge(totalDiscountInfo);
        return new TotalBenefit(totalDiscountInfo, freeMenus, badge);
    }

    private boolean isEventTarget(Order order) {
        return (order.getTotalPrice()).isGreaterThan(new Money(10_000));
    }

    private List<Menu> processGiveawayEvent(Order order) {
        List<Menu> freeMenus = new ArrayList<>();
        for (GiveawayEvent giveawayEvent : giveawayEvents) {
            Menu menu = giveawayEvent.getFreeMenu(order);
            if (menu != null) {
                freeMenus.add(menu);
            }
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
