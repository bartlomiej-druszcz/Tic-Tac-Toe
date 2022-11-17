package org.example.player;

public class Statistic {

    private Integer numberOfRoundsWon;
    private Integer numberOfRoundsLost;
    private Integer numberOfRoundsTied;

    public Statistic() {
        this.numberOfRoundsWon = 0;
        this.numberOfRoundsLost = 0;
        this.numberOfRoundsTied = 0;
    }

    public Integer getNumberOfRoundsWon() {
        return numberOfRoundsWon;
    }

    public Integer getNumberOfRoundsLost() {
        return numberOfRoundsLost;
    }

    public Integer getNumberOfRoundsTied() {
        return numberOfRoundsTied;
    }

    public void setNumberOfRoundsWon(Integer numberOfRoundsWon) {
        this.numberOfRoundsWon = numberOfRoundsWon;
    }

    public void setNumberOfRoundsLost(Integer numberOfRoundsLost) {
        this.numberOfRoundsLost = numberOfRoundsLost;
    }

    public void setNumberOfRoundsTied(Integer numberOfRoundsTied) {
        this.numberOfRoundsTied = numberOfRoundsTied;
    }

    @Override
    public String toString() {
        return " Player Statistic: " +
                "Won= " + numberOfRoundsWon +
                ", Lost= " + numberOfRoundsLost +
                ", Tied= " + numberOfRoundsTied;
    }
}
