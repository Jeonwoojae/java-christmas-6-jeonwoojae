package christmas.controller;

import christmas.domain.Order;
import christmas.dto.Day;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlannerController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        outputView.printWelcome();
        Day predictDay = new Day(inputView.readDate());
        Order newOrder = new Order(inputView.readOrder());
    }
}
