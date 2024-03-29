package org.example;

public class Robot {
    private int row;
    private int col;
    private Orientation orientation;

    public Robot(int row, int col, Orientation orientation) {
        this.row = row;
        this.col = col;
        this.orientation = orientation;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public enum Orientation {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }
}