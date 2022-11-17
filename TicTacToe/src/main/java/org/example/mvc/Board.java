package org.example.mvc;

public class Board {
    private Character[][] board;

    public void initGame() {
        this.board = new Character[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}};
    }

    public Character[][] getBoard() {
        return this.board;
    }

    public void setField(Integer[] coordinates, Character sign) {
        this.board[coordinates[0]][coordinates[1]] = sign;
    }
}
