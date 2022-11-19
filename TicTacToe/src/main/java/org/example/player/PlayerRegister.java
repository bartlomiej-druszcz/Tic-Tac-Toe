package org.example.player;

public class PlayerRegister extends Player {
    private String login;
    private String password;
    private Statistic statistic;

    public PlayerRegister(String login, String password, String figure, Statistic statistic) {
        super.setFigure(figure);
        this.login = login;
        this.password = password;
        this.statistic = statistic;
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
}
