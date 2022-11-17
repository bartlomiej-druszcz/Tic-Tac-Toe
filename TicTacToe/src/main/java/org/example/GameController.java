package org.example;

import org.example.computer.Computer;
import org.example.mvc.Board;
import org.example.mvc.BoardController;
import org.example.mvc.BoardView;
import org.example.player.Player;
import org.example.player.PlayerUnregister;

import java.util.Scanner;

public class GameController {

    public static void main(String args[]) {
        Board board = new Board();
        BoardView boardView = new BoardView();
        BoardController boardController = new BoardController(board, boardView);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter level: ");
        playWithComputer(boardController, scanner.nextLine());

    }

    public static void playWithComputer(BoardController boardController, String level) {
        Computer computer = new Computer('O');
        boardController.initBoard();
        boardController.updateBoardView();

        while (boardController.checkWin(boardController.getStateBoard()).size() == 0) {
            Integer[] inputPosition = boardController.correctInputPosition();
            while (!boardController.isMoveCorrect(boardController.getStateBoard(), inputPosition)) {
                System.out.print("\nEnabled position! ");
                inputPosition = boardController.correctInputPosition();
            }
            boardController.setBoardField(inputPosition, computer.getSymbol() == 'O' ? 'X' : 'O');
            boardController.updateBoardView();
            if ((boardController.checkWin(boardController.getStateBoard()).size() == 0)) {
                Integer[] computerPosition = computer.simulateComputerGame(level, boardController.getStateBoard());
                boardController.setBoardField(computerPosition, computer.getSymbol());
                boardController.updateBoardView();
            }
        }
    }
}
