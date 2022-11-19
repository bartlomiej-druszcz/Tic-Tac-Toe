package org.example.player;

public class Statistic {
    private Integer numberOfRoundsWon;
    private Integer numberOfRoundsLost;
    private Integer numberOfRoundsTied;
    private Integer numberOfPoints;

    public Statistic() {
        this.numberOfRoundsWon = 0;
        this.numberOfRoundsLost = 0;
        this.numberOfRoundsTied = 0;
        this.numberOfPoints = 0;
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

    public void incrementNumberOfRoundsWon() {
        this.numberOfRoundsWon++;
    }

    public void incrementNumberOfRoundsLost() {
        this.numberOfRoundsLost++;
    }

    public void incrementNumberOfRoundsTied() {
        this.numberOfRoundsTied++;
    }

    public Integer getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(Integer level, Boolean win) {
        if (win) {
            this.numberOfPoints += level * 3;
        } else {
            this.numberOfPoints += level;
        }
    }
}
