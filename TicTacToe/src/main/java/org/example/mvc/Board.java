package org.example.mvc;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private static Integer[] mapToCoordinates(Integer input) {
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
            default -> throw new IllegalArgumentException("Wrong Field Selected");
        };
    }

    public static boolean isMoveCorrect(Character[][] board, Integer input) {
        Integer[] array = mapToCoordinates(input);
        return board[array[0]][array[1]].equals(' ');
    }

    private static List<Integer[]> checkWin(Character[][] board) {
        List<Integer[]> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                list.add(new Integer[]{0, 0});
                list.add(new Integer[]{0, 1});
                list.add(new Integer[]{0, 2});
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                list.add(new Integer[]{1, 0});
                list.add(new Integer[]{1, 1});
                list.add(new Integer[]{1, 2});
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                list.add(new Integer[]{2, 0});
                list.add(new Integer[]{2, 1});
                list.add(new Integer[]{2, 2});
            }
        }
        return list;
    }
}
