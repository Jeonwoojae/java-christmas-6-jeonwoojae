package christmas.domain.dto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.dto.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {
    private static final Money HIGH_MONEY = new Money(200);
    private static final Money LOW_MONEY = new Money(100);
    private static final Money LOW_MONEY_2 = new Money(100);

    @DisplayName("0원 미만의 금액에 대한 예외 처리")
    @ValueSource(strings = {"-123"})
    @ParameterizedTest
    void underValueMoney(Long input) {
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("비교할 Money 양에 따라 CompareTo는 3종류의 값을 반환할 수 있음")
    @Test
    void testCompareTo() {
        assertTrue(LOW_MONEY.compareTo(HIGH_MONEY) < 0);
        assertTrue(HIGH_MONEY.compareTo(LOW_MONEY) > 0);
        assertTrue(LOW_MONEY.compareTo(LOW_MONEY) == 0);
    }

    @Test
    @DisplayName("같은 값을 가지고 있는 객체는 같다고 할 수 있음")
    void testEquals() {
        assertEquals(LOW_MONEY, LOW_MONEY_2);
        assertEquals(LOW_MONEY, LOW_MONEY);
        assertNotEquals(LOW_MONEY, HIGH_MONEY);
        assertNotEquals(LOW_MONEY, null);
        assertNotEquals(LOW_MONEY, new Object());
    }

    @Test
    @DisplayName("같은 값을 가진 Money는 같은 해시코드")
    void testHashCode() {
        assertEquals(LOW_MONEY.hashCode(), LOW_MONEY_2.hashCode());
        assertNotEquals(LOW_MONEY.hashCode(), HIGH_MONEY.hashCode());
    }
}