package com.example.examen2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Game game;
    int id1 = 0;
    int id2 = 1;
    int id3 = 2;
    ImageView imagen;
    ImageView imagen2;
    ImageView imagen3;
    Coordenada coordenada1 = new Coordenada(0, 0);
    Coordenada coordenada2 = new Coordenada(0, 0);


    private final int idTablero[][] = {
            {R.id.f0c0boton, R.id.f0c1boton, R.id.f0c2boton},
            {R.id.f1c0boton, R.id.f1c1boton, R.id.f1c2boton},
            {R.id.f2c0boton, R.id.f2c1boton, R.id.f2c2boton},
            {R.id.f3c0boton, R.id.f3c1boton, R.id.f3c2boton}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();
    }


    public void pulsar(View v) {
        int id = v.getId();

        if (game.getEstado() == "1") {
            id1 = id;
            imagen = (ImageView) findViewById(id);
            coordenada1 = coorJuego(id);


        } else {
            id2 = id;
            coordenada2 = coorJuego(id);
            imagen2 = (ImageView) findViewById(id);

        }


        // String columna = String.valueOf(coordenada.getColumna());
        //String fila = String.valueOf(coordenada.getFila());
        int valor = game.tablero[coordenada1.getFila()][coordenada1.getColumna()];
        String valore = String.valueOf(game.tablero[coordenada1.getFila()][coordenada1.getColumna()]);
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "hola", Toast.LENGTH_SHORT);

        Toast toast2 =
                Toast.makeText(getApplicationContext(),
                        valore, Toast.LENGTH_SHORT);
        if (valor == 0) {
            return;
        } else {
            imagen.setImageResource(R.drawable.boton_seleccionado);

            if (game.getEstado() == "2") {
                if (coordenada2.getColumna() == coordenada1.getColumna() + 1 || coordenada2.getColumna() + 1 == coordenada1.getColumna() || coordenada2.getFila() == coordenada1.getFila() - 1 || coordenada2.getFila() - 1 == coordenada1.getFila()) {

                    game.cambiarEstado();

                    return;
                }
                if (game.tablero[coordenada2.getFila()][coordenada2.getColumna()] == 0) {

                    if (coordenada1.getColumna() == coordenada2.getColumna()) {

                        if (coordenada1.getColumna() > 1 && coordenada1.getFila() <= 1 && idTablero[coordenada1.getFila() + 2][coordenada1.getColumna()] == idTablero[coordenada2.getFila()][coordenada2.getColumna()]) {
                            //toast1.show();
                            imagen.setImageResource(0);
                            imagen2.setImageResource(R.drawable.boton);
                            id3 = idTablero[coordenada2.getFila() - 1][coordenada2.getColumna()];
                            imagen3 = (ImageView) findViewById(id3);
                            imagen3.setImageResource(0);
                            game.tablero[coordenada2.getFila() - 1][coordenada2.getColumna()] = 0;
                            game.tablero[coordenada1.getFila()][coordenada1.getColumna()] = 0;
                            game.tablero[coordenada2.getFila()][coordenada2.getColumna()] = 1;
                            game.cambiarEstado();
                            return;

                        }
                if(coordenada1.getColumna()>1 && idTablero[coordenada1.getFila()-2][coordenada1.getColumna()] == idTablero[coordenada2.getFila()][coordenada2.getColumna()]){
                    imagen.setImageResource(0);
                    imagen2.setImageResource(R.drawable.boton);
                    id3 = idTablero[coordenada2.getFila()+1][coordenada2.getColumna()];
                    imagen3 = (ImageView)findViewById(id3);
                    imagen3.setImageResource(0);
                    game.tablero[coordenada2.getFila()+1][coordenada2.getColumna()] = 0;
                    game.tablero[coordenada1.getFila()][coordenada1.getColumna()] = 0;
                    game.tablero[coordenada2.getFila()][coordenada2.getColumna()] = 1;
                    game.cambiarEstado();
                    return;
                }

                    }
                    if (coordenada1.getFila() == coordenada2.getFila()) {

                        if (idTablero[coordenada1.getFila()][coordenada1.getColumna() + 2] == idTablero[coordenada2.getFila()][coordenada2.getColumna()]) {
                            imagen.setImageResource(0);
                            imagen2.setImageResource(R.drawable.boton);
                            id3 = idTablero[coordenada2.getFila()][coordenada2.getColumna() - 1];
                            imagen3 = (ImageView) findViewById(id3);
                            imagen3.setImageResource(0);
                            game.tablero[coordenada2.getFila()][coordenada2.getColumna() - 1] = 0;
                            game.tablero[coordenada1.getFila()][coordenada1.getColumna()] = 0;
                            game.tablero[coordenada2.getFila()][coordenada2.getColumna()] = 1;
                        }
                    }


                }


            }

        }


        //toast2.show();


        game.cambiarEstado();
        if(ganado(game)){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Has ganado la partida.");
            builder1.setPositiveButton("Jugar otra", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent restartIntent = getBaseContext().getPackageManager()
                            .getLaunchIntentForPackage(getBaseContext().getPackageName());
                    restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(restartIntent);

                }
            });
            builder1.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(1);
                }
            });


            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

    }


    public Coordenada coorJuego(int id) {
        int fila = 0;
        int columna = 0;

        for (int i = 0; i < Game.FILAS; i++) {
            for (int j = 0; j < Game.COLUMNAS; j++) {
                if (idTablero[i][j] == id) {
                    columna = j;
                    fila = i;
                }
            }
        }
        Coordenada coordenada = new Coordenada(fila, columna);
        return coordenada;
    }

    public boolean ganado(Game game){
        int fichas = 0;
        boolean ganado = false;
        for (int i =0; i < 4; i++){
            for (int j = 0; j< 3; j++){
                if(game.tablero[i][j] == 1){
                    fichas++;
                }
            }
        }
        if(fichas == 1){
            ganado = true;
        }


        return ganado;
    }

    public void reiniciar(View v){
        Intent restartIntent = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(restartIntent);
    }
}
