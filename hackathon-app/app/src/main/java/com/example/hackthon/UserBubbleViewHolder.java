package com.example.hackthon;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserBubbleViewHolder extends RecyclerView.ViewHolder {
    TextView user_message_txv;
    TextView user_message_hour_txv;


    public UserBubbleViewHolder(@NonNull  View itemView) {
        super(itemView);
        user_message_txv = itemView.findViewById(R.id.user_message);
        user_message_hour_txv = itemView.findViewById(R.id.user_message_hour);
    }
}
