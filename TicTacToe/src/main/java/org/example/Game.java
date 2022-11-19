package org.example;

import org.example.computer.Computer;
import org.example.mvc.BoardController;
import org.example.player.Player;
import org.example.player.PlayerRegister;
import org.example.player.PlayerUnregister;

import java.util.List;

public class Game {
    public void playUnregisterWithComputer(BoardController boardController, String level, PlayerUnregister player) {
        Character[][] result = playWithComputer(boardController, level, player);
        List<Integer[]> list = boardController.checkWin(result);
        if (list.size() == 0 && !boardController.isEmptyField(result)) {
            System.out.println(player.getNick() + " made a draw.");
        } else if (list.size() != 0 &&
                result[list.get(0)[0]][list.get(0)[1]].equals(player.getFigure().charAt(0))) {
            System.out.println(player.getNick() + " won.");
        } else if (list.size() != 0 &&
                result[list.get(0)[0]][list.get(0)[1]].equals(player.getFigure().charAt(0) == 'O' ? 'X' : 'O')) {
            System.out.println(player.getNick() + " lost.");
        }
    }

    public void playRegisterWithComputer(BoardController boardController, String level, PlayerRegister player) {
        Character[][] result = playWithComputer(boardController, level, player);
        List<Integer[]> list = boardController.checkWin(result);
        if (list.size() == 0 && !boardController.isEmptyField(result)) {
            player.getStatistic().incrementNumberOfRoundsTied();
            player.getStatistic().setNumberOfPoints(Integer.parseInt(level), false);
        } else if (list.size() != 0 &&
                result[list.get(0)[0]][list.get(0)[1]].equals(player.getFigure().charAt(0))) {
            player.getStatistic().incrementNumberOfRoundsWon();
            player.getStatistic().setNumberOfPoints(Integer.parseInt(level), true);
        } else if (list.size() != 0 &&
                result[list.get(0)[0]][list.get(0)[1]].equals(player.getFigure().charAt(0) == 'O' ? 'X' : 'O')) {
            player.getStatistic().incrementNumberOfRoundsLost();
        }
    }

    public void playRegisterWithUser(BoardController boardController, PlayerRegister player) {
        boardController.initBoard();
        boardController.updateBoardView();

        while (boardController.checkWin(boardController.getStateBoard()).size() == 0 &&
                boardController.isEmptyField(boardController.getStateBoard())) {
            System.out.println("Your move: ");
            Integer[] inputPosition = boardController.correctInputPosition();
            while (!boardController.isMoveCorrect(boardController.getStateBoard(), inputPosition)) {
                System.out.print("\nEnabled position! ");
                inputPosition = boardController.correctInputPosition();
            }
            boardController.setBoardField(inputPosition, player.getFigure().charAt(0));
            boardController.updateBoardView();

            if (boardController.checkWin(boardController.getStateBoard()).size() == 0 &&
                    boardController.isEmptyField(boardController.getStateBoard())) {
                System.out.println("Opponent's move: ");
                Integer[] opponentPosition = boardController.correctInputPosition();
                boardController.setBoardField(opponentPosition, player.getFigure().charAt(0) == 'O' ? 'X' : 'O');
                boardController.updateBoardView();
            }
        }

        List<Integer[]> list = boardController.checkWin(boardController.getStateBoard());
        if (list.size() == 0 && !boardController.isEmptyField(boardController.getStateBoard())) {
            player.getStatistic().incrementNumberOfRoundsTied();
            player.getStatistic().setNumberOfPoints(1, false);
        } else if (list.size() != 0 &&
                boardController.getStateBoard()[list.get(0)[0]][list.get(0)[1]].equals(player.getFigure().charAt(0))) {
            player.getStatistic().incrementNumberOfRoundsWon();
            player.getStatistic().setNumberOfPoints(1, true);
        } else if (list.size() != 0 &&
                boardController.getStateBoard()[list.get(0)[0]][list.get(0)[1]].equals(player.getFigure().charAt(0) == 'O' ? 'X' : 'O')) {
            player.getStatistic().incrementNumberOfRoundsLost();
        }
    }

    public Character[][] playWithComputer(BoardController boardController, String level, Player player) {
        Computer computer = new Computer(player.getFigure().charAt(0) == 'O' ? 'X' : 'O');
        boardController.initBoard();
        boardController.updateBoardView();

        while (boardController.checkWin(boardController.getStateBoard()).size() == 0 &&
                boardController.isEmptyField(boardController.getStateBoard())) {
            Integer[] inputPosition = boardController.correctInputPosition();
            while (!boardController.isMoveCorrect(boardController.getStateBoard(), inputPosition)) {
                System.out.print("\nEnabled position! ");
                inputPosition = boardController.correctInputPosition();
            }
            boardController.setBoardField(inputPosition, player.getFigure().charAt(0));
            boardController.updateBoardView();

            if (boardController.checkWin(boardController.getStateBoard()).size() == 0 &&
                    boardController.isEmptyField(boardController.getStateBoard())) {
                Integer[] computerPosition = computer.simulateComputerGame(level, boardController.getStateBoard());
                boardController.setBoardField(computerPosition, computer.getSymbol());
                boardController.updateBoardView();
            }
        }
        return boardController.getStateBoard();
    }
}
