package org.example.computer;

import java.util.*;

/**
 * The winning strategy is a strategy described by Kevin Crowley and Robert S. Siegler in 1993.
 * It should be read in such a way that we try to check the conditions of the next possible moves one by one,
 * and when possible, we execute it. We repeat the whole thing until we finish the game.
 */
public final class WinningStrategy {
    /**
     * 1. Win
     * If there is a row, column, or diagonal with two of my pieces and a blank space,
     * Then play the blank space (thus winning the game).
     * <p>
     * 2. Block
     * If there is a row, column, or diagonal with two of my opponent's pieces and a blank space,
     * Then play the blank space (thus blocking a potential win for my opponent).
     *
     * @param board  character table with the current state of the game
     * @param symbol my symbol or symbol of the opponent as a sign
     * @return coordinates of the spot where insert the symbol or (-1, -1) if there is no spot to make this kind move
     */
    public static Integer[] winOrBlockMove(Character[][] board, Character symbol) {
        Integer[] winOrBlockField = checkRowsAndColumns(board, symbol);
        if (winOrBlockField[0] != -1) {
            return winOrBlockField;
        } else {
            return checkDiagonals(board, symbol);
        }
    }

    /**
     * 3. Fork
     * If there are two intersecting rows, columns, or diagonals with one of my pieces and two blanks, and
     * If the intersecting space is empty,
     * Then move to the intersecting space (thus creating two ways to win on my next turn).
     *
     * @param board  character table with the current state of the game
     * @param symbol my symbol as a sign
     * @return coordinates of the spot where insert the symbol or (-1, -1) if there is no spot to make this kind move
     */
    public static Integer[] makeBranchMove(Character[][] board, Character symbol) {
        return findBranchingField(board, symbol);
    }

    /**
     * 4. Block Fork
     * If there are two intersecting rows, columns, or diagonals with one of my opponent's pieces and two blanks, and
     * If the intersecting space is empty,
     * Then
     * If there is an empty location that creates a two-in-a-row for me (thus forcing my opponent to block rather than fork),
     * Then move to the location.
     * Else move to the intersection space (thus occupying the location that my opponent could use to fork).
     *
     * @param board  character table with the current state of the game
     * @param symbol symbol of the opponent  as a sign
     * @return coordinates of the spot where insert the symbol or (-1, -1) if there is no spot to make this kind move
     */
    public static Integer[] makeBlockBranchMove(Character[][] board, Character symbol) {
        Integer[] blockField = findBranchingField(board, symbol);
        if (blockField[0] != -1) {
            Integer[] twoInRowOrColumn = checkOptionTwoInRowOrColumn(board, symbol == 'O' ? 'X' : 'O');
            if (twoInRowOrColumn[0] != -1) {
                return twoInRowOrColumn;
            }
            Integer[] twoInDiagonal = checkOptionTwoInDiagonal(board, symbol == 'O' ? 'X' : 'O');
            if (twoInDiagonal[0] != -1) {
                return twoInDiagonal;
            }
            return blockField;
        }
        return blockField;
    }

    /**
     * 5. Play Center
     * If the center is blank,
     * Then play the center.
     *
     * @param board character table with the current state of the game
     * @return coordinates of the spot where insert the symbol or (-1, -1) if there is no spot to make this kind move
     */
    public static Integer[] centerMove(Character[][] board) {
        if (board[1][1].equals(' ')) {
            return new Integer[]{1, 1};
        }
        return new Integer[]{-1, -1};

    }

    /**
     * 6. Play Opposite Corner
     * If my opponent is in a corner, and
     * If the opposite corner is empty,
     * Then play the opposite corner.
     *
     * @param board  character table with the current state of the game
     * @param symbol symbol of the opponent  as a sign
     * @return coordinates of the spot where insert the symbol or (-1, -1) if there is no spot to make this kind move
     */
    public static Integer[] oppositeCornerMove(Character[][] board, Character symbol) {
        if (board[0][0].equals(' ') && board[2][2].equals(symbol)) {
            return new Integer[]{0, 0};
        }
        if (board[2][2].equals(' ') && board[0][0].equals(symbol)) {
            return new Integer[]{2, 2};
        }
        if (board[0][2].equals(' ') && board[2][0].equals(symbol)) {
            return new Integer[]{0, 2};
        }
        if (board[2][0].equals(' ') && board[0][2].equals(symbol)) {
            return new Integer[]{2, 0};
        }
        return new Integer[]{-1, -1};
    }

