package com.example.conecta4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;


import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //array de id int idTablero[][]
    Game game;
    Musica musica;
    private final int idTablero[][] = {
            {R.id.f0c0boton,R.id.f0c1boton,R.id.f0c2boton,R.id.f0c3boton,R.id.f0c4boton,R.id.f0c5boton,R.id.f0c6boton},
            {R.id.f1c0boton,R.id.f1c1boton,R.id.f1c2boton,R.id.f1c3boton,R.id.f1c4boton,R.id.f1c5boton,R.id.f1c6boton},
            {R.id.f2c0boton,R.id.f2c1boton,R.id.f2c2boton,R.id.f2c3boton,R.id.f2c4boton,R.id.f2c5boton,R.id.f2c6boton},
            {R.id.f3c0boton,R.id.f3c1boton,R.id.f3c2boton,R.id.f3c3boton,R.id.f3c4boton,R.id.f3c5boton,R.id.f3c6boton},
            {R.id.f4c0boton,R.id.f4c1boton,R.id.f4c2boton,R.id.f4c3boton,R.id.f4c4boton,R.id.f4c5boton,R.id.f4c6boton},
            {R.id.f5c0boton,R.id.f5c1boton,R.id.f5c2boton,R.id.f5c3boton,R.id.f5c4boton,R.id.f5c5boton,R.id.f5c6boton}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musica.play(this, R.raw.musica);

        game = new Game(2);
    }

    protected  void onStop() {

        super.onStop();
        musica.stop(this);
    }

    protected void onRestart() {

        super.onRestart();
        musica.play(this, R.raw.musica);
    }

    protected  void onResume(){
        super.onResume();
        Boolean play = false;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(sharedPreferences.contains(Preferences.PLAY_MUSIC_KEY)){
            play = sharedPreferences.getBoolean(Preferences.PLAY_MUSIC_KEY, Preferences.PLAY_MUSIC_DEFAULT);

        }if(play == true){
            Musica.play(this, R.raw.musica);
        }
    }


    public void pulsado(View v){
        int id =v.getId();
        ImageButton imageButton = (ImageButton) v;

        Coordenada coordenada =new Coordenada(0,0);

        coordenada = coorJuego(id);
        if(game.empate()){
            game.setEstado('T');
        }

        switch (game.getEstado()){
            case 'J':
                if(!game.columnaCompleta(coordenada.getColumna())){
                    if(game.actualizarTablero(coordenada)) {
                        dibujarFicha(coordenada, game.getTurno());
                        if(game.jugadaGanadora(coordenada)>=0){
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setTitle(getString(R.string.finPartida)+game.getTurno());
                            builder.setMessage(R.string.nuevaPartida);
                            builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent restartIntent = getBaseContext().getPackageManager()
                                            .getLaunchIntentForPackage(getBaseContext().getPackageName());
                                    restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(restartIntent);

                                }
                            });
                            builder.setNegativeButton(R.string.salir, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    System.exit(1);
                                }
                            });

                            builder.show();
                        }else {
                            game.cambiarTurno();
                        }
                    }
                }else{
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    R.string.columnaCompleta, Toast.LENGTH_SHORT);

                    toast1.show();
                }
                break;
            case 'T':
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.tablas + game.getTurno());
                builder.setMessage(R.string.nuevaPartida);
                builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent restartIntent = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage(getBaseContext().getPackageName());
                        restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(restartIntent);

                    }
                });
                builder.setNegativeButton(R.string.salir, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(1);
                    }
                });

                builder.show();
                break;
            default:


        }





    }

    public Coordenada coorJuego(int id){
        int contador = 0;
        int fila = 0;
        int columna = 0;

        for (int i =0; i < Game.FILAS ; i++){
            for (int j =0; j < Game.COLUMNAS ; j++){
                if (idTablero[i][j] == id){
                   columna = j;
                   fila = game.selecionarFila(columna);
                }
            }
        }
        Coordenada coordenada = new Coordenada(fila,columna);
        return coordenada;
    }

    private void dibujarFicha(Coordenada coordenada, int jugador){
        int fila = coordenada.getFila();
        int columna = coordenada.getColumna();

        int id = idTablero[fila][columna];
        ImageButton imageButton = (ImageButton) findViewById(id);

        if (jugador == game.JUGADOR){
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                imageButton.setImageResource(R.drawable.c4_boton_verde);
            }else{
                imageButton.setImageResource(R.drawable.c4_boton_verde_land);
            }
        }

        if(jugador == game.MAQUINA){
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

                imageButton.setImageResource(R.drawable.c4_boton_rojo);
            }else{
                imageButton.setImageResource(R.drawable.c4_boton_rojo_land);

            }
        }
    }


    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        game.stringToTablero(savedInstanceState.getString("Tablero"));
        game.setEstado(savedInstanceState.getChar("Estado"));
        game.setTurno(savedInstanceState.getInt("Turno"));
        dibujarTablero();

    }

    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("Tablero", game.tableroToString());
        outState.putInt("Turno", game.getTurno());
        outState.putChar("Estado", game.getEstado());
    }

    public void dibujarTablero(){
        for (int i =0; i< game.FILAS ; i++){
            for (int j=0; j < game.COLUMNAS ; j++){
                Coordenada coordenada = new Coordenada(i,j);
                dibujarFicha(coordenada, game.tablero[i][j]);
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuAbout:
                startActivity(new Intent(this, About.class));
                return true;

            case R.id.sendMessage:
                    Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "CONECTA_4");
                    intent.putExtra(Intent.EXTRA_TEXT, "Nueva aplicación Android");
                    startActivity(intent);
                    return true;
            case R.id.preferences:
                startActivity(new Intent(this, Preferences.class));
                return true;
            default:
                return true;
        }


    }

}
