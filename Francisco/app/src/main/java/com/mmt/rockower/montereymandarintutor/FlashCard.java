package com.mmt.rockower.montereymandarintutor;


public class FlashCard {
    private String front;
    private String back;

    public FlashCard(String front, String back)
    {
        this.front = front;
        this.back = back;
    }

    public String getFront(){
        return front;
    }

    public String getBack(){
        return back;
    }
}
