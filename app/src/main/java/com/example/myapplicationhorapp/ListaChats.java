package com.example.myapplicationhorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ListaChats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_chats);
    }

    public void IrChat(View view) {
        Intent Chat = new Intent(this, Chat.class);
        startActivity(Chat);
    }
}