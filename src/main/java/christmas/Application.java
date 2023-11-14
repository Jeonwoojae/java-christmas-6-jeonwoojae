package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.EventPlannerController;

public class Application {
    public static void main(String[] args) {
        EventPlannerController controller = new EventPlannerController();
        controller.run();
        Console.close();
    }
}
