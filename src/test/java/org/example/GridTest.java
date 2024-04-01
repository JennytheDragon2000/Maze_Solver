package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


class GridTest {

    private Grid grid;

    @Test
    void testLoadFromFile(@TempDir Path tempDir) throws IOException {
        // Create a temporary file with grid data
        String gridData = "....\n" +
                ".@#.\n" +
                "..#G\n" +
                "....";
        Path file = tempDir.resolve("grid.txt");
        Files.writeString(file, gridData);

        // Create a Grid instance from the file
        Grid grid = new Grid(file.toString());

        // Assert the loaded grid data
        assertEquals(4, grid.getRows());
        assertEquals(4, grid.getCols());
        assertEquals(1, grid.getStartRow());
        assertEquals(1, grid.getStartCol());
        assertEquals(2, grid.getEndRow());
        assertEquals(3, grid.getEndCol());
        assertFalse(grid.isObstacle(0, 0));
        assertTrue(grid.isObstacle(1, 2));
        assertTrue(grid.isObstacle(2, 2));
    }
    @Test
    void testMyArrayListAdd() {
        Grid.MyArrayList<String> list = new Grid.MyArrayList<>();
        list.add("hello");
        list.add("world");
        assertEquals(2, list.size());
        assertEquals("hello", list.get(0));
        assertEquals("world", list.get(1));
    }

    @Test
    void testMyArrayListGrow() {
        Grid.MyArrayList<String> list = new Grid.MyArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("test");
        }
        assertEquals(20, list.size());
    }

    @Test
    void testMyArrayListIndexOutOfBounds() {
        Grid.MyArrayList<String> list = new Grid.MyArrayList<>();
        list.add("hello");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }
}