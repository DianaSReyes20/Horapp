package com.example.myapplicationhorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }

    public void irCalendario(View view) {
        Intent Calendario = new Intent(this, Calendar.class);
        startActivity(Calendario);
    }

    public void irChats(View view) {
        Intent Chats = new Intent(this, buscarchat.class);
        startActivity(Chats);
    }

    public void IrNotificaciones(View view) {
        Intent Notificaciones = new Intent(this, MainActivity.class);
        startActivity(Notificaciones);
    }

    public void IrEdPerfil(View view) {
        Intent EdPerfil = new Intent(this, EditarPerfil.class);
        startActivity(EdPerfil);
    }
}
