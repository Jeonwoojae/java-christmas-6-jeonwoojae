package christmas.domain.dto;

import christmas.domain.Menu;
import christmas.domain.badge.Badge;
import java.util.List;

public record TotalBenefit(List<DiscountInfo> totalDiscountInfo, List<Menu> freeMenus, Badge badge) {

    public Money getTotalDiscount() {
        Money total = new Money(0);
        total = calculateDiscount(total);
        total = calculateGiveEventAmount(total);
        return total;
    }

    private Money calculateDiscount(Money total) {
        if (!totalDiscountInfo.isEmpty()) {
            for (DiscountInfo info : totalDiscountInfo) {
                total = total.add(info.amount());
            }
        }
        return total;
    }

    private Money calculateGiveEventAmount(Money total) {
        if (!freeMenus.isEmpty()) {
            for (Menu freeMenu : freeMenus) {
                total = total.add(freeMenu.getPrice());
            }
        }
        return total;
    }
}
