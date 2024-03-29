package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListTest {

    @Test
    void testConstructor() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    void testAdd() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        // Add to an empty list
        list.add(0, 1);
        assertEquals(1, list.get(0));
        assertEquals(1, list.size());

        // Add at the end
        list.add(list.size(), 2);
        assertEquals(2, list.get(1));
        assertEquals(2, list.size());

        // Add at the beginning
        list.add(0, 3);
        assertEquals(3, list.get(0));
        assertEquals(3, list.size());

        // Add in the middle
        list.add(2, 4);
        assertEquals(4, list.get(2));
        assertEquals(4, list.size());

        // Add when capacity needs to increase
        for (int i = 0; i < 8; i++) {
            list.add(list.size(), i + 5);
        }
        assertEquals(12, list.size());

        // Add at negative index
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 13));

        // Add at index greater than size
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(list.size() + 1, 14));
    }

    @Test
    void testRemove() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);
        list.add(3, 4);

        // Remove from the beginning
        assertEquals(1, list.remove(0));
        assertEquals(2, list.get(0));
        assertEquals(3, list.size());

        // Remove from the end
        assertEquals(4, list.remove(2));
        assertEquals(2, list.size());

        // Remove from the middle
        assertEquals(3, list.remove(1));
        assertEquals(2, list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertEquals(1, list.size());
        assertEquals(2, list.remove(0));

        // Remove from a list with single element
        list.add(0, 5);
        assertEquals(5, list.remove(0));
        assertTrue(list.isEmpty());

        // Remove at negative index
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));

        // Remove at index greater than or equal to size
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));
    }

    @Test
    void testGet() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);

        // Get from the beginning
        assertEquals(1, list.get(0));

        // Get from the end
        assertEquals(3, list.get(2));

        // Get from the middle
        assertEquals(2, list.get(1));

        // Get at negative index
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));

        // Get at index greater than or equal to size
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size()));
    }

    @Test
    void testSize() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        assertEquals(0, list.size());

        list.add(0, 1);
        list.add(1, 2);
        assertEquals(2, list.size());

        list.remove(0);
        assertEquals(1, list.size());
    }

    @Test
    void testIsEmpty() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        assertTrue(list.isEmpty());

        list.add(0, 1);
        assertFalse(list.isEmpty());
    }

    @Test
    void testEnsureCapacity() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add(i, i + 1);
        }
        assertEquals(15, list.size());

        for (int i = 0; i < list.size(); i++) {
            assertEquals(i + 1, list.get(i));
        }
    }

    @Test
    void testEdgeCases() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        // Add and remove in various combinations
        list.add(0, 1);
        list.add(1, 2);
        list.remove(0);
        list.add(0, 3);
        list.add(2, 4);
        list.remove(1);
        assertEquals(2, list.size());
        assertEquals(3, list.get(0));
        assertEquals(4, list.get(1));

        // Behavior at maximum capacity
        for (int i = 0; i < 20; i++) {
            list.add(list.size(), i + 5);
        }
        assertEquals(22, list.size());

        // Behavior with empty list
        list = new CustomArrayList<>();
        assertTrue(list.isEmpty());
    }
}