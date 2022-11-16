package org.example.mvc;

import java.util.Arrays;
import java.util.List;

public class BoardView {
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_RED = "\u001B[31m";

    public static final Character[][] SIGN_X = new Character[][]{
            {' ', '_', '_', '_', '_', '_', '_', '_', '_', '_', ' '},
            {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'|', ' ', ' ', '\\', ' ', ' ', ' ', '/', ' ', ' ', '|'},
            {'|', ' ', ' ', ' ', '\\', ' ', '/', ' ', ' ', ' ', '|'},
            {'|', ' ', ' ', ' ', '/', ' ', '\\', ' ', ' ', ' ', '|'},
            {'|', ' ', ' ', '/', ' ', ' ', ' ', '\\', ' ', ' ', '|'},
            {'|', '_', '_', '_', '_', '_', '_', '_', '_', '_', '|'}};

    public static final Character[][] EMPTY = new Character[][]{
            {' ', '_', '_', '_', '_', '_', '_', '_', '_', '_', ' '},
            {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'|', '_', '_', '_', '_', '_', '_', '_', '_', '_', '|'}};

    public static final Character[][] SIGN_O = new Character[][]{
            {' ', '_', '_', '_', '_', '_', '_', '_', '_', '_', ' '},
            {'|', ' ', ' ', ' ', '_', '_', '_', ' ', ' ', ' ', '|'},
            {'|', ' ', ' ', '/', ' ', ' ', ' ', '\\', ' ', ' ', '|'},
            {'|', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', '|'},
            {'|', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', '|'},
            {'|', ' ', ' ', '\\', '_', '_', '_', '/', ' ', ' ', '|'},
            {'|', '_', '_', '_', '_', '_', '_', '_', '_', '_', '|'}};


    public Character[][] signToArraySigns(Character character) {
        if (character.equals('X')) {
            return SIGN_X;
        } else if (character.equals('O')) {
            return SIGN_O;
        } else {
            return EMPTY;
        }
    }

    public Boolean isColor(Integer[] coordinates, List<Integer[]> list) {
        for (Integer[] element : list) {
            if (Arrays.deepEquals(element, new Integer[]{coordinates[0], coordinates[1]})) {
                return true;
            }
        }
        return false;
    }

    public void print(Character[][] board, List<Integer[]> list) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println();
        StringBuilder stringBuilder = new StringBuilder();
        for (int m = 0; m < board.length; m++) {
            for (int i = 0; i < EMPTY.length; i++) {
                for (int n = 0; n < board[m].length; n++) {
                    Boolean color = isColor(new Integer[]{m, n}, list);
                    if (color) {
                        stringBuilder.append(ANSI_RED);
                    }

                    Character[][] sign = signToArraySigns(board[m][n]);
                    for (int j = 0; j < sign[i].length; j++) {
                        stringBuilder.append(sign[i][j]);
                    }

                    if (color) {
                        stringBuilder.append(ANSI_RESET);
                    }
                }
                stringBuilder.append("\n");
            }
        }
        System.out.println(stringBuilder);
        System.out.println();
    }
}
