package christmas.domain.dto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateTest {

    @DisplayName("범위 외의 날짜에 대한 예외 처리")
    @ValueSource(ints = {-12, 0, 32})
    @ParameterizedTest
    void dayRangeException(Integer day) {
        assertThatThrownBy(() -> new Date(day))
                .isInstanceOf(IllegalArgumentException.class);
    }
}