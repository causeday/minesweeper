package com.yunchen.application;

public class NumberBlock extends Block {

    private int mineCount;

    NumberBlock(int mineCount) {
        this.mineCount = mineCount;
    }

    public int getMineCount(){
        return mineCount;
    }

    public void setMineCount(int mineCount){
        this.mineCount = mineCount;
    }

    @Override
    public String open() {
        isOpen = true;
        if(mineCount==0){
            return "";
        }
        return mineCount+"";
    }

}
