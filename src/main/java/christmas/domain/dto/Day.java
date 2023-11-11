package christmas.domain.dto;

public record Day(int day) implements Comparable<Day> {
    public Day {
        checkDayRange(day);
    }

    private void checkDayRange(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    @Override
    public int compareTo(Day otherDay) {
        return Integer.compare(this.day, otherDay.day);
    }

    public Integer getLeftDay(Day otherDay) {
        return otherDay.day - this.day;
    }

    public boolean isWeekDay() {
        int dayOfWeek = this.day % 7;
        // 1일이 금요일이므로, 1일부터 3일까지 (금요일, 토요일, 일요일)와 7일 (목요일)은 주말입니다.
        if (dayOfWeek == 1 || dayOfWeek == 2) {
            return false;
        }
        return true;
    }
}
