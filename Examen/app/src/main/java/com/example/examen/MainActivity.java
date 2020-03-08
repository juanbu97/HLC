package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Game game;
    int id1=0;
    int id2=1;
    ImageView imagen2;
    ImageView imagen;


    private final int idTablero[] =
            {R.id.ficha1,R.id.ficha2,R.id.ficha3,R.id.ficha4,
            R.id.ficha5,R.id.ficha6,R.id.ficha7,R.id.ficha8,
            R.id.ficha9,R.id.ficha10,R.id.ficha11,R.id.ficha12,
            R.id.ficha13,R.id.ficha14,R.id.ficha15,R.id.ficha16,
            R.id.ficha17,R.id.ficha18,R.id.ficha19,R.id.ficha20,
            R.id.ficha21,R.id.ficha22,R.id.ficha23,R.id.ficha24,
            R.id.ficha25,R.id.ficha26,R.id.ficha27,R.id.ficha28,
            R.id.ficha29,R.id.ficha30,R.id.ficha31,R.id.ficha32};

    private final int idFichas[] = {
            R.drawable.ficha1,R.drawable.ficha2,R.drawable.ficha3,R.drawable.ficha4,
            R.drawable.ficha5,R.drawable.ficha6,R.drawable.ficha7,R.drawable.ficha8,
            R.drawable.ficha9,R.drawable.ficha10,R.drawable.ficha11,R.drawable.ficha12,
            R.drawable.ficha13,R.drawable.ficha14,R.drawable.ficha15,R.drawable.ficha16,
            R.drawable.ficha1,R.drawable.ficha2,R.drawable.ficha3,R.drawable.ficha4,
            R.drawable.ficha5,R.drawable.ficha6,R.drawable.ficha7,R.drawable.ficha8,
            R.drawable.ficha9,R.drawable.ficha10,R.drawable.ficha11,R.drawable.ficha12,
            R.drawable.ficha13,R.drawable.ficha14,R.drawable.ficha15,R.drawable.ficha16};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game(idTablero);



    }

    public void destapar(View v){
        Handler handler = new Handler();

        int id = v.getId();


        if(game.getEstado() == "1"){
            id1 = idFichas[game.posicion(id)];
            imagen2 = (ImageView) findViewById(id);



        }

        if(game.getEstado() == "2"){
            id2 = idFichas[game.posicion(id)];

        }

        imagen = (ImageView) findViewById(id);



        imagen.setImageResource(idFichas[game.posicion(id)]);

        if(game.getEstado() == "2"){
            if(id1 == id2){
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                " premio", Toast.LENGTH_SHORT);

                toast1.show();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imagen.setImageResource(0);
                        imagen2.setImageResource(0);
                    }
                },1000);

            }else{
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                " Has fallado", Toast.LENGTH_SHORT);

                toast1.show();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imagen.setImageResource(R.drawable.fondo);
                        imagen2.setImageResource(R.drawable.fondo);
                    }
                },1000);

            }
        }



        game.cambiarEstado();








    }







}
