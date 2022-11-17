package org.example.computer;

import static org.example.computer.RandomStrategy.randomMove;
import static org.example.computer.WinningStrategy.*;

public class Computer {
    private final Character symbol;
    private final Character opponentSymbol;

    public Computer(Character symbol) {
        this.symbol = symbol;
        this.opponentSymbol = symbol == 'O' ? 'X' : 'O';
    }

    public Integer[] simulateComputerGame(String level, Character[][] board) {
        return switch (level) {
            case "1" -> easyLevel(board);
            case "2", "3" -> mediumOrHardLevel(board, level);
            default -> throw new IllegalArgumentException("Wrong level selected!");
        };
    }

    private Integer[] easyLevel(Character[][] board) {
        return randomMove(board);
    }

    private Integer[] mediumOrHardLevel(Character[][] board, String level) {
        if (winOrBlockMove(board, symbol)[0] != -1) {
            return winOrBlockMove(board, symbol);
        }
        if (winOrBlockMove(board, opponentSymbol)[0] != -1) {
            return winOrBlockMove(board, opponentSymbol);
        }
        if (level.equals("3")) {
            if (makeBranchMove(board, symbol)[0] != -1) {
                return makeBranchMove(board, symbol);
            }
        }
        if (makeBlockBranchMove(board, opponentSymbol)[0] != -1) {
            return makeBlockBranchMove(board, opponentSymbol);
        }
        if (centerMove(board)[0] != -1) {
            return centerMove(board);
        }
        if (oppositeCornerMove(board, opponentSymbol)[0] != -1) {
            return oppositeCornerMove(board, opponentSymbol);
        }
        if (emptyCornerMove(board)[0] != -1) {
            return emptyCornerMove(board);
        }
        return emptySideMove(board);
    }

    public Character getSymbol() {
        return symbol;
    }
}
