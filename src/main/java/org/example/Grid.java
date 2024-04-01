package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Grid {
    private boolean[][] grid;
    private int rows;
    private int cols;
    private int startRow, startCol;
    private int endRow, endCol;


    public Grid(String filename) {
        loadFromFile(filename);
    }

    private void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int row = 0;
            MyArrayList<String> lines = new MyArrayList<>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            rows = lines.size();
            cols = lines.get(0).length();
            grid = new boolean[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    char c = lines.get(i).charAt(j);
                    if (c == '#') {
                        grid[i][j] = true;
                    } else if (c == '@') {
                        startRow = i;
                        startCol = j;
                    } else if (c == 'G') {
                        endRow = i;
                        endCol = j;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static class MyArrayList<T> {
        private T[] data;
        private int size;
        private static final int INITIAL_CAPACITY = 10;

        public MyArrayList() {
            this.data = (T[]) new Object[INITIAL_CAPACITY];
            this.size = 0;
        }

        public void add(T element) {
            ensureCapacity();
            data[size++] = element;
        }

        public T get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            return data[index];
        }

        public int size() {
            return size;
        }

        private void ensureCapacity() {
            if (size == data.length) {
                T[] newData = (T[]) new Object[data.length * 2];
                System.arraycopy(data, 0, newData, 0, size);
                data = newData;
            }
        }
    }
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

//    public void setObstacle(int row, int col) {
//        grid[row][col] = true;
//    }

    public boolean isObstacle(int row, int col) {
        return grid[row][col];
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getEndCol() {
        return endCol;
    }
}