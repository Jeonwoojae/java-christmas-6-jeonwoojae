package christmas.domain.badge;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {

    @Test
    @DisplayName("각 배지 이름 테스트")
    void getName() {
        StarBadge starBadge = new StarBadge();
        SantaBadge santaBadge = new SantaBadge();
        TreeBadge treeBadge = new TreeBadge();
        NoneBadge noneBadge = new NoneBadge();

        assertEquals(starBadge.getName(), "별");
        assertEquals(santaBadge.getName(), "산타");
        assertEquals(treeBadge.getName(), "트리");
        assertEquals(noneBadge.getName(), "없음");
    }
}