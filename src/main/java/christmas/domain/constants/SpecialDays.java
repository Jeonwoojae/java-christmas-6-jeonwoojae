package christmas.domain.constants;

import christmas.domain.dto.Day;
import java.util.Arrays;
import java.util.List;

public class SpecialDays {
    private static final Integer[] SPECIAL_DAYS_INFO = {3, 10, 17, 24, 25, 31};
    public static final List<Day> SPECIAL_DAYS = Arrays.stream(SPECIAL_DAYS_INFO).map(Day::new).toList();
}
