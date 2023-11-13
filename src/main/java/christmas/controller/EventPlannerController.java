package christmas.controller;

import christmas.domain.Order;
import christmas.domain.Payment;
import christmas.domain.discount.AlwaysDiscountStrategy;
import christmas.domain.discount.ChristmasDiscountStrategy;
import christmas.domain.discount.DateDiscountStrategy;
import christmas.domain.discount.SpecialDiscountStrategy;
import christmas.domain.giveway.GiveChampagneStrategy;
import christmas.domain.giveway.GiveawayEvent;
import christmas.dto.Day;
import christmas.dto.TotalBenefit;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class EventPlannerController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    List<DateDiscountStrategy> discountStrategies = new ArrayList<>(List.of(new AlwaysDiscountStrategy(),
            new ChristmasDiscountStrategy(),
            new SpecialDiscountStrategy()));
    List<GiveawayEvent> giveawayEvents = new ArrayList<>(List.of(new GiveChampagneStrategy()));
    private final Payment payment = new Payment(discountStrategies,giveawayEvents);

    public void run() {
        outputView.printWelcome();
        Day predictDay = getPredictDay();
        Order newOrder = getOrder();
        outputView.printBenefitPreviewMessage();
        printOrderDetails(newOrder);

        TotalBenefit totalBenefit = payment.process(newOrder, predictDay);
        printBenefitPreview(newOrder, totalBenefit);
    }

    private void printBenefitPreview(Order newOrder, TotalBenefit totalBenefit) {
        outputView.printGiveawayMenu(totalBenefit);
        outputView.printDiscountDetails(totalBenefit);
        outputView.printTotalBenefitAmount(totalBenefit);
        outputView.printExpectedPaymentAmount(newOrder, totalBenefit);
        outputView.printEventBadge(totalBenefit);
    }

    private void printOrderDetails(Order newOrder) {
        outputView.printOrder(newOrder);
        outputView.printTotalPriceBeforeDiscount(newOrder);
    }

    private Order getOrder() {
        Order newOrder;
        try {
            newOrder = new Order(inputView.readOrder());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            newOrder = getOrder();
        }
        return newOrder;
    }

    private Day getPredictDay() {
        Day predictDay;
        try {
            predictDay = new Day(inputView.readDate());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            predictDay = getPredictDay();
        }
        return predictDay;
    }
}
