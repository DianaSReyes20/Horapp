package com.example.myapplicationhorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LogIn extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicioapp);
    }

    public void IrFacebook(View view) {
        Intent Facebook = new Intent(this, Facebook.class);
        startActivity(Facebook);
    }
    public void IrMenu(View view) {
        Intent Menu = new Intent(this, Menu.class);
        startActivity(Menu);
    }
}