    /**
     * 7. Play Empty Corner
     * If there is an empty corner,
     * Then move to an empty corner.
     *
     * @param board character table with the current state of the game
     * @return coordinates of the spot where insert the symbol or (-1, -1) if there is no spot to make this kind move
     */
    public static Integer[] emptyCornerMove(Character[][] board) {
        List<Integer[]> list = new ArrayList<>();
        if (board[0][0].equals(' ')) {
            list.add(new Integer[]{0, 0});
        }
        if (board[2][2].equals(' ')) {
            list.add(new Integer[]{2, 2});
        }
        if (board[0][2].equals(' ')) {
            list.add(new Integer[]{0, 2});
        }
        if (board[2][0].equals(' ')) {
            list.add(new Integer[]{2, 0});
        }
        if (list.size() != 0) {
            return RandomStrategy.chooseRandom(list);
        }
        return new Integer[]{-1, -1};
    }

    /**
     * 8. Play Empty Side
     * If there is an empty side,
     * Then move to an empty side.
     *
     * @param board character table with the current state of the game
     * @return coordinates of the spot where insert the symbol
     */
    public static Integer[] emptySideMove(Character[][] board) {
        List<Integer[]> list = new ArrayList<>();
        if (board[0][1].equals(' ')) {
            list.add(new Integer[]{0, 1});
        }
        if (board[1][2].equals(' ')) {
            list.add(new Integer[]{1, 2});
        }
        if (board[1][0].equals(' ')) {
            list.add(new Integer[]{1, 0});
        }
        if (board[2][1].equals(' ')) {
            list.add(new Integer[]{2, 1});
        }
        return RandomStrategy.chooseRandom(list);
    }

