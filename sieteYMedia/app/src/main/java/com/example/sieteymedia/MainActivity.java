package com.example.sieteymedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    ImageView imagenCarta;
    Game game ;
    Button botonSacarCarta;
    TextView puntuacion;
    TextView puntuacionMaquina;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagenCarta = (ImageView) findViewById(R.id.imagenCarta);
        botonSacarCarta = (Button) findViewById(R.id.sacarCarta);
        puntuacion = (TextView) findViewById(R.id.puntuacion);
        puntuacionMaquina = (TextView) findViewById(R.id.puntuacionMaquina);
        game = new Game(0,0);


    }

    public void sacarCarta(View v){
        int carta = game.sacarCarta();
        int cartaMaquina = game.sacarCarta();

        int identificadorCarta = getResources().getIdentifier("com.example.sieteymedia:drawable/c"+carta, null, null);

        imagenCarta.setImageResource(identificadorCarta);

        if((carta%10>=8) || (carta%10 == 0)){
            game.puntuacion += 0.5;
        }else{
            game.puntuacion += (carta%10);
        }

        if((cartaMaquina%10>=8) || (cartaMaquina%10 == 0)){
            game.puntuacionMaquina += 0.5;
        }else{
            game.puntuacionMaquina += (cartaMaquina%10);
        }

        puntuacion.setText("Puntuacion: "+Float.toString(game.puntuacion));

        if(game.puntuacion > 7.5){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Has perdido máquina", Toast.LENGTH_SHORT);

            toast1.show();
        }
    }

    public void reiniciar(View v){
        game.crearBaraja();
        game.puntuacion = 0;
        game.puntuacionMaquina = 0;
        puntuacion.setText("Puntuacion: "+Float.toString(game.puntuacion));
        puntuacionMaquina.setText("");
    }

    public void plantarse(View v){
        puntuacion.setText("Puntuacion: "+Float.toString(game.puntuacion));
        puntuacionMaquina.setText("Puntuacion Maquina: "+Float.toString(game.puntuacionMaquina));

        if(game.puntuacion <= 7.5 && game.puntuacion > game.puntuacionMaquina){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Ha ganado el jugador", Toast.LENGTH_SHORT);

            toast1.show();
        }else if(game.puntuacion == game.puntuacionMaquina){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Empate", Toast.LENGTH_SHORT);

            toast1.show();
        }else{
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "La maquina ha ganado", Toast.LENGTH_SHORT);

            toast1.show();
        }

    }





}
