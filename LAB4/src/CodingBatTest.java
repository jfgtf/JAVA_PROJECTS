import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodingBatTest {

    private final CodingBat obj = new CodingBat();

    @Test
    public void firstLast6() {
        assertTrue(obj.firstLast6(new int[]{6, 5, 1}));
        assertTrue(obj.firstLast6(new int[]{1, 5, 6}));
        assertFalse(obj.firstLast6(new int[]{5, 5, 1}));
    }

    @Test
    public void sameFirstLast() {
        assertFalse(obj.sameFirstLast(new int[]{6, 5, 1}));
        assertFalse(obj.sameFirstLast(new int[]{6, 5, 1}));
        assertFalse(obj.sameFirstLast(new int[]{6, 5, 1}));
    }

    @Test
    public void makePi() {
        assertArrayEquals(new int[] {3, 1, 4}, obj.makePi());
    }

    @Test
    public void commonEnd() {
        assertTrue(obj.commonEnd(new int[]{6, 5, 1}, new int[]{6, 3, 6}));
        assertFalse(obj.commonEnd(new int[]{9, 5, 1}, new int[]{6, 3, 6}));
        assertTrue(obj.commonEnd(new int[]{1, 5, 1}, new int[]{6, 3, 1}));
    }

    @Test
    public void countEvens() {
        assertEquals(3, obj.countEvens(new int[] {1, 2, 3, 4, 5, 6}));
        assertEquals(0, obj.countEvens(new int[] {1, 9, 3, 9, 5, 11}));
        assertEquals(1, obj.countEvens(new int[] {1, 13, 3, 121, 5, 6}));
    }

    @Test
    public void bigDiff() {
        assertEquals(10, obj.bigDiff(new int[] {1, 11}));
        assertEquals(3, obj.bigDiff(new int[] {9, 8, 11, 10, 11}));
        assertEquals(24, obj.bigDiff(new int[] {33, 44, 52, 37, 42, 57}));
    }
}