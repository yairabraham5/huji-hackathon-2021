package com.example.hackthon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BubbleAdapter extends RecyclerView.Adapter {
    private static final int MESSAGE_SENT_BY_USER = 1;
    private static final int MESSAGE_SENT_BY_BOT = 2;
    ChatMessagesHolder chatMessagesHolder = null;

    public void setChatMessagesHolder(ChatMessagesHolder chatMessagesHolder){
        this.chatMessagesHolder = chatMessagesHolder;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType ==MESSAGE_SENT_BY_USER){
            View userView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_user, parent, false);
            return new UserBubbleViewHolder(userView);
        }
        if(viewType == MESSAGE_SENT_BY_BOT){
            View botView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_bot, parent, false);
            return new BotBubbleViewHolder(botView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BubbleChat bubbleChat = chatMessagesHolder.getChat().get(position);
        if(bubbleChat.getUserWriter()){
            TextView user_message_txv = ((UserBubbleViewHolder) holder).user_message_txv;
            TextView user_message_hour_txv = ((UserBubbleViewHolder) holder).user_message_hour_txv;
            user_message_txv.setText(bubbleChat.getMessage());
            int hour = bubbleChat.getTime().hour;
            int minutes = bubbleChat.getTime().minute;
            String time = hour + ":" + minutes;
            user_message_hour_txv.setText(time);
        }
        else{
            TextView user_message_txv = ((BotBubbleViewHolder) holder).bot_message_txv;
            TextView user_message_hour_txv = ((BotBubbleViewHolder) holder).bot_message_hour_txv;
            user_message_txv.setText(bubbleChat.getMessage());
            int hour = bubbleChat.getTime().hour;
            int minutes = bubbleChat.getTime().minute;
            String time = hour + ":" + minutes;
            user_message_hour_txv.setText(time);
        }

    }

    @Override
    public int getItemCount() {
        return chatMessagesHolder.getChat().size();
    }
}
