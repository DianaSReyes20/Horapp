package com.example.myapplicationhorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class buscarchat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscarchat);
    }
    public void IrAChat(View view) {
        Intent Chat = new Intent(this, Chat.class);
        startActivity(Chat);
    }
}