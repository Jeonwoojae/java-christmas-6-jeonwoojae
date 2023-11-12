package christmas.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.domain.dto.Money;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderTest {
    private final String DESSERT_LIST = "초코케이크-1,아이스크림-1";
    private final Money DESSERT_PRICE = new Money(20_000L);
    private final String MAIN_MENU_LIST = "티본스테이크-1,해산물파스타-1";
    private final String APPETIZER_LIST = "시저샐러드-1";
    private final String INCORRECT_MENU = "티본스테이크-1,없는메뉴-1";
    private final String DUPLICATE_MENU = "티본스테이크-1,티본스테이크-1";
    private final String EXCESS_MENU = "티본스테이크-20,바비큐립-1";
    private final String ONLY_DRINK = "제로콜라-1";
    private final String INVALID_FORMAT_MENU = "티본스테이크,바비큐립-1";
    private final String INVALID_QUANTITY_MENU = "티본스테이크-abc,바비큐립-1";
    private final String NO_SEPARATOR_MENU = "티본스테이크-1바비큐립-1";

    @Test
    @DisplayName("주문메뉴 사이에 구분자가 없을 경우 예외가 발생한다.")
    public void orderWithNoSeparatorTest() {
        assertThrows(IllegalArgumentException.class, () -> new Order(NO_SEPARATOR_MENU));
    }

    @Test
    @DisplayName("수량 구분자가 없을 경우 예외가 발생한다.")
    public void orderWithInvalidFormatMenuTest() {
        assertThrows(IllegalArgumentException.class, () -> new Order(INVALID_FORMAT_MENU));
    }

    @Test
    @DisplayName("수량이 숫자가 아닌 경우 예외가 발생한다.")
    public void orderWithInvalidQuantityMenuTest() {
        assertThrows(IllegalArgumentException.class, () -> new Order(INVALID_QUANTITY_MENU));
    }
    @ParameterizedTest
    @DisplayName("수량이 0보다 작을 경우 예외가 발생한다.")
    @ValueSource(strings = {"티본스테이크--1,바비큐립-1", "티본스테이크-0,바비큐립-1"})
    public void orderWithInvalidQuantityTest(String orderInput) {
        assertThrows(IllegalArgumentException.class, () -> new Order(orderInput));
    }

    @Test
    @DisplayName("주문 성공 테스트")
    public void orderCreateTest() {
        assertDoesNotThrow(() -> new Order(MAIN_MENU_LIST));
    }

    @Test
    @DisplayName("존재하지 않는 메뉴를 주문 시 예외가 발생한다.")
    public void orderWithNonexistentMenuTest() {
        assertThrows(IllegalArgumentException.class, () -> new Order(INCORRECT_MENU));
    }

    @Test
    @DisplayName("중복 주문 시 예외가 발생한다.")
    public void orderWithDuplicateMenuTest() {
        assertThrows(IllegalArgumentException.class, () -> new Order(DUPLICATE_MENU));
    }

    @Test
    @DisplayName("20개 초과 주문 시 예외가 발생한다.")
    public void orderWithExcessMenuTest() {
        assertThrows(IllegalArgumentException.class, () -> new Order(EXCESS_MENU));
    }

    @Test
    @DisplayName("Drink 메뉴만 주문 시 예외가 발생한다.")
    public void orderWithOnlyDrinkTest() {
        assertThrows(IllegalArgumentException.class, () -> new Order(ONLY_DRINK));
    }

    @Test
    @DisplayName("디저트메뉴 갯수 Count 테스트")
    void getNumberOfDesserts() {
        Order order = new Order(DESSERT_LIST);

        assertThat(order.getNumberOfMenuCategory(MenuCategory.DESSERT)).isEqualTo(2);
    }

    @Test
    @DisplayName("여러 메뉴들 중 디저트메뉴 갯수만 Count할 수 있다.")
    void getNumberOfDessertsOnly() {
        List<String> orderList = new ArrayList<>();
        orderList.add(DESSERT_LIST);
        orderList.add(MAIN_MENU_LIST);
        orderList.add(APPETIZER_LIST);
        Order order = new Order(String.join(",", orderList));

        assertThat(order.getNumberOfMenuCategory(MenuCategory.DESSERT)).isEqualTo(2);
    }

    @Test
    @DisplayName("주문한 음식 가격의 총합을 정확히 반환한다.")
    void getTotalPrice() {
        Order order = new Order(DESSERT_LIST);

        assertThat(order.getTotalPrice()).isEqualTo(DESSERT_PRICE);
    }
}