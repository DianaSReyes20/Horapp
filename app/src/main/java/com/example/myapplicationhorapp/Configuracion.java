package com.example.myapplicationhorapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class Configuracion extends AppCompatActivity {

    /*TextView estado;
    Spinner combomaterias;
    Spinner comboprofesor;
    Spinner combotema;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuracion);

        /*estado = (TextView) findViewById(R.id.textView41);
        combomaterias = (Spinner) findViewById(R.id.idSpinnermaterias);
        comboprofesor = (Spinner) findViewById(R.id.idSpinnerprofesor);
        combotema = (Spinner) findViewById(R.id.idSpinnertema);

        ArrayAdapter<CharSequence> Adapter1 = ArrayAdapter.createFromResource(
                this, R.array.combo_materias, android.R.layout.simple_spinner_item);
        combomaterias.setAdapter(Adapter1);

        ArrayAdapter<CharSequence> Adapter2 = ArrayAdapter.createFromResource(
                this, R.array.combo_profesor, android.R.layout.simple_spinner_item);
        comboprofesor.setAdapter(Adapter2);

        ArrayAdapter<CharSequence> Adapter3 = ArrayAdapter.createFromResource(
                this, R.array.combo_tema, android.R.layout.simple_spinner_item);
        combotema.setAdapter(Adapter3);*/
    }
}
