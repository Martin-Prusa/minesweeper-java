package cz.educanet.minesweeper.logic;

public class Cell {

    private int fieldState;
    private boolean isBomb;

    public Cell() {
        this.fieldState = 0;
        this.isBomb = false;
    }

    public int getFieldState() {
        return fieldState;
    }

    public void setFieldState(int fieldState) {
        this.fieldState = fieldState;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }
}