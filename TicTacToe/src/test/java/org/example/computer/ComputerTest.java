package org.example.computer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ComputerTest {
    static Computer computer;
    static Character[][] test;
    static List<Integer[]> listTest;

    @BeforeAll
    public static void init() {
        computer = new Computer('X');
        test = new Character[][]{
                {'O', 'O', 'X'},
                {' ', ' ', ' '},
                {'O', ' ', 'X'}};
        listTest = new ArrayList<>();
        listTest.add(new Integer[]{1, 0});
        listTest.add(new Integer[]{1, 1});
        listTest.add(new Integer[]{2, 1});
        listTest.add(new Integer[]{1, 2});
    }

    @RepeatedTest(1000)
    void simulateComputerGameRandomField() {
        //given
//
        //when
        boolean flag = false;
        Integer[] randoms = computer.simulateComputerGame("1", test);
        for (Integer[] element : listTest) {
            if (Arrays.deepEquals(element, randoms)) {
                flag = true;
                break;
            }
        }
        //then
        Assertions.assertTrue(flag);
    }

    @Test
    void simulateComputerGameException() {
        String expectedMessage = "Wrong level selected!";
        var exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> computer.simulateComputerGame("4", test));

        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void mediumOrHardLevel() {
        //given
        Character[][] testMedium = new Character[][]{
                {' ', ' ', 'X'},
                {' ', ' ', 'O'},
                {'O', 'X', ' '}};
        Character[][] testHard = new Character[][]{
                {'O', ' ', 'X'},
                {'X', ' ', ' '},
                {' ', 'O', ' '}};
        //when
        Integer[] resultMedium = computer.simulateComputerGame("2",testMedium);
        Integer[] resultHard = computer.simulateComputerGame("3",testHard);
        //then
        Assertions.assertArrayEquals(new Integer[] {1,2}, resultHard);
        Assertions.assertArrayEquals(new Integer[] {0,1}, resultMedium);
    }
}