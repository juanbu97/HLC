package com.example.examen2;

public class Game {

    static final int COLUMNAS = 3;
    static final int FILAS = 4;


    static final int VACIO = 0;
    static final int LLENO = 1;


    private String estado = "1"; //1 2

    public int[][] tablero = new int[4][3];

    public Game(){
        tablero = new int [FILAS][COLUMNAS];

        for (int i =0; i < FILAS; i++){
            for (int j = 0; j< COLUMNAS; j++){
                tablero[i][j] = LLENO;
            }
        }

        tablero[0][2] = VACIO;
        tablero[2][2] = VACIO;
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
