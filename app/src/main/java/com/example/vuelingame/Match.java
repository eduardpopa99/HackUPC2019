package com.example.vuelingame;

public class Match {
    private String jugador1;
    private String jugador2;
    private long puntuacio1;
    private long puntuacio2;

    public Match(){}

    public Match(String jugador1, String jugador2, long puntuacio1, long puntuacio2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.puntuacio1 = puntuacio1;
        this.puntuacio2 = puntuacio2;
    }

    public void setJugador1(String jugador1) {
        this.jugador1 = jugador1;
    }

    public void setJugador2(String jugador2) {
        this.jugador2 = jugador2;
    }

    public void setPuntuacio1(long puntuacio1) {
        this.puntuacio1 = puntuacio1;
    }

    public void setPuntuacio2(long puntuacio2) {
        this.puntuacio2 = puntuacio2;
    }

    public String getJugador1() {
        return jugador1;
    }

    public String getJugador2() {
        return jugador2;
    }

    public long getPuntuacio1() {
        return puntuacio1;
    }

    public long getPuntuacio2() {
        return puntuacio2;
    }
}
