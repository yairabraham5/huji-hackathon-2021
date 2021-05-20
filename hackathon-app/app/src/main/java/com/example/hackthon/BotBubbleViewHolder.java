package com.example.hackthon;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BotBubbleViewHolder extends RecyclerView.ViewHolder {
    TextView bot_message_txv;
    TextView bot_message_hour_txv;

    public BotBubbleViewHolder(@NonNull  View itemView) {
        super(itemView);
        bot_message_txv = itemView.findViewById(R.id.bot_message);
        bot_message_hour_txv = itemView.findViewById(R.id.bot_message_hour);
    }
}
