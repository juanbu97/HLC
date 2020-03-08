package com.example.examen;

import java.util.ArrayList;
import java.util.Collections;

public class Game {

    ArrayList<Integer> arrayList = new ArrayList<Integer>();
    String estado = "1";


    public Game(int[] arrayId){



        for (int i =0; i < 32; i++){
            arrayList.add(arrayId[i]);
        }

        Collections.shuffle(arrayList);


    }

    public int posicion(int id){
        return  arrayList.indexOf(id);

    }

    public void cambiarEstado(){
        if(getEstado() == "1"){
            this.estado = "2";
        }else{
            this.estado = "1";
        }

    }

    public String getEstado(){
        return this.estado;
    }







}
