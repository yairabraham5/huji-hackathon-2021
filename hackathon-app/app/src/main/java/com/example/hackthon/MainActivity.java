package com.example.hackthon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton chatButton = findViewById(R.id.new_conversation);


        //TODO: add bot class
        chatButton.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, ChatActivity.class);
            startActivity(intent);

        });
    }
}