package org.example;

import java.io.IOException;

import static org.example.DisplayMenu.printMenuStart;

public class GameMain {
    public static void main(String args[]) throws IOException, InterruptedException {
        GameController gameController = new GameController();
        printMenuStart();
        gameController.startMenu();
    }
}
