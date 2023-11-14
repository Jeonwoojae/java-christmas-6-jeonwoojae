package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuCategoryTest {

    @Test
    @DisplayName("getCategory 메소드가 각 MenuCategory의 이름을 올바르게 반환하는지 확인")
    void getCategory() {
        assertEquals("애피타이저", MenuCategory.APPETIZER.getCategory());
        assertEquals("메인", MenuCategory.MAIN.getCategory());
        assertEquals("디저트", MenuCategory.DESSERT.getCategory());
        assertEquals("음료", MenuCategory.DRINK.getCategory());
    }

    @Test
    @DisplayName("values 메소드가 MenuCategory의 모든 enum 상수를 배열로 반환하는지, 그리고 그 순서가 정의된 순서와 같은지 확인")
    void values() {
        MenuCategory[] menuCategories = MenuCategory.values();
        assertEquals(4, menuCategories.length);
        assertEquals(MenuCategory.APPETIZER, menuCategories[0]);
        assertEquals(MenuCategory.MAIN, menuCategories[1]);
        assertEquals(MenuCategory.DESSERT, menuCategories[2]);
        assertEquals(MenuCategory.DRINK, menuCategories[3]);
    }

    @Test
    @DisplayName("valueOf 메소드가 주어진 이름의 MenuCategory를 올바르게 반환하는지 확인합니다. 또한 존재하지 않는 이름을 주었을 때 IllegalArgumentException이 발생하는지도 확인")
    void valueOf() {
        assertEquals(MenuCategory.APPETIZER, MenuCategory.valueOf("APPETIZER"));
        assertEquals(MenuCategory.MAIN, MenuCategory.valueOf("MAIN"));
        assertEquals(MenuCategory.DESSERT, MenuCategory.valueOf("DESSERT"));
        assertEquals(MenuCategory.DRINK, MenuCategory.valueOf("DRINK"));

        assertThrows(IllegalArgumentException.class, () -> MenuCategory.valueOf("NOT_EXIST"));
    }
}