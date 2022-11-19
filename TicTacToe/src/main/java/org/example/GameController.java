package org.example;

import org.example.mvc.Board;
import org.example.mvc.BoardController;
import org.example.mvc.BoardView;
import org.example.player.PlayerDAO;
import org.example.player.PlayerRegister;
import org.example.player.PlayerUnregister;

import java.io.IOException;
import java.util.*;

import static org.example.DisplayMenu.*;
import static org.example.player.PlayerDAO.getLogin;
import static org.example.player.PlayerDAO.getPassword;

public class GameController {
    Game game = new Game();
    Board board = new Board();
    BoardView boardView = new BoardView();
    BoardController boardController = new BoardController(board, boardView);
    PlayerDAO playerDAO = new PlayerDAO();

    public void startMenu() throws IOException, InterruptedException, InputMismatchException {
        playerDAO.setListPlayerRegister(playerDAO.read());

        int numberMenu = correctInput();
        switch (numberMenu) {
            case 1 -> {
                clearConsole();
                System.out.println("=========== CREATE ACCOUNT ===========");
                playerDAO.create();
                printMenuStart();
                startMenu();
            }
            case 2 -> {
                clearConsole();
                System.out.println("============== SIGN IN ==============");
                String login = getLogin();
                String password = getPassword();
                for (Map.Entry<String, PlayerRegister> entry : playerDAO.getListPlayerRegister().entrySet()) {
                    if (entry.getKey().equals(login) && entry.getValue().getPassword().equals(password)) {
                        System.out.println("Log in successful");
                        printMenuPlayer();
                        playerMenu(entry.getValue());
                        break;
                    }
                }
                printMenuStart();
                System.out.println("Login or password is incorrect!\n");
                startMenu();
            }
            case 3 -> {
                PlayerUnregister playerunregister = new PlayerUnregister();
                game.playUnregisterWithComputer(boardController, chooseLevel(), playerunregister);
                pressEnter();
                printMenuStart();
                startMenu();
            }
            case 4 -> {
                clearConsole();
                System.out.println("=============== EXIT ===============");
                System.out.println("\nExit the program\n");
                System.exit(0);
            }
            default -> {
                printMenuStart();
                System.out.println("The value is invalid\n");
                startMenu();
            }
        }
    }

    public void playerMenu(PlayerRegister playerRegister) throws IOException, InterruptedException, InputMismatchException {
        int numberMenu = correctInput();
        switch (numberMenu) {
            case 1 -> {
                game.playRegisterWithComputer(boardController, chooseLevel(), playerRegister);
                playerDAO.update();
                pressEnter();
                printMenuPlayer();
                playerMenu(playerRegister);
            }
            case 2 -> {
                game.playRegisterWithUser(boardController, playerRegister);
                playerDAO.update();
                pressEnter();
                printMenuPlayer();
                playerMenu(playerRegister);
            }
            case 3 -> {
                clearConsole();
                System.out.println("============ STATISTICS ============");
                for (Map.Entry<String, PlayerRegister> entry : playerDAO.getListPlayerRegister().entrySet()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("  ");
                    stringBuilder.append(entry.getValue().getLogin());
                    for (int i = 0; i < 10 - entry.getValue().getLogin().length(); i++) {
                        stringBuilder.append(" ");
                    }
                    stringBuilder.append("->   Wins: ");
                    stringBuilder.append(entry.getValue().getStatistic().getNumberOfRoundsWon());
                    stringBuilder.append("         Ties: ");
                    stringBuilder.append(entry.getValue().getStatistic().getNumberOfRoundsTied());
                    stringBuilder.append("         Lost: ");
                    stringBuilder.append(entry.getValue().getStatistic().getNumberOfRoundsLost());
                    stringBuilder.append("         Points: ");
                    stringBuilder.append(entry.getValue().getStatistic().getNumberOfPoints());
                    System.out.println(stringBuilder);
                }
                Scanner scanner = new Scanner(System.in);
                System.out.println("\nq -> exit");
                String input = scanner.nextLine();
                while (!input.equals("q")) {
                    System.out.println("\nq -> exit");
                    input = scanner.nextLine();
                }
                printMenuPlayer();
                playerMenu(playerRegister);
            }
            case 4 -> {
                clearConsole();
                System.out.println("========== CHANGE PASSWORD ==========");
                String login = getLogin();
                String password = getPassword();
                for (Map.Entry<String, PlayerRegister> entry : playerDAO.getListPlayerRegister().entrySet()) {
                    if (entry.getKey().equals(login) && entry.getValue().getPassword().equals(password)) {
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("Enter new password => ");
                        String newPassword = scanner.nextLine();
                        entry.getValue().setPassword(newPassword);
                        printMenuPlayer();
                        playerDAO.update();
                        playerMenu(playerRegister);
                        break;
                    }
                }
            }
            case 5 -> {
                printMenuStart();
                startMenu();
            }
            default -> {
                printMenuPlayer();
                System.out.println("The value is invalid");
                playerMenu(playerRegister);
            }
        }

    }

    public Integer correctInput() {
        Integer input = 0;
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;
        while (!flag) {
            System.out.print("Select number -> ");
            try {
                while (!scanner.hasNextInt()) {
                    scanner.next();
                    System.out.print("Select number -> ");
                }
                input = scanner.nextInt();
                flag = true;
            } catch (InputMismatchException | IllegalArgumentException ex) {
                System.out.print(ex.getMessage() + " ");
            }
        }
        return input;
    }
}