    /**
     * The function looks for a row or column with two of my symbols (the opponent's symbols) and an empty space.
     * If it finds such a combination, it moves to an empty spot.
     *
     * @param board  character table with the current state of the game
     * @param symbol my symbol or symbol of the opponent as a sign
     * @return coordinates of the spot where insert the symbol or (-1, -1) if there is no spot to make this kind move
     */
    public static Integer[] checkRowsAndColumns(Character[][] board, Character symbol) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0].equals(symbol) && board[i][1].equals(symbol) && board[i][2].equals(' ')) {
                return new Integer[]{i, 2};
            }
            if (board[i][0].equals(symbol) && board[i][2].equals(symbol) && board[i][1].equals(' ')) {
                return new Integer[]{i, 1};
            }
            if (board[i][1].equals(symbol) && board[i][2].equals(symbol) && board[i][0].equals(' ')) {
                return new Integer[]{i, 0};
            }
            if (board[0][i].equals(symbol) && board[1][i].equals(symbol) && board[2][i].equals(' ')) {
                return new Integer[]{2, i};
            }
            if (board[0][i].equals(symbol) && board[2][i].equals(symbol) && board[1][i].equals(' ')) {
                return new Integer[]{1, i};
            }
            if (board[1][i].equals(symbol) && board[2][i].equals(symbol) && board[0][i].equals(' ')) {
                return new Integer[]{0, i};
            }
        }
        return new Integer[]{-1, -1};
    }

    /**
     * The function looks for a diagonal with two of my symbols (the opponent's symbols) and an empty space.
     * If it finds such a combination, it moves to an empty spot.
     *
     * @param board  character table with the current state of the game
     * @param symbol my symbol or symbol of the opponent as a sign
     * @return coordinates of the spot where insert the symbol or (-1, -1) if there is no spot to make this kind move
     */
    public static Integer[] checkDiagonals(Character[][] board, Character symbol) {
        if (board[0][0].equals(symbol) && board[1][1].equals(symbol) && board[2][2].equals(' ')) {
            return new Integer[]{2, 2};
        }
        if (board[0][0].equals(symbol) && board[2][2].equals(symbol) && board[1][1].equals(' ')) {
            return new Integer[]{1, 1};
        }
        if (board[1][1].equals(symbol) && board[2][2].equals(symbol) && board[0][0].equals(' ')) {
            return new Integer[]{0, 0};
        }
        if (board[0][2].equals(symbol) && board[1][1].equals(symbol) && board[2][0].equals(' ')) {
            return new Integer[]{2, 0};
        }
        if (board[0][2].equals(symbol) && board[2][0].equals(symbol) && board[1][1].equals(' ')) {
            return new Integer[]{1, 1};
        }
        if (board[1][1].equals(symbol) && board[2][0].equals(symbol) && board[0][2].equals(' ')) {
            return new Integer[]{0, 2};
        }
        return new Integer[]{-1, -1};
    }

    /**
     * The function checks if there are two intersecting rows, columns or diagonals with one my symbol and two empty
     * spaces, and if the intersection is empty.
     *
     * @param board  character table with the current state of the game
     * @param symbol my symbol or symbol of the opponent as a sign
     * @return coordinates of the spot where insert the symbol or (-1, -1) if there is no spot to make this kind move
     */
    public static Integer[] findBranchingField(Character[][] board, Character symbol) {
        Set<Integer> invalidColumns = new HashSet<>();
        boolean validDiagonalFirst = isValidFirstDiagonal(board, symbol);
        boolean validDiagonalSecond = isValidSecondDiagonal(board, symbol);

        for (int i = 0; i < board.length; i++) {
            boolean validRow = isValidRow(board, symbol, i);
            for (int j = 0; j < board.length && validRow; j++) {
                if (board[i][j].equals('X') || board[i][j].equals('O') || invalidColumns.contains(j)) {
                    continue;
                }

                boolean validColumn = isValidColumn(board, symbol, j);
                if (validColumn) {
                    return new Integer[]{i, j};
                } else {
                    invalidColumns.add(j);
                }
            }
            if (validRow) {
                if (validDiagonalFirst && board[i][i].equals(' ')) {
                    return new Integer[]{i, i};
                }
                if (validDiagonalSecond && board[i][board.length - 1 - i].equals(' ')) {
                    return new Integer[]{i, board.length - 1 - i};
                }
            }
            if (isValidColumn(board, symbol, i)) {
                if (validDiagonalFirst && board[i][i].equals(' ')) {
                    return new Integer[]{i, i};
                }
                if (validDiagonalSecond && board[board.length - 1 - i][i].equals(' ')) {
                    return new Integer[]{board.length - 1 - i, i};
                }
            }
        }
        if (board[1][1].equals(' ') && validDiagonalFirst && validDiagonalSecond) {
            return new Integer[]{1, 1};
        }
        return new Integer[]{-1, -1};
    }

    /**
     * The function checks whether the row satisfies the conditions for branching.
     *
     * @param board  character table with the current state of the game
     * @param symbol my symbol as a sign
     * @param row    number row
     * @return true if there are two empty spaces and one occupied by my symbol
     */
    public static Boolean isValidRow(Character[][] board, Character symbol, Integer row) {
        int countEmptyFieldInRow = 0;
        int countFieldWithSymbolInRow = 0;

        for (int k = 0; k < board.length; k++) {
            if (board[row][k].equals(symbol)) {
                countFieldWithSymbolInRow++;
            } else if (board[row][k].equals(' ')) {
                countEmptyFieldInRow++;
            }
        }
        return countEmptyFieldInRow == 2 && countFieldWithSymbolInRow == 1;
    }

    /**
     * The function checks whether the column satisfies the conditions for branching.
     *
     * @param board  character table with the current state of the game
     * @param symbol my symbol as a sign
     * @param column number column
     * @return true if there are two empty spaces and one occupied by my symbol
     */
    public static Boolean isValidColumn(Character[][] board, Character symbol, Integer column) {
        int countEmptyFieldInColumn = 0;
        int countFieldWithSymbolInColumn = 0;

        for (Character[] characters : board) {
            if (characters[column].equals(symbol)) {
                countFieldWithSymbolInColumn++;
            } else if (characters[column].equals(' ')) {
                countEmptyFieldInColumn++;
            }
        }
        return countEmptyFieldInColumn == 2 && countFieldWithSymbolInColumn == 1;
    }

    /**
     * The function checks whether the first diagonal satisfies the conditions for branching.
     *
     * @param board  character table with the current state of the game
     * @param symbol my symbol as a sign
     * @return true if there are two empty spaces and one occupied by my symbol
     */
    public static Boolean isValidFirstDiagonal(Character[][] board, Character symbol) {
        int countEmptyFieldDiagonal = 0;
        int countMyFieldDiagonal = 0;

        for (int i = 0; i < board.length; i++) {
            if (board[i][i].equals(' ')) {
                countEmptyFieldDiagonal++;
            }
            if (board[i][i].equals(symbol)) {
                countMyFieldDiagonal++;
            }
        }
        return countEmptyFieldDiagonal == 2 && countMyFieldDiagonal == 1;
    }

    /**
     * The function checks whether the second diagonal satisfies the conditions for branching.
     *
     * @param board  character table with the current state of the game
     * @param symbol my symbol as a sign
     * @return true if there are two empty spaces and one occupied by my symbol
     */
    public static Boolean isValidSecondDiagonal(Character[][] board, Character symbol) {
        int countEmptyFieldDiagonal = 0;
        int countMyFieldDiagonal = 0;

        for (int i = 0; i < board.length; i++) {
            if (board[i][board.length - 1 - i].equals(' ')) {
                countEmptyFieldDiagonal++;
            }
            if (board[i][board.length - 1 - i].equals(symbol)) {
                countMyFieldDiagonal++;
            }
        }
        return countEmptyFieldDiagonal == 2 && countMyFieldDiagonal == 1;
    }

    /**
     * The function looks for a row or column with one my symbol and two free spaces. If it finds such a combination,
     * it moves to an empty spot.
     *
     * @param board  character table with the current state of the game
     * @param symbol my symbol as a sign
     * @return coordinates of the spot where insert the symbol or (-1, -1) if there is no spot to make this kind move
     */
    public static Integer[] checkOptionTwoInRowOrColumn(Character[][] board, Character symbol) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0].equals(' ') && board[i][1].equals(' ') && board[i][2].equals(symbol)) {
                return new Integer[]{i, 1};
            }
            if (board[i][0].equals(' ') && board[i][1].equals(symbol) && board[i][2].equals(' ')) {
                return new Integer[]{i, 0};
            }
            if (board[i][0].equals(symbol) && board[i][1].equals(' ') && board[i][2].equals(' ')) {
                return new Integer[]{i, 1};
            }
            if (board[0][i].equals(' ') && board[1][i].equals(' ') && board[2][i].equals(symbol)) {
                return new Integer[]{1, i};
            }
            if (board[0][i].equals(' ') && board[1][i].equals(symbol) && board[2][i].equals(' ')) {
                return new Integer[]{0, i};
            }
            if (board[0][i].equals(symbol) && board[1][i].equals(' ') && board[2][i].equals(' ')) {
                return new Integer[]{1, i};
            }
        }
        return new Integer[]{-1, -1};
    }

    /**
     * The function looks for a diagonal with one my symbol and two free spaces. If it finds such a combination,
     * it moves to an empty spot.
     *
     * @param board  character table with the current state of the game
     * @param symbol my symbol as a sign
     * @return coordinates of the spot where insert the symbol or (-1, -1) if there is no spot to make this kind move
     */
    public static Integer[] checkOptionTwoInDiagonal(Character[][] board, Character symbol) {
        if (board[0][0].equals(' ') && board[1][1].equals(' ') && board[2][2].equals(symbol)) {
            return new Integer[]{1, 1};
        }
        if (board[0][0].equals(' ') && board[1][1].equals(symbol) && board[2][2].equals(' ')) {
            return new Integer[]{2, 2};
        }
        if (board[0][0].equals(symbol) && board[1][1].equals(' ') && board[2][2].equals(' ')) {
            return new Integer[]{1, 1};
        }
        if (board[0][2].equals(' ') && board[1][1].equals(' ') && board[2][0].equals(symbol)) {
            return new Integer[]{1, 1};
        }
        if (board[0][2].equals(' ') && board[1][1].equals(symbol) && board[2][0].equals(' ')) {
            return new Integer[]{2, 0};
        }
        if (board[0][2].equals(symbol) && board[1][1].equals(' ') && board[2][0].equals(' ')) {
            return new Integer[]{1, 1};
        }
        return new Integer[]{-1, -1};
    }
}
