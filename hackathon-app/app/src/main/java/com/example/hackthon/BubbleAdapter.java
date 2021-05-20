package com.example.hackthon;

import android.view.LayoutInflater;
import android.view.ViewGroup;

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
            LayoutInflater.from(parent.getContext()).inflate()//todo
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
