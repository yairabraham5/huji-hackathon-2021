package com.example.hackthon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BubbleAdapter bubbleAdapter;
    ChatMessagesHolder chatMessagesHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        recyclerView = findViewById(R.id.recycler_chat);
        bubbleAdapter = new BubbleAdapter();
        chatMessagesHolder = new ChatMessagesHolderImp();
        bubbleAdapter.setChatMessagesHolder(chatMessagesHolder);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(bubbleAdapter);
        EditText editText = findViewById(R.id.editTextInsertTask);
        FloatingActionButton floatingActionButton = findViewById(R.id.buttonSend);
        floatingActionButton.setOnClickListener(v->{
            int length = editText.getText().toString().length();
            if(length != 0){
                //TODO call amitsour model
                chatMessagesHolder.addChat(editText.getText().toString(), true);
                editText.setText("");
                bubbleAdapter.notifyDataSetChanged();
            }

        });
    }
}