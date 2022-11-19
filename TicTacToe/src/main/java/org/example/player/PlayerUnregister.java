package org.example.player;

import java.util.Random;
import java.util.Scanner;

public class PlayerUnregister extends Player {
    private final String nick;

    public PlayerUnregister() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(" Choose your sign X or O  => ");
        String figure = scanner.nextLine();
        while (!figure.equals("X") && !figure.equals("O")) {
            System.out.print(" Wrong choice, try again X or O  => ");
            figure = scanner.nextLine();
        }
        this.setFigure(figure);

        int length = 3;
        String alphaNumeric = "0123456789";
        StringBuilder nick = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphaNumeric.length());
            char randomChar = alphaNumeric.charAt(index);
            nick.append(randomChar);
        }
        this.nick = "Player" + nick;
    }

    public String getNick() {
        return nick;
    }
}
