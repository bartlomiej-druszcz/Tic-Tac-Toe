package org.example.mvc;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BoardController {
    private final Board board;
    private final BoardView boardView;

    public BoardController(Board board, BoardView boardView) {
        this.board = board;
        this.boardView = boardView;
    }

    public void initBoard() {
        board.initGame();
    }

    public void setBoardField(Integer[] inputInsert, Character sign) {
        board.setField(inputInsert, sign);
    }

    public Character[][] getStateBoard() {
        return board.getBoard();
    }

    public void updateBoardView() {
        boardView.print(board.getBoard(), checkWin(board.getBoard()));
    }

    public Boolean isMoveCorrect(Character[][] board, Integer[] input) {
        return board[input[0]][input[1]].equals(' ');
    }

    public Boolean isEmptyField(Character[][] board) {
        for (Character[] row : board) {
            for (Character field : row) {
                if (field.equals(' ')) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Integer[]> checkWin(Character[][] board) {
        List<Integer[]> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].equals(' ')) {
                list.add(new Integer[]{i, 0});
                list.add(new Integer[]{i, 1});
                list.add(new Integer[]{i, 2});
                return list;
            }
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].equals(' ')) {
                list.add(new Integer[]{0, i});
                list.add(new Integer[]{1, i});
                list.add(new Integer[]{2, i});
                return list;
            }
        }

        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals(' ')) {
            list.add(new Integer[]{0, 0});
            list.add(new Integer[]{1, 1});
            list.add(new Integer[]{2, 2});
            return list;
        }

        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[2][0].equals(' ')) {
            list.add(new Integer[]{0, 2});
            list.add(new Integer[]{1, 1});
            list.add(new Integer[]{2, 0});
            return list;
        }
        return list;
    }

    public Integer[] mapToCoordinates(Integer input) {
        return switch (input) {
            case 7 -> new Integer[]{0, 0};
            case 8 -> new Integer[]{0, 1};
            case 9 -> new Integer[]{0, 2};
            case 4 -> new Integer[]{1, 0};
            case 5 -> new Integer[]{1, 1};
            case 6 -> new Integer[]{1, 2};
            case 1 -> new Integer[]{2, 0};
            case 2 -> new Integer[]{2, 1};
            case 3 -> new Integer[]{2, 2};
            default -> throw new IllegalArgumentException("\nWrong field selected!");
        };
    }

    public Integer[] correctInputPosition() {
        Integer[] coordinates = new Integer[]{};
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;
        while (!flag) {
            System.out.print("Enter position: ");
            try {
                while (!scanner.hasNextInt()) {
                    scanner.next();
                    System.out.print("\nEnter a number! Enter position: ");
                }
                int input = scanner.nextInt();
                coordinates = mapToCoordinates(input);
                flag = true;
            } catch (InputMismatchException | IllegalArgumentException ex) {
                System.out.print(ex.getMessage() + " ");
            }
        }
        return coordinates;
    }
}
