package com.example.hackthon;

import java.util.List;

public interface ChatMessagesHolder {

    public void addChat(String chat,boolean userMessage);

    public List<BubbleChat> getChat();





}
