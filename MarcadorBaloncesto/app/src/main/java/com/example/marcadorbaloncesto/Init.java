package com.example.marcadorbaloncesto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Init extends AppCompatActivity {
    Button botonInicio;
    EditText equipoLocal;
    EditText equipoVisitante;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        botonInicio = (Button) findViewById(R.id.botonInicio);
        equipoLocal = (EditText) findViewById(R.id.equipoLocal);
        equipoVisitante = (EditText)findViewById(R.id.equipoVisitante);
    }

    public void inicioPartido(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("equipoLocal", equipoLocal.getText().toString());
        intent.putExtra("equipoVisitante", equipoVisitante.getText().toString());
        startActivity(intent);
    }
}
