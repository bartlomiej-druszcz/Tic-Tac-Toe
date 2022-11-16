package org.example.computer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A strategy of determining random blank spaces in the Tic-Tac-Toe game.
 */
public final class RandomStrategy {
    /**
     * The function adds the coordinates of blank space to the list and randomly selects one item from among them to
     * insert the symbol.
     *
     * @param board character table with the current state of the game
     * @return coordinates of the spot where insert the symbol
     */
    public static Integer[] randomMove(Character[][] board) {
        List<Integer[]> list = findBlanks(board);
        return chooseRandom(list);
    }

    /**
     * The function searches the board for empty fields and returns a list of these places.
     *
     * @param board character table with the current state of the game
     * @return list of empty places
     */
    public static List<Integer[]> findBlanks(Character[][] board) {
        List<Integer[]> list = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].equals(' ')) {
                    list.add(new Integer[]{i, j});
                }
            }
        }
        return list;
    }

    /**
     * The function selects a random item from the list.
     *
     * @param list list of empty places
     * @return coordinates of the spot where insert the symbol
     */
    public static Integer[] chooseRandom(List<Integer[]> list) {
        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}
