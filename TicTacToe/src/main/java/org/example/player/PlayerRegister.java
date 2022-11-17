package org.example.player;

import java.util.Scanner;

public class PlayerRegister extends Player {
    private String login;
    private String password;
    private Statistic statistic;

    public PlayerRegister() {
        System.out.print(" Enter your login  => ");
        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();

        System.out.print(" Enter your password  => ");
        String password = scanner.nextLine();

        System.out.print(" Choose your sign X or O  => ");
        String figure = scanner.nextLine();

        while (!figure.equals("X") && !figure.equals("O")) {
            System.out.print(" Wrong choice, try again X or O  => ");
            figure = scanner.nextLine();
        }

        this.setFigure(figure);
        this.login = login;
        this.password = password;
        this.statistic = new Statistic();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }

    @Override
    public String toString() {
        return "PlayerRegister{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", statistic=" + statistic +
                '}';
    }
}
