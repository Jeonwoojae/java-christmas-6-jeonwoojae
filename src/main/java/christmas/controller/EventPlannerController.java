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
        Day predictDay = new Day(inputView.readDate());
        Order newOrder = new Order(inputView.readOrder());

        outputView.printBenefitPreviewMessage();
        outputView.printOrder(newOrder);
        outputView.printTotalPriceBeforeDiscount(newOrder);

        TotalBenefit totalBenefit = payment.process(newOrder, predictDay);
        outputView.printGiveawayMenu(totalBenefit);
        outputView.printDiscountDetails(totalBenefit);
        outputView.printTotalBenefitAmount(totalBenefit);
        outputView.printExpectedPaymentAmount(newOrder, totalBenefit);
        outputView.printEventBadge(totalBenefit);
    }
}
