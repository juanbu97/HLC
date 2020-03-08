package com.example.marcadorbaloncesto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    int puntosLocal = 0;
    int puntosVisitante = 0;
    TextView marcadorLocal;
    TextView marcadorVisitante;
    TextView equipoLocal;
    TextView equipoVisitante;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        marcadorLocal = (TextView) findViewById(R.id.marcadorLocal);
        marcadorVisitante = (TextView) findViewById(R.id.marcadorVisitante);


        equipoLocal = (TextView) findViewById(R.id.local);
        equipoVisitante= (TextView) findViewById(R.id.visitante);

        asignarNombresEquipos();

        Button botonSuma1Local = (Button) findViewById(R.id.botonMas1Local);
        Button botonSuma2Local = (Button) findViewById(R.id.botonMas2Local);
        Button botonSuma3Local = (Button) findViewById(R.id.botonMas3Local);

        Button botonSuma1Visitante = (Button) findViewById(R.id.botonMas1Visitante);
        Button botonSuma2Visitante = (Button) findViewById(R.id.botonMas2Visitante);
        Button botonSuma3Visitante = (Button) findViewById(R.id.botonMas3Visitante);

        Button botonIncrementaLocal = (Button) findViewById(R.id.botonIncrementoLocal);
        Button botonDecrementaLocal = (Button) findViewById(R.id.botonDecrementoLocal);

        Button botonIncrementaVisitante = (Button) findViewById(R.id.botonIncrementoVisitante);
        Button botonDecrementaVisitante = (Button) findViewById(R.id.botonDecrementoVisitante);

        final Button botonReiniciar = (Button) findViewById(R.id.botonReset);


        botonSuma1Local.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
               puntosLocal = Integer.parseInt(marcadorLocal.getText().toString());
               puntosLocal ++;
               Integer.toString(puntosLocal);
               marcadorLocal.setText(String.format("%02d",puntosLocal));
            }
        });

        botonSuma2Local.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                puntosLocal = Integer.parseInt(marcadorLocal.getText().toString());
                puntosLocal +=2;
                Integer.toString(puntosLocal);
                marcadorLocal.setText(String.format("%02d",puntosLocal));
            }
        });

        botonSuma3Local.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                puntosLocal = Integer.parseInt(marcadorLocal.getText().toString());
                puntosLocal +=3;
                Integer.toString(puntosLocal);
                marcadorLocal.setText(String.format("%02d",puntosLocal));
            }
        });

        botonSuma1Visitante.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                puntosVisitante = Integer.parseInt(marcadorVisitante.getText().toString());
                puntosVisitante ++;
                Integer.toString(puntosVisitante);
                marcadorVisitante.setText(String.format("%02d",puntosVisitante));
            }
        });

        botonSuma2Visitante.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                puntosVisitante = Integer.parseInt(marcadorVisitante.getText().toString());
                puntosVisitante +=2;
                Integer.toString(puntosVisitante);
                marcadorVisitante.setText(String.format("%02d",puntosVisitante));
            }
        });

        botonSuma3Visitante.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                puntosVisitante = Integer.parseInt(marcadorVisitante.getText().toString());
                puntosVisitante +=3;
                Integer.toString(puntosVisitante);
                marcadorVisitante.setText(String.format("%02d",puntosVisitante));
            }
        });

        botonIncrementaLocal.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                puntosLocal = Integer.parseInt(marcadorLocal.getText().toString());
                puntosLocal ++;
                Integer.toString(puntosLocal);
                marcadorLocal.setText(String.format("%02d",puntosLocal));
            }
        });

        botonDecrementaLocal.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                puntosLocal = Integer.parseInt(marcadorLocal.getText().toString());
                if(puntosLocal>0)
                    puntosLocal --;
                Integer.toString(puntosLocal);
                marcadorLocal.setText(String.format("%02d",puntosLocal));
            }
        });

        botonIncrementaVisitante.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                puntosVisitante = Integer.parseInt(marcadorVisitante.getText().toString());
                puntosVisitante ++;
                Integer.toString(puntosVisitante);
                marcadorVisitante.setText(String.format("%02d",puntosVisitante));
            }
        });

        botonDecrementaVisitante.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                puntosVisitante = Integer.parseInt(marcadorVisitante.getText().toString());
                if(puntosVisitante>0)
                    puntosVisitante --;
                Integer.toString(puntosVisitante);
                marcadorVisitante.setText(String.format("%02d",puntosVisitante));
            }
        });

        final Context context = this;

        botonReiniciar.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v) {


                AlertDialog.Builder alertdialog = new AlertDialog.Builder(context);
                alertdialog.setTitle("Reiniciar marcador");
                alertdialog.setMessage("¿Deseas reiniciar ambos marcadores?")
                        .setCancelable(false)// Aqui defines que no se pueda dar atras (cancelar el dialog) el usuario debera seleccionar una opcion
                        .setPositiveButton(" Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // agrega aqui la funcion que quieres hacer con el boton positivo
                                puntosVisitante = Integer.parseInt(marcadorVisitante.getText().toString());
                                puntosLocal = Integer.parseInt(marcadorLocal.getText().toString());


                                puntosVisitante = 0;
                                puntosLocal = 0;

                                Integer.toString(puntosVisitante);
                                marcadorVisitante.setText(String.format("%02d", puntosVisitante));

                                Integer.toString(puntosLocal);
                                marcadorLocal.setText(String.format("%02d", puntosLocal));
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // agrega aqui la funcion que quieres hacer con el boton negativo
                            }
                        });
                AlertDialog mensaje = alertdialog.create();
                mensaje.show();






            }
        });




    }

    private void asignarNombresEquipos() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            equipoLocal.setText(bundle.getString("equipoLocal"));
            equipoVisitante.setText(bundle.getString("equipoVisitante"));
        }
    }

}



