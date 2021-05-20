package com.example.hackthon;
import android.text.format.Time;

import java.util.Calendar;

public class BubbleChat {
    String message;
    Time time_of_message;
    BubbleChat(String message){
        this.message = message;
        time_of_message = new Time();
        time_of_message.setToNow();
    }

    String getMessage(){
        return this.message;
    }

    Time getTime(){
        return this.time_of_message;}
}


