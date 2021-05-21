package com.example.hackthon;
import android.text.format.Time;

import java.util.Calendar;


public class BubbleChat {
    boolean userMessage;
    String message;
    Time time_of_message;

    BubbleChat(String message, boolean userMessage){
        this.message = message;
        time_of_message = new Time();
        time_of_message.setToNow();
        this.userMessage = userMessage;
    }

    String getMessage(){
        return this.message;
    }

    Time getTime(){
        return this.time_of_message;
    }

    boolean getUserWriter(){
        return this.userMessage;
    }
}


