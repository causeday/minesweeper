package com.yunchen.application;

/**
 * Indicate different levels and their configurations.
 * @author Yunchen
 */

public enum Difficulty{
    LOW(9,9,10), MEDIUM(16,16,40), HIGH(16,30,99);

    private int rows;
    private int cols;
    private int mineCount;
    private int eleCount;

    Difficulty(int rows, int ylen, int mineCount){
        this.rows = rows;
        this.cols = ylen;
        this.mineCount = mineCount;
        this.eleCount = rows *ylen;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getMineCount() {
        return mineCount;
    }

    public int getEleCount() {
        return eleCount;
    }
}
