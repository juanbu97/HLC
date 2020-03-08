package com.example.sieteymedia;

import java.util.ArrayList;
import java.util.Collections;

public class Game{

    float puntuacion;
    float puntuacionMaquina;

    ArrayList<Integer> arrayBaraja = new ArrayList<Integer>();


    public Game(float puntuacion, float puntuacionMaquina){
        this.puntuacion = puntuacion;
        this.puntuacionMaquina = puntuacionMaquina;
        crearBaraja();
    }

    public  void crearBaraja(){

            for(int i = 1; i<=40; i++){
                arrayBaraja.add(i);
            }



        Collections.shuffle(arrayBaraja);
    }

    public  int sacarCarta(){

        return arrayBaraja.remove(0);

    }






}
