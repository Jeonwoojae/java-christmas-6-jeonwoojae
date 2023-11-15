package christmas.domain.badge;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.discount.Event;
import christmas.dto.DiscountInfo;
import christmas.dto.Money;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeFactoryTest {
    private final DiscountInfo FiveThousandPrice = new DiscountInfo(Event.ALWAYS_DISCOUNT, new Money(5_000L));
    private final DiscountInfo TenThousandPrice = new DiscountInfo(Event.ALWAYS_DISCOUNT, new Money(10_000L));
    private final DiscountInfo TwentyThousandPrice = new DiscountInfo(Event.ALWAYS_DISCOUNT, new Money(20_000L));
    private final DiscountInfo NoneBadgePrice = new DiscountInfo(Event.ALWAYS_DISCOUNT, new Money(4_999L));

    @Test
    @DisplayName("혜택 금액이 5천원 이상이면 별 배지를 제공한다.")
    void getStarBadge() {
        List<DiscountInfo> fiveThousandPrice = new ArrayList<>(List.of(FiveThousandPrice));
        Badge badge = BadgeFactory.getBadge(fiveThousandPrice, null);
        assertInstanceOf(StarBadge.class, badge);
    }

    @Test
    @DisplayName("혜택 금액이 1만원 이상이면 트리 배지를 제공한다.")
    void getTreeBadge() {
        List<DiscountInfo> tenThousandPrice = new ArrayList<>(List.of(TenThousandPrice));
        Badge badge = BadgeFactory.getBadge(tenThousandPrice, null);
        assertInstanceOf(TreeBadge.class, badge);
    }

    @Test
    @DisplayName("혜택 금액이 2만원 이상이면 산타 배지를 제공한다.")
    void getSantaBadge() {
        List<DiscountInfo> twentyThousandPrice = new ArrayList<>(List.of(TwentyThousandPrice));
        Badge badge = BadgeFactory.getBadge(twentyThousandPrice, null);
        assertInstanceOf(SantaBadge.class, badge);
    }

    @Test
    @DisplayName("혜택 금액이 5천원 미만이면 배지를 제공하지 않는다.")
    void getNoneBadge() {
        List<DiscountInfo> noneBadgePrice = new ArrayList<>(List.of(NoneBadgePrice));
        Badge badge = BadgeFactory.getBadge(noneBadgePrice, null);
        assertInstanceOf(NoneBadge.class, badge);
    }
}