package com.example.myapplicationhorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Calendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendario);
    }

    public void IrCamera(View view) {
        Intent Camera = new Intent(this, Camera.class);
        startActivity(Camera);
    }
}
