package com.yunchen.application;

public class MinedBlock extends Block{

    MinedBlock() {

    }

    @Override
    public String open() {
        isOpen = true;
        return "";
    }

}
