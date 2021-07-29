package com.epam.sandbox;

public class MutableInteger {
    private int value;
    public MutableInteger(){
        this.value=0;
    }
    public MutableInteger(int value){
        this.value=value;
    }
    public int get(){
        return value;
    }
    public void set(int value){
        this.value=value;
    }
    public void increment(){
        value++;
    }
}
