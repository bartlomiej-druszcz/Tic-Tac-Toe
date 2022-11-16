package org.example.computer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class RandomStrategyTest {
    static Character[][] testThreeFieldEmpty;
    static Character[][] testOneFieldEmpty;
    static Character[][] testNineFieldEmpty;
    static List<Integer[]> listThreeFieldEmpty;
    static List<Integer[]> listOneFieldEmpty;
    static List<Integer[]> listnineFieldEmpty;

    @BeforeAll
    static void beforeAll() {
        testThreeFieldEmpty = new Character[][]{
                {'O', 'O', 'X'},
                {' ', ' ', ' '},
                {'O', 'X', 'X'}};
        listThreeFieldEmpty = new ArrayList<>();
        listThreeFieldEmpty.add(new Integer[]{1, 0});
        listThreeFieldEmpty.add(new Integer[]{1, 1});
        listThreeFieldEmpty.add(new Integer[]{1, 2});

        testOneFieldEmpty = new Character[][]{
                {'O', 'O', 'X'},
                {'X', 'O', 'X'},
                {'O', 'X', ' '}};
        listOneFieldEmpty = new ArrayList<>();
        listOneFieldEmpty.add(new Integer[]{2, 2});

        testNineFieldEmpty = new Character[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}};
        listnineFieldEmpty = Arrays.asList(new Integer[]{0, 0}, new Integer[]{0, 1}, new Integer[]{0, 2},
                new Integer[]{1, 0}, new Integer[]{1, 1}, new Integer[]{1, 2},
                new Integer[]{2, 0}, new Integer[]{2, 1}, new Integer[]{2, 2});

    }

    @RepeatedTest(100)
    void randomMoveWithTreeEmpty() {
        //given
//
        //when
        boolean flag = false;
        Integer[] randoms = RandomStrategy.randomMove(testThreeFieldEmpty);
        for (Integer[] element : listThreeFieldEmpty) {
            if (Arrays.deepEquals(element, randoms)) {
                flag = true;
                break;
            }
        }
        //then
        Assertions.assertTrue(flag);
    }

    @RepeatedTest(100)
    void randomMoveWithOneEmpty() {
        //given
//
        //when
        boolean flag = false;
        Integer[] randoms = RandomStrategy.randomMove(testOneFieldEmpty);
        for (Integer[] element : listOneFieldEmpty) {
            if (Arrays.deepEquals(element, randoms)) {
                flag = true;
                break;
            }
        }
        //then
        Assertions.assertTrue(flag);
    }

    @RepeatedTest(100)
    void randomMoveWithNineEmpty() {
        //given
//
        //when
        boolean flag = false;
        Integer[] randoms = RandomStrategy.randomMove(testNineFieldEmpty);
        for (Integer[] element : listnineFieldEmpty) {
            if (Arrays.deepEquals(element, randoms)) {
                flag = true;
                break;
            }
        }
        //then
        Assertions.assertTrue(flag);
    }

    public static Stream<Arguments> numberEmptyData() {
        return Stream.of(
                Arguments.of(new Character[][]{
                        {' ', ' ', 'X'},
                        {' ', ' ', ' '},
                        {' ', ' ', ' '}}, 8),
                Arguments.of(new Character[][]{
                        {'O', ' ', ' '},
                        {' ', ' ', ' '},
                        {'O', 'X', 'X'}}, 5),
                Arguments.of(new Character[][]{
                        {'O', ' ', 'X'},
                        {' ', ' ', ' '},
                        {' ', ' ', ' '}}, 7),
                Arguments.of(new Character[][]{
                        {'O', ' ', 'X'},
                        {' ', 'X', ' '},
                        {'O', 'X', 'X'}}, 3),
                Arguments.of(new Character[][]{
                        {' ', ' ', ' '},
                        {' ', 'O', ' '},
                        {'O', ' ', 'X'}}, 6),
                Arguments.of(new Character[][]{
                        {'O', 'O', ' '},
                        {'X', 'O', 'X'},
                        {'O', 'X', 'X'}}, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("numberEmptyData")
    void sizeOutputList(Character[][] arrayInput, Integer expectedOutput) {
        //given

        //when
        Integer actualSize = RandomStrategy.findBlanks(arrayInput).size();
        //then
        Assertions.assertEquals(expectedOutput, actualSize);
    }

    public static Stream<Arguments> listData() {
        List<Integer[]> oneList = new ArrayList<>();
        oneList.add(new Integer[]{1, 1});
        return Stream.of(
                Arguments.of(new Character[][]{
                                {'O', 'O', 'X'},
                                {' ', ' ', ' '},
                                {'O', 'X', 'X'}},
                        Arrays.asList(new Integer[]{1, 0}, new Integer[]{1, 1}, new Integer[]{1, 2})),
                Arguments.of(new Character[][]{
                                {'O', 'O', 'X'},
                                {'X', ' ', 'X'},
                                {'O', 'X', 'X'}},
                        oneList),
                Arguments.of(new Character[][]{
                                {' ', ' ', ' '},
                                {' ', ' ', ' '},
                                {' ', ' ', ' '}},
                        Arrays.asList(new Integer[]{0, 0}, new Integer[]{0, 1}, new Integer[]{0, 2},
                                new Integer[]{1, 0}, new Integer[]{1, 1}, new Integer[]{1, 2},
                                new Integer[]{2, 0}, new Integer[]{2, 1}, new Integer[]{2, 2}))
        );
    }

    @ParameterizedTest
    @MethodSource("listData")
    void findBlanks(Character[][] arrayInput, List<Integer[]> expectedOutput) {
        //given

        //when
        List<Integer[]> actualList = RandomStrategy.findBlanks(arrayInput);
        boolean flag = true;
        for (int i = 0; i < actualList.size(); i++) {
            if (!Arrays.deepEquals(actualList.get(i), expectedOutput.get(i))) {
                flag = false;
                break;
            }
        }
        //then
        Assertions.assertTrue(flag);
    }

    @RepeatedTest(100)
    void chooseRandomFromList() {
        //given
//
        //when
        boolean flag = false;
        Integer[] randoms = RandomStrategy.chooseRandom(listnineFieldEmpty);
        for (Integer[] element : listnineFieldEmpty) {
            if (Arrays.deepEquals(element, randoms)) {
                flag = true;
                break;
            }
        }
        //then
        Assertions.assertTrue(flag);
    }
}
