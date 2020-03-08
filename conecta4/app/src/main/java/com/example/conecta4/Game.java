package com.example.conecta4;

public class Game {

    static final int COLUMNAS = 7;
    static final int FILAS = 6;


    static final int VACIO = 0;
    static final int MAQUINA = 1;
    static final int JUGADOR = 2;

    static final String JUGADOR1GANA = "1111";
    static final String JUGADOR2GANA = "2222";

    private int turno;
    private char estado ; //J jugando, G ganado, T tablas

    public int[][] tablero = new int[6][7];



    /**
     * Constructor
     */
    public Game(int jugador){

        tablero = new int [FILAS][COLUMNAS];

        for (int i =0; i < FILAS; i++){
            for (int j = 0; j< COLUMNAS; j++){
                tablero[i][j] = VACIO;
            }
        }
        this.estado = 'J';
        setTurno(jugador);
    }

    public boolean actualizarTablero(Coordenada coordenada){
        boolean lActualiza = false;
        int fila = coordenada.getFila();
        int columna = coordenada.getColumna();

        if(tablero[fila][columna]==VACIO) {
            tablero[fila][columna] = getTurno();
            lActualiza = true;
        }
        return lActualiza;
    }

    public void cambiarTurno(){
        setTurno(getTurno() == MAQUINA ? JUGADOR : MAQUINA);
    }

    public int getTurno() {
        return this.turno;
    }

    public void setTurno(int jugador) {
        this.turno = jugador;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public int selecionarFila(int columna){
        int i = FILAS -1;
        int fila = -1;
        boolean filaSeleccionada = false;
        while (i >= 0 && !filaSeleccionada){
            if(tablero[i][columna] == VACIO){
                fila = i;
                filaSeleccionada = true;

            }
            i--;
        }
        return fila;
    }

    public boolean columnaCompleta(int columna){
        boolean estaCompleta = false;
        if (tablero[0][columna] != VACIO) {
            estaCompleta = true;
        }
        return estaCompleta;
    }

    /**
     * Método que recoge la horizontal
     * @param coordenada
     * @return
     */
    public String horizontal(Coordenada coordenada){
        String cadena = "";
        int fila = coordenada.getFila();
        int contador;
        for(contador = 0 ; contador < COLUMNAS; contador++){
            cadena += Integer.toString(tablero[fila][contador]);
        }

        return cadena;
    }

    /**
     * Método que recoge la vertical
     * @param coordenada
     * @return
     */
    public String vertical (Coordenada coordenada){
        String cadena = "";
        int columna = coordenada.getColumna();
        int fila;
        for(fila = 0; fila < FILAS;fila++){
            cadena += Integer.toString(tablero[fila][columna]);
        }

        return cadena;
    }

    /**
     * Método que recoge la diagonal izquierda
     * @param coordenada
     * @return
     */
    public String diagonal1 (Coordenada coordenada){
        String cadena="";
        int columna = coordenada.getColumna();
        int fila = coordenada.getFila();
        int i = fila - Math.min(fila,columna);
        int j = columna - Math.min(fila,columna);
        while(i<FILAS && j<COLUMNAS){
            cadena += Integer.toString(tablero[i][j]);
            i++;
            j++;
        }
        return cadena;
    }

    /**
     * Método que recoge la diagonal derecha
     * @param coordenada
     * @return
     */
    public String diagonal2(Coordenada coordenada){
        String cadena = "";
        int columna = 0;
        int fila = 0;
        for (fila =0;fila< FILAS;fila++){
            for(columna = 0; columna < COLUMNAS; columna++){
                if(columna + fila == coordenada.getFila() + coordenada.getColumna()){
                    cadena+= Integer.toString(tablero[fila][columna]);
                }
            }
        }
        return cadena;
    }

    public int jugadaGanadora(Coordenada coordenada){
        int resultado = -1;
        if(this.estado == 'G') return -1;
        String patron = this.getTurno() == MAQUINA ? JUGADOR1GANA : JUGADOR2GANA;

        if (horizontal(coordenada).contains(patron)){
            this.estado = 'G';
            resultado = 0;
        }
        if(vertical(coordenada).contains(patron)){
            this.estado = 'G';
            resultado = 1;
        }
        if(diagonal1(coordenada).contains(patron)){
            this.estado = 'G';
            resultado = 2;
        }
        if(diagonal2(coordenada).contains(patron)){
            this.estado = 'G';
            resultado = 3;
        }
        return resultado;
    }

    public boolean empate(){
        boolean tablas = true;
        for(int i =0; i<FILAS;i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (tablero[i][j] == 0) {
                    tablas = false;

                }
            }
        }
        return tablas;
    }


    public String tableroToString(){
        String str = "";
        for (int i = 0; i<FILAS; i++){
            for (int j = 0; j<COLUMNAS; j++)
                str += tablero[i][j];
        }
        return str;
    }
    public void stringToTablero(String str){
        int contador = 0;
        for(int i = 0; i<FILAS;i++){
            for (int j = 0; j < COLUMNAS; j++) {
                tablero[i][j] = Integer.parseInt(String.valueOf(str.charAt(contador)));
                contador++;
            }
        }
    }
}
