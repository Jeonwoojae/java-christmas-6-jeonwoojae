package christmas.domain;

import christmas.domain.dto.Money;
import java.util.Arrays;
import java.util.Optional;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", new Money(6000), MenuCategory.APPETIZER),
    TAPAS("타파스", new Money(5500), MenuCategory.APPETIZER),
    CAESAR_SALAD("시저샐러드", new Money(8000), MenuCategory.APPETIZER),

    T_BONE_STEAK("티본스테이크", new Money(55000), MenuCategory.MAIN),
    BBQ_RIBS("바비큐립", new Money(54000), MenuCategory.MAIN),
    SEAFOOD_PASTA("해산물파스타", new Money(35000), MenuCategory.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", new Money(25000), MenuCategory.MAIN),

    CHOCO_CAKE("초코케이크", new Money(15000), MenuCategory.DESSERT),
    ICE_CREAM("아이스크림", new Money(5000), MenuCategory.DESSERT),

    ZERO_COLA("제로콜라", new Money(3000), MenuCategory.DRINK),
    RED_WINE("레드와인", new Money(60000), MenuCategory.DRINK),
    CHAMPAGNE("샴페인", new Money(25000), MenuCategory.DRINK);

    private final String name;
    private final Money price;
    private final MenuCategory category;

    Menu(String name, Money price, MenuCategory category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public static Optional<Menu> findMenuByName(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(name))
                .findFirst();
    }

    public String getName() {
        return name;
    }
}