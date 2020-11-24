package com.yunchen.application;

/**
 * Parent class.
 * To be honest, it is not very necessary to use inheritance in this game...
 * @author Yunchen
 *
 */

abstract public class Block {

    private boolean isFlag;
    boolean isOpen;



    Block() {
        this.isFlag = false;
        this.isOpen = false;
    }

    public abstract String open();

    public void toggleFlag(){
        isFlag = !isFlag;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public boolean isOpen() {
        return isOpen;
    }
}