package com.example.vuelingame;

public class Ranking {

    private String NomUsuari;
    private long puntuacio;

    public Ranking() {
    }


    public Ranking(String nomUsuari, long puntuacio) {
        this.NomUsuari = nomUsuari;
        this.puntuacio = puntuacio;
    }

    public String getNomUsuari() {
        return NomUsuari;
    }

    public void setNomUsuari(String nomUsuari) {
        this.NomUsuari = nomUsuari;
    }

    public long getPuntuacio() {
        return puntuacio;
    }

    public void setPuntuacio(long puntuacio) {
        this.puntuacio = puntuacio;
    }
}
