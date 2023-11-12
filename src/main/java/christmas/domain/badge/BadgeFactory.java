package christmas.domain.badge;

import christmas.dto.DiscountInfo;
import christmas.dto.Money;
import java.util.List;

public class BadgeFactory {
    public static Badge getBadge(List<DiscountInfo> allDiscountedInfo) {
        Money totalDiscountedAmount = getTotalDiscountedAmount(allDiscountedInfo);
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

    // TODO 증정 이벤트도 계산에 포함되어야 함
    private static Money getTotalDiscountedAmount(List<DiscountInfo> allDiscountedInfo) {
        Money totalDiscountedAmount = new Money(0L);
        for (DiscountInfo info : allDiscountedInfo) {
            Money money = info.amount();
            totalDiscountedAmount = totalDiscountedAmount.add(money);
        }
        return totalDiscountedAmount;
    }
}

