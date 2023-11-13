package christmas.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import java.util.NoSuchElementException;

public class InputView {
    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String readString = readLine();
        return convertToInteger(readString);
    }
    private Integer convertToInteger(String value) {
        Integer convertedValue;
        try {
            convertedValue = Integer.parseInt(value);
        } catch (NumberFormatException | NoSuchElementException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
        return convertedValue;
    }

    public String readOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String readString = readLine();
        return readString;
    }
}
