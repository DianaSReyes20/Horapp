package com.example.myapplicationhorapp;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Chat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
    }

    public void IrCamera(View view) {
        Intent Camera = new Intent(this, Camera.class);
        startActivity(Camera);
    }

    public void irDocuments(View view) {
        Intent Documents = new Intent(this, Gallery.class);
        startActivity(Documents);
    }
}
