package org.example.player;

import java.io.IOException;
import java.util.*;

public class PrintMenu {

    static PlayerDAO playerRegisterDAO = new PlayerDAO();

    public static void starMenu() throws IOException, InterruptedException, InputMismatchException {
        System.out.println("===========MENU============");
        System.out.println("1. Register ");
        System.out.println("2. Sign in ");
        System.out.println("3. Play now");
        System.out.println("4. Exit");
        System.out.println("===========================");
        System.out.print("Select number ->");

        Scanner scanner = new Scanner(System.in);
        int numberMenu = scanner.nextInt();
        switch (numberMenu) {
            case 1 -> {
                playerRegisterDAO.create();
                starMenu();
            }
            case 2 -> { //Log in
                HashMap<String, String> lista = playerRegisterDAO.getAllLogin();
                String login = PlayerDAO.getLogin();
                String password = PlayerDAO.getPassword();
                for (Map.Entry<String, String> entry : lista.entrySet()) {
                    if (entry.getKey().equals(login) && entry.getValue().equals(password)) {
                        System.out.println("Log in successful");
                        playerMenu();
                        break;
                    }
                }
                System.out.println("Login or password is incorrect");
                starMenu();
            }
            case 3 -> {
                PlayerUnregister playerunregister = new PlayerUnregister();
                System.out.println("start gry");
            }

            case 4 -> System.out.println("Exit the program");
            default -> {
                System.out.println("The value is invalid");
                starMenu();
            }
        }

        System.out.println();
    }

    public static void playerMenu() throws IOException, InterruptedException, InputMismatchException {
        System.out.println("=======PLAYER MENU============");
        System.out.println("1. Play with computer ");
        System.out.println("2. Play with user ");
        System.out.println("3. Show Statistic");
        System.out.println("4. Change Password");
        System.out.println("5. Exit");
        System.out.println("==============================");
        System.out.print("Select number ->");
        Scanner scanner = new Scanner(System.in);
        int numberMenu = scanner.nextInt();

        switch (numberMenu) {
            case 1 -> System.out.println("gra z komputerem");
            case 2 -> System.out.println("gra z użytkownikiem");
            case 3 -> {
                playerRegisterDAO.readAllPlayer();
                playerMenu();
            }
            case 4 -> {
                System.out.println("Change your password");
                playerRegisterDAO.update();
                playerMenu();
            }
            case 5 -> starMenu();
            default -> {
                System.out.println("The value is invalid");
                starMenu();
            }
        }

    }
}
