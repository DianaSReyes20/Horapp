package com.example.myapplicationhorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

public class Gallery extends AppCompatActivity {

    TextView estado;
    Spinner combomaterias;
    Spinner comboprofesor;
    Spinner combotema;
    Button okbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);

        estado = (TextView) findViewById(R.id.textView41);
        combomaterias = (Spinner) findViewById(R.id.idSpinnermaterias);
        comboprofesor = (Spinner) findViewById(R.id.idSpinnerprofesor);
        combotema = (Spinner) findViewById(R.id.idSpinnertema);
        okbtn = (Button) findViewById(R.id.button2);

        ArrayAdapter<CharSequence> Adapter1 = ArrayAdapter.createFromResource(
                this, R.array.combo_materias, android.R.layout.simple_spinner_item);
        combomaterias.setAdapter(Adapter1);

        ArrayAdapter<CharSequence> Adapter2 = ArrayAdapter.createFromResource(
                this, R.array.combo_profesor, android.R.layout.simple_spinner_item);
        comboprofesor.setAdapter(Adapter2);

        ArrayAdapter<CharSequence> Adapter3 = ArrayAdapter.createFromResource(
                this, R.array.combo_tema, android.R.layout.simple_spinner_item);
        combotema.setAdapter(Adapter3);
    }

    public void IrBuscarEvento(View view) {
        Intent BuscarEvento = new Intent(this, Documents.class);
        startActivity(BuscarEvento);
    }
}