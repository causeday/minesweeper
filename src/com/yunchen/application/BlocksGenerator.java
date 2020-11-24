package com.yunchen.application;

/**
 * Generate all blocks with randomly distributed mines,
 * and calculate the number of mines in the neighborhood for non-mined blocks.
 * @author Yunchen
 */

public class BlocksGenerator {

    private int rows;
    private int cols;
    private int eleCount;
    private int mineCount;
    private final int[][] neighbors =
            {{-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}};

    public BlocksGenerator(Difficulty d) {
        this.rows = d.getRows();
        this.cols = d.getCols();
        this.mineCount = d.getMineCount();
        eleCount = rows * cols;
    }

    private boolean verify(int row, int col) {
        return row >= 0
                && row < this.rows
                && col >= 0
                && col < this.cols;
    }

    private Block[] randomizeMines(){
        Block[] blocks = new Block[eleCount];
        for(int i = 0; i< mineCount; i++){
            blocks[i] = new MinedBlock();
        }
        for(int i = eleCount-1; i>0; i--){
            int j = (int)(Math.random()*(i));
            Block temp = blocks[i];
            blocks[i] = blocks[j];
            blocks[j] = temp;
        }
        return blocks;
    }

    /*
     * Split one-dimension block array to two-dimension block array.
     */

    private Block[][] splitBlocks(){
        Block[][] newBlocks = new Block[rows][cols];
        Block[] blocks = this.randomizeMines();
        int index = 0;
        for(int i = 0; i< rows; i++){
            for(int j = 0; j< cols; j++) {
                newBlocks[i][j] = blocks[index];
                index++;
            }
        }
        return newBlocks;
    }

    private int[][] calculateMines(Block[][] blocks){
        int[][] mines = new int[rows][cols];
        for(int i = 0; i< rows; i++){
            for(int j=0;j<blocks[i].length;j++){
                if(blocks[i][j] instanceof MinedBlock) {
                    for(int[] neighbor: neighbors){
                        int newRow = i+neighbor[0];
                        int newCol = j+neighbor[1];
                        if(verify(newRow, newCol)){
                            mines[newRow][newCol]++;
                        }
                    }
                }
            }
        }
        return mines;
    }

    public Block[][] getBlocks(){
        Block[][] blocks = this.splitBlocks();
        int[][] minesCount = this.calculateMines(blocks);
        for(int i = 0; i< rows; i++){
            for(int j = 0; j< cols; j++){
                if(!(blocks[i][j] instanceof MinedBlock)){
                        blocks[i][j] = new NumberBlock(minesCount[i][j]);
                    }
                }
            }
        return blocks;
    }

}
