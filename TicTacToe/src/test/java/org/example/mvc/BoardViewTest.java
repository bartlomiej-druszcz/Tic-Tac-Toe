package org.example.mvc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class BoardViewTest {

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

    @Test
    void signToArraySigns() {
        //given
        BoardView boardView = new BoardView();
        //when
        Character[][] actualCharacterO = boardView.signToArraySigns('O');
        Character[][] actualCharacterX = boardView.signToArraySigns('X');
        Character[][] actualCharacterEmpty = boardView.signToArraySigns(' ');
        //then
        Assertions.assertArrayEquals(SIGN_O, actualCharacterO);
        Assertions.assertArrayEquals(SIGN_X, actualCharacterX);
        Assertions.assertArrayEquals(EMPTY, actualCharacterEmpty);
    }

    @Test
    void isColor() {
        //given
        BoardView boardView = new BoardView();
        List<Integer[]> listTest = new ArrayList<>();
        listTest.add(new Integer[]{1, 0});
        listTest.add(new Integer[]{1, 1});
        listTest.add(new Integer[]{2, 1});

        List<Integer[]> listEmpty = new ArrayList<>();
        //when
        Boolean colorActual = boardView.isColor(new Integer[]{1, 1}, listTest);
        Boolean colorNotActual = boardView.isColor(new Integer[]{0, 0}, listTest);
        Boolean colorNotActualEmpty = boardView.isColor(new Integer[]{0, 0}, listEmpty);
        //then
        Assertions.assertEquals(true, colorActual);
        Assertions.assertEquals(false, colorNotActual);
        Assertions.assertEquals(false, colorNotActualEmpty);
    }
}