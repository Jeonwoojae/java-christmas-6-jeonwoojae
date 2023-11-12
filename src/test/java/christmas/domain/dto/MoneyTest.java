package christmas.domain.dto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.dto.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @DisplayName("0원 미만의 금액에 대한 예외 처리")
    @ValueSource(strings = {"-123"})
    @ParameterizedTest
    void underValueMoney(Long input) {
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}