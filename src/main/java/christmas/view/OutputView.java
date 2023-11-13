package christmas.view;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.dto.DiscountInfo;
import christmas.dto.Money;
import christmas.dto.TotalBenefit;
import java.util.List;

public class OutputView {
    public void printWelcome() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printBenefitPreviewMessage() {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void printOrder(Order newOrder) {
        System.out.println();
        System.out.println("<주문 메뉴>");
        System.out.println(newOrder);
    }

    public void printTotalPriceBeforeDiscount(Order order) {
        System.out.println("<할인 전 총주문 금액>");
        Money totalPrice = order.getTotalPrice();
        System.out.println(totalPrice);
    }

    public void printGiveawayMenu(TotalBenefit totalBenefit) {
        System.out.println();
        System.out.println("<증정 메뉴>");
        List<Menu> freeMenus = totalBenefit.freeMenus();
        if (freeMenus.size() == 0) {
            System.out.println("없음");
            return;
        }
        for (Menu menu : freeMenus) {
            System.out.println(menu.getName() + " 1개");
        }
    }

    public void printEventBadge(TotalBenefit totalBenefit) {
        System.out.println();
        System.out.println("<12월 이벤트 배지>");
        System.out.println(totalBenefit.badge().getName());
    }

    public void printExpectedPaymentAmount(Order order, TotalBenefit totalBenefit) {
        Money beforeDiscount = order.getTotalPrice();
        Money discountAmount = totalBenefit.getOnlyDiscountedMoney();
        Money afterDiscount = beforeDiscount.minus(discountAmount);

        System.out.println();
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(afterDiscount);
    }

    public void printTotalBenefitAmount(TotalBenefit totalBenefit) {
        System.out.println();
        System.out.println("<총혜택 금액>");
        Money totalDiscountPrice = totalBenefit.getTotalDiscount();
        if (totalDiscountPrice.equals(new Money(0))) {
            System.out.print("-");
        }
        System.out.print(totalDiscountPrice);
    }

    public void printDiscountDetails(TotalBenefit totalBenefit) {
        System.out.println();
        System.out.println("<혜택 내역>");
        if (totalBenefit.getTotalDiscount().equals(new Money(0))) {
            System.out.println("없음");
            return;
        }
        List<DiscountInfo> discountInfos = totalBenefit.totalDiscountInfo();
        for (DiscountInfo discountInfo : discountInfos) {
            if (discountInfo.amount().equals(new Money(0))) {
                continue;
            }
            System.out.println(discountInfo);
        }
    }
}
