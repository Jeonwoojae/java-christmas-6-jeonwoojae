package christmas.domain.dto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.dto.Day;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DayTest {
    private final List<Integer> TEST_WEEK_DAYS = Arrays.asList(3,4,5,6,7,10,11,12,13,14,17,18,19,20,21,24,25,26,27,28,31);
    private final List<Integer> TEST_WEEKEND_DAYS = Arrays.asList(1,2,8,9,15,16,22,23,29,30);

    @DisplayName("범위 외의 날짜에 대한 예외 처리")
    @ValueSource(ints = {-12, 0, 32})
    @ParameterizedTest
    void dayRangeException(Integer day) {
        assertThatThrownBy(() -> new Day(day))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("주말이라면 true를 반환")
    void isWeekDay_True() {
        List<Day> days = TEST_WEEK_DAYS.stream().map(day -> new Day(day)).toList();

        assertTrue(days.stream().allMatch(day -> day.isWeekDay()));
    }

    @Test
    @DisplayName("주말이라면 false를 반환")
    void isWeekDay_False() {
        List<Day> days = TEST_WEEKEND_DAYS.stream().map(day -> new Day(day)).toList();

        assertFalse(days.stream().allMatch(day -> day.isWeekDay()));
    }
}