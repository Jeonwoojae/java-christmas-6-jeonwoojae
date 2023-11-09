package christmas.domain.dto;

public record Date(int day) {
    public Date {
        checkDayRange(day);
    }

    private void checkDayRange(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
