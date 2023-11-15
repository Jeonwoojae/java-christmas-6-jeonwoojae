package christmas.domain.badge;

import christmas.domain.Menu;
import christmas.dto.DiscountInfo;
import christmas.dto.Money;
import java.util.List;

public class BadgeFactory {
    public static Badge getBadge(List<DiscountInfo> allDiscountedInfo, List<Menu> freeMenus) {
        Money totalDiscountedAmount = getTotalDiscountedAmount(allDiscountedInfo, freeMenus);
        if (totalDiscountedAmount.isGreaterThan(new Money(20_000))) {
            return new SantaBadge();
        }
        if (totalDiscountedAmount.isGreaterThan(new Money(10_000))) {
            return new TreeBadge();
        }
        if (totalDiscountedAmount.isGreaterThan(new Money(5_000))){
            return new StarBadge();
        }
        return new NoneBadge();
    }

    private static Money getTotalDiscountedAmount(List<DiscountInfo> allDiscountedInfo, List<Menu> freeMenus) {
        Money totalDiscountedAmount = new Money(0L);
        totalDiscountedAmount = addDiscountedMoney(allDiscountedInfo, totalDiscountedAmount);
        totalDiscountedAmount = addFreeMenusMoney(freeMenus, totalDiscountedAmount);
        return totalDiscountedAmount;
    }

    private static Money addDiscountedMoney(List<DiscountInfo> allDiscountedInfo, Money totalDiscountedAmount) {
        for (DiscountInfo info : allDiscountedInfo) {
            Money money = info.amount();
            totalDiscountedAmount = totalDiscountedAmount.add(money);
        }
        return totalDiscountedAmount;
    }

    private static Money addFreeMenusMoney(List<Menu> freeMenus, Money totalDiscountedAmount) {
        for (Menu menu : freeMenus) {
            Money money = menu.getPrice();
            totalDiscountedAmount = totalDiscountedAmount.add(money);
        }
        return totalDiscountedAmount;
    }
    private BadgeFactory() {
        throw new IllegalArgumentException("[ERROR] 유틸리티 클래스는 생성할 수 없습니다.");
    }
}

