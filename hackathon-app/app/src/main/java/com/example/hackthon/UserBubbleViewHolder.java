package com.example.hackthon;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserBubbleViewHolder extends RecyclerView.ViewHolder {
    TextView user_message;

    public UserBubbleViewHolder(@NonNull  View itemView) {
        super(itemView);
        user_message = itemView.findViewById(R.id.user_message);
    }
}
