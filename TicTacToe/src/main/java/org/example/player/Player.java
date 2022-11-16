package org.example.player;

public abstract class Player {
    private String figure;
    public Player() {
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }



    @Override
    public String toString() {
        return "Player{" +
                "figure=" + figure +
                '}';
    }
}
