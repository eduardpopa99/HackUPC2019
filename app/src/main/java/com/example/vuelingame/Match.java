package com.example.vuelingame;

public class Match {

    public String score1;
    public String score2;
    public String isOpen;

    public Match(){}

    public Match(String score1, String score2, String isOpen) {
        this.score1 = score1;
        this.score2 = score2;
        this.isOpen = isOpen;
    }

    public String getScore1() {
        return score1;
    }

    public String getScore2() {
        return score2;
    }

    public String getIsOpen() {
        return isOpen;
    }
}
