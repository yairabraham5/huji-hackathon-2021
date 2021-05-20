package com.example.hackthon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChatMessagesHolderImp implements ChatMessagesHolder{
    List<BubbleChat> bubbleChatList;

    public ChatMessagesHolderImp(){
        bubbleChatList = new ArrayList<>();
    }

    public void addChat(String chat,boolean userMessage){
        BubbleChat bubbleChat = new BubbleChat(chat, userMessage);
        bubbleChatList.add(bubbleChat);
    }

    public List<BubbleChat> getChat(){
        return bubbleChatList;
    }



}
