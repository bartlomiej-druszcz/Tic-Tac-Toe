package org.example.computer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class WinningStrategyTest {

    public static Stream<Arguments> winOrBlockMoveData() {
        return Stream.of(
                Arguments.of(new Character[][]{
                        {'X', 'X', 'O'},
                        {'O', 'X', ' '},
                        {'O', 'O', ' '}}, 'O', new Integer[]{2, 2}),
                Arguments.of(new Character[][]{
                        {'X', 'X', 'O'},
                        {'O', ' ', ' '},
                        {'O', ' ', 'X'}}, 'X', new Integer[]{1, 1}),
                Arguments.of(new Character[][]{
                        {' ', 'X', 'O'},
                        {'X', ' ', 'O'},
                        {'O', ' ', 'X'}}, 'X', new Integer[]{-1, -1})
        );
    }

    @ParameterizedTest
    @MethodSource("winOrBlockMoveData")
    void winOrBlockMove(Character[][] arrayInput, Character symbol, Integer[] expectedOutput) {
        //given

        //when
        Integer[] actualOutput = WinningStrategy.winOrBlockMove(arrayInput, symbol);
        //then
        Assertions.assertEquals(expectedOutput[0], actualOutput[0]);
        Assertions.assertEquals(expectedOutput[1], actualOutput[1]);
    }

    public static Stream<Arguments> makeBranchMoveData() {
        return Stream.of(
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {' ', ' ', 'X'},
                        {'X', ' ', 'O'}}, 'X', new Integer[]{1, 0}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {' ', ' ', 'O'},
                        {'X', 'O', 'X'}}, 'X', new Integer[]{0, 0}),
                Arguments.of(new Character[][]{
                        {' ', ' ', ' '},
                        {'O', ' ', 'O'},
                        {'X', ' ', 'X'}}, 'X', new Integer[]{1, 1}),
                Arguments.of(new Character[][]{
                        {' ', ' ', ' '},
                        {'O', ' ', ' '},
                        {'X', ' ', 'X'}}, 'X', new Integer[]{0, 2}),
                Arguments.of(new Character[][]{
                        {'O', ' ', ' '},
                        {'O', ' ', ' '},
                        {'X', ' ', ' '}}, 'O', new Integer[]{1, 1}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {'O', ' ', 'O'},
                        {'X', 'O', 'X'}}, 'X', new Integer[]{-1, -1}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {'X', ' ', 'O'},
                        {'O', ' ', 'X'}}, 'X', new Integer[]{-1, -1})
        );
    }

    @ParameterizedTest
    @MethodSource("makeBranchMoveData")
    void makeBranchMove(Character[][] arrayInput, Character symbol, Integer[] expectedOutput) {
        //given

        //when
        Integer[] actualOutput = WinningStrategy.makeBranchMove(arrayInput, symbol);
        //then
        Assertions.assertEquals(expectedOutput[0], actualOutput[0]);
        Assertions.assertEquals(expectedOutput[1], actualOutput[1]);
    }

    public static Stream<Arguments> makeBlockBranchMoveData() {
        return Stream.of(
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {' ', ' ', 'X'},
                        {'X', ' ', 'O'}}, 'X', new Integer[]{0, 1}),
                Arguments.of(new Character[][]{
                        {'O', ' ', ' '},
                        {' ', 'X', ' '},
                        {' ', ' ', 'O'}}, 'O', new Integer[]{1, 0}),
                Arguments.of(new Character[][]{
                        {'X', ' ', ' '},
                        {' ', 'O', ' '},
                        {' ', ' ', 'O'}}, 'O', new Integer[]{0, 1}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {'X', ' ', 'O'},
                        {'O', ' ', 'X'}}, 'X', new Integer[]{-1, -1})
        );
    }

    @ParameterizedTest
    @MethodSource("makeBlockBranchMoveData")
    void makeBlockBranchMove(Character[][] arrayInput, Character symbol, Integer[] expectedOutput) {
        //given

        //when
        Integer[] actualOutput = WinningStrategy.makeBlockBranchMove(arrayInput, symbol);
        //then
        Assertions.assertEquals(expectedOutput[0], actualOutput[0]);
        Assertions.assertEquals(expectedOutput[1], actualOutput[1]);
    }

    public static Stream<Arguments> centerMoveData() {
        return Stream.of(
                Arguments.of(new Character[][]{
                        {' ', ' ', ' '},
                        {' ', ' ', ' '},
                        {' ', ' ', ' '}}, new Integer[]{1, 1}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'X'},
                        {' ', ' ', ' '},
                        {' ', ' ', ' '}}, new Integer[]{1, 1}),
                Arguments.of(new Character[][]{
                        {'X', ' ', 'X'},
                        {'O', ' ', 'X'},
                        {'O', ' ', 'O'}}, new Integer[]{1, 1}),
                Arguments.of(new Character[][]{
                        {' ', ' ', ' '},
                        {' ', 'O', ' '},
                        {' ', ' ', ' '}}, new Integer[]{-1, -1}),
                Arguments.of(new Character[][]{
                        {'O', 'O', ' '},
                        {'X', 'O', 'X'},
                        {'O', 'X', 'X'}}, new Integer[]{-1, -1})
        );
    }

    @ParameterizedTest
    @MethodSource("centerMoveData")
    void centerMove(Character[][] arrayInput, Integer[] expectedOutput) {
        //given

        //when
        Integer[] actualOutput = WinningStrategy.centerMove(arrayInput);
        //then
        Assertions.assertEquals(expectedOutput[0], actualOutput[0]);
        Assertions.assertEquals(expectedOutput[1], actualOutput[1]);
    }

    public static Stream<Arguments> oppositeCornerMoveData() {
        return Stream.of(
                Arguments.of(new Character[][]{
                        {'X', ' ', ' '},
                        {' ', ' ', ' '},
                        {' ', ' ', ' '}}, 'X', new Integer[]{2, 2}),
                Arguments.of(new Character[][]{
                        {'X', ' ', ' '},
                        {' ', ' ', 'O'},
                        {' ', ' ', 'X'}}, 'X', new Integer[]{-1, -1}),
                Arguments.of(new Character[][]{
                        {'X', ' ', ' '},
                        {' ', ' ', 'X'},
                        {' ', ' ', 'O'}}, 'X', new Integer[]{-1, -1}),
                Arguments.of(new Character[][]{
                        {'X', ' ', ' '},
                        {' ', ' ', 'X'},
                        {'X', ' ', 'O'}}, 'X', new Integer[]{0, 2}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {' ', ' ', ' '},
                        {' ', ' ', ' '}}, 'O', new Integer[]{2, 0}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {' ', ' ', 'O'},
                        {'O', ' ', ' '}}, 'O', new Integer[]{-1, -1}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {' ', ' ', 'X'},
                        {'X', ' ', ' '}}, 'O', new Integer[]{-1, -1}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {' ', ' ', 'X'},
                        {'X', ' ', 'O'}}, 'O', new Integer[]{0, 0}),
                Arguments.of(new Character[][]{
                        {' ', ' ', ' '},
                        {' ', ' ', ' '},
                        {'X', ' ', ' '}}, 'X', new Integer[]{0, 2}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'X'},
                        {' ', ' ', ' '},
                        {'X', ' ', ' '}}, 'X', new Integer[]{-1, -1}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {' ', ' ', ' '},
                        {'X', ' ', ' '}}, 'X', new Integer[]{-1, -1}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {' ', ' ', ' '},
                        {'X', ' ', 'X'}}, 'X', new Integer[]{0, 0}),
                Arguments.of(new Character[][]{
                        {' ', ' ', ' '},
                        {' ', ' ', ' '},
                        {' ', ' ', 'O'}}, 'O', new Integer[]{0, 0}),
                Arguments.of(new Character[][]{
                        {'X', ' ', ' '},
                        {' ', ' ', 'O'},
                        {' ', ' ', 'O'}}, 'O', new Integer[]{-1, -1}),
                Arguments.of(new Character[][]{
                        {'X', ' ', 'O'},
                        {' ', ' ', ' '},
                        {' ', ' ', 'O'}}, 'O', new Integer[]{2, 0}),
                Arguments.of(new Character[][]{
                        {'O', ' ', ' '},
                        {' ', 'X', ' '},
                        {' ', ' ', 'O'}}, 'O', new Integer[]{-1, -1}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {' ', 'X', ' '},
                        {'O', ' ', 'O'}}, 'O', new Integer[]{0, 0}),
                Arguments.of(new Character[][]{
                        {' ', ' ', ' '},
                        {' ', ' ', ' '},
                        {' ', ' ', ' '}}, 'X', new Integer[]{-1, -1})
        );
    }

    @ParameterizedTest
    @MethodSource("oppositeCornerMoveData")
    void oppositeCornerMove(Character[][] arrayInput, Character oponentSymbol, Integer[] expectedOutput) {
        //given

        //when
        Integer[] actualOutput = WinningStrategy.oppositeCornerMove(arrayInput, oponentSymbol);
        //then
        Assertions.assertEquals(expectedOutput[0], actualOutput[0]);
        Assertions.assertEquals(expectedOutput[1], actualOutput[1]);
    }

    public static Stream<Arguments> emptyCornerMoveData() {
        return Stream.of(
                Arguments.of(new Character[][]{
                        {'X', ' ', ' '},
                        {' ', ' ', ' '},
                        {'O', ' ', 'X'}}, new Integer[]{0, 2}),
                Arguments.of(new Character[][]{
                        {'X', ' ', 'O'},
                        {' ', ' ', ' '},
                        {' ', ' ', 'X'}}, new Integer[]{2, 0}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {' ', ' ', ' '},
                        {'O', ' ', 'X'}}, new Integer[]{0, 0}),
                Arguments.of(new Character[][]{
                        {'X', ' ', 'O'},
                        {' ', ' ', ' '},
                        {'O', ' ', ' '}}, new Integer[]{2, 2}),
                Arguments.of(new Character[][]{
                        {'X', ' ', 'O'},
                        {' ', ' ', ' '},
                        {'O', ' ', 'X'}}, new Integer[]{-1, -1}),
                Arguments.of(new Character[][]{
                        {'X', ' ', 'O'},
                        {' ', 'X', ' '},
                        {'O', ' ', 'X'}}, new Integer[]{-1, -1})
        );
    }

    @ParameterizedTest
    @MethodSource("emptyCornerMoveData")
    void emptyCornerMove(Character[][] arrayInput, Integer[] expectedOutput) {
        //given

        //when
        Integer[] actualOutput = WinningStrategy.emptyCornerMove(arrayInput);
        //then
        Assertions.assertEquals(expectedOutput[0], actualOutput[0]);
        Assertions.assertEquals(expectedOutput[1], actualOutput[1]);
    }

    public static Stream<Arguments> emptyCornerMoveManyOptionsData() {
        return Stream.of(
                Arguments.of(new Character[][]{
                        {'X', ' ', ' '},
                        {' ', ' ', ' '},
                        {' ', ' ', 'X'}}, Arrays.asList(new Integer[]{0, 2}, new Integer[]{2, 0})),
                Arguments.of(new Character[][]{
                        {' ', ' ', ' '},
                        {' ', ' ', ' '},
                        {' ', ' ', ' '}}, Arrays.asList(new Integer[]{0, 2}, new Integer[]{2, 0},
                        new Integer[]{2, 2}, new Integer[]{0, 0})),
                Arguments.of(new Character[][]{
                        {'X', ' ', ' '},
                        {' ', ' ', ' '},
                        {' ', ' ', ' '}}, Arrays.asList(new Integer[]{0, 2}, new Integer[]{2, 0}, new Integer[]{2, 2}))
        );
    }

    @ParameterizedTest
    @MethodSource("emptyCornerMoveManyOptionsData")
    void emptyCornerMoveManyOptionsToChoose(Character[][] arrayInput, List<Integer[]> list) {
        //given

        //when
        boolean flag = false;
        Integer[] randoms = WinningStrategy.emptyCornerMove(arrayInput);
        for (Integer[] element : list) {
            if (Arrays.deepEquals(element, randoms)) {
                flag = true;
                break;
            }
        }
        //then
        Assertions.assertTrue(flag);
    }

    public static Stream<Arguments> emptySideMoveData() {
        return Stream.of(
                Arguments.of(new Character[][]{
                        {'X', ' ', ' '},
                        {'O', ' ', 'O'},
                        {'O', 'X', 'X'}}, new Integer[]{0, 1}),
                Arguments.of(new Character[][]{
                        {'X', 'O', ' '},
                        {'O', ' ', ' '},
                        {' ', 'X', 'X'}}, new Integer[]{1, 2}),
                Arguments.of(new Character[][]{
                        {'X', 'O', 'O'},
                        {' ', ' ', 'X'},
                        {' ', 'O', 'X'}}, new Integer[]{1, 0}),
                Arguments.of(new Character[][]{
                        {' ', 'X', 'O'},
                        {'X', ' ', 'O'},
                        {'O', ' ', 'X'}}, new Integer[]{2, 1})
        );
    }

    @ParameterizedTest
    @MethodSource("emptySideMoveData")
    void emptySideMove(Character[][] arrayInput, Integer[] expectedOutput) {
        //given

        //when
        Integer[] actualOutput = WinningStrategy.emptySideMove(arrayInput);
        //then
        Assertions.assertEquals(expectedOutput[0], actualOutput[0]);
        Assertions.assertEquals(expectedOutput[1], actualOutput[1]);
    }

    public static Stream<Arguments> emptySideMoveManyOptionsData() {
        return Stream.of(
                Arguments.of(new Character[][]{
                        {'X', ' ', ' '},
                        {' ', ' ', ' '},
                        {'O', ' ', 'X'}}, Arrays.asList(new Integer[]{0, 1}, new Integer[]{1, 0},
                        new Integer[]{1, 2}, new Integer[]{2, 1})),
                Arguments.of(new Character[][]{
                        {'X', 'O', ' '},
                        {' ', ' ', ' '},
                        {' ', ' ', 'X'}}, Arrays.asList(new Integer[]{1, 0}, new Integer[]{1, 2}, new Integer[]{2, 1})),
                Arguments.of(new Character[][]{
                        {'X', 'O', 'O'},
                        {' ', ' ', 'X'},
                        {' ', ' ', 'X'}}, Arrays.asList(new Integer[]{1, 0}, new Integer[]{2, 1})),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {'X', ' ', 'O'},
                        {'O', ' ', 'X'}}, Arrays.asList(new Integer[]{0, 1}, new Integer[]{2, 1})),
                Arguments.of(new Character[][]{
                        {' ', 'X', 'O'},
                        {'X', ' ', ' '},
                        {'O', ' ', 'X'}}, Arrays.asList(new Integer[]{1, 2}, new Integer[]{2, 1}))
        );
    }

    @ParameterizedTest
    @MethodSource("emptySideMoveManyOptionsData")
    void emptySideMoveManyOptionsToChoose(Character[][] arrayInput, List<Integer[]> list) {
        ///given

        //when
        boolean flag = false;
        Integer[] randoms = WinningStrategy.emptySideMove(arrayInput);
        for (Integer[] element : list) {
            if (Arrays.deepEquals(element, randoms)) {
                flag = true;
                break;
            }
        }
        //then
        Assertions.assertTrue(flag);
    }

    public static Stream<Arguments> checkRowsAndColumnsToWinOrBlockData() {
        return Stream.of(
                Arguments.of(new Character[][]{
                        {'X', 'X', ' '},
                        {' ', 'O', ' '},
                        {'O', ' ', 'X'}}, 'X', new Integer[]{0, 2}),
                Arguments.of(new Character[][]{
                        {'O', 'O', ' '},
                        {'X', 'X', ' '},
                        {'O', 'X', 'X'}}, 'X', new Integer[]{1, 2}),
                Arguments.of(new Character[][]{
                        {'X', 'O', ' '},
                        {'X', 'O', ' '},
                        {' ', 'X', 'X'}}, 'X', new Integer[]{2, 0}),
                Arguments.of(new Character[][]{
                        {'X', 'O', ' '},
                        {'X', 'X', ' '},
                        {' ', 'O', 'O'}}, 'O', new Integer[]{2, 0}),
                Arguments.of(new Character[][]{
                        {'X', 'O', ' '},
                        {'O', ' ', ' '},
                        {'X', 'O', 'O'}}, 'O', new Integer[]{1, 1}),
                Arguments.of(new Character[][]{
                        {'X', 'O', ' '},
                        {'X', ' ', 'O'},
                        {' ', 'X', 'O'}}, 'O', new Integer[]{0, 2}),
                Arguments.of(new Character[][]{
                        {' ', 'X', 'O'},
                        {'X', ' ', 'O'},
                        {'O', ' ', 'X'}}, 'X', new Integer[]{-1, -1})
        );
    }

    @ParameterizedTest
    @MethodSource("checkRowsAndColumnsToWinOrBlockData")
    void checkRowsAndColumns(Character[][] arrayInput, Character symbol, Integer[] expectedOutput) {
        //given

        //when
        Integer[] actualOutput = WinningStrategy.checkRowsAndColumns(arrayInput, symbol);
        //then
        Assertions.assertEquals(expectedOutput[0], actualOutput[0]);
        Assertions.assertEquals(expectedOutput[1], actualOutput[1]);
    }

    public static Stream<Arguments> checkDiagonalsToWinOrBlockData() {
        return Stream.of(
                Arguments.of(new Character[][]{
                        {'X', 'X', 'O'},
                        {'O', ' ', ' '},
                        {'O', ' ', 'X'}}, 'X', new Integer[]{1, 1}),
                Arguments.of(new Character[][]{
                        {' ', 'O', ' '},
                        {'X', 'X', ' '},
                        {'O', 'X', 'X'}}, 'X', new Integer[]{0, 0}),
                Arguments.of(new Character[][]{
                        {'X', 'O', ' '},
                        {'X', 'X', 'O'},
                        {' ', 'X', ' '}}, 'X', new Integer[]{2, 2}),
                Arguments.of(new Character[][]{
                        {'X', 'X', 'O'},
                        {'X', 'O', ' '},
                        {' ', 'O', 'O'}}, 'O', new Integer[]{2, 0}),
                Arguments.of(new Character[][]{
                        {'X', 'O', 'O'},
                        {'O', ' ', ' '},
                        {'O', 'X', 'O'}}, 'O', new Integer[]{1, 1}),
                Arguments.of(new Character[][]{
                        {'X', 'O', ' '},
                        {'X', 'O', 'X'},
                        {'O', 'X', 'O'}}, 'O', new Integer[]{0, 2}),
                Arguments.of(new Character[][]{
                        {' ', 'X', 'O'},
                        {'X', ' ', 'O'},
                        {'O', ' ', 'X'}}, 'X', new Integer[]{-1, -1})
        );
    }

    @ParameterizedTest
    @MethodSource("checkDiagonalsToWinOrBlockData")
    void checkDiagonals(Character[][] arrayInput, Character symbol, Integer[] expectedOutput) {
        //given

        //when
        Integer[] actualOutput = WinningStrategy.checkDiagonals(arrayInput, symbol);
        //then
        Assertions.assertEquals(expectedOutput[0], actualOutput[0]);
        Assertions.assertEquals(expectedOutput[1], actualOutput[1]);
    }

    public static Stream<Arguments> findBranchingFieldData() {
        return Stream.of(
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {' ', ' ', 'X'},
                        {'X', ' ', 'O'}}, 'X', new Integer[]{1, 0}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {' ', ' ', 'O'},
                        {'X', 'O', 'X'}}, 'X', new Integer[]{0, 0}),
                Arguments.of(new Character[][]{
                        {' ', ' ', ' '},
                        {'O', ' ', 'O'},
                        {'X', ' ', 'X'}}, 'X', new Integer[]{1, 1}),
                Arguments.of(new Character[][]{
                        {' ', ' ', ' '},
                        {'O', ' ', ' '},
                        {'X', ' ', 'X'}}, 'X', new Integer[]{0, 2}),
                Arguments.of(new Character[][]{
                        {'O', ' ', ' '},
                        {'O', ' ', ' '},
                        {'X', ' ', ' '}}, 'O', new Integer[]{1, 1}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {'O', ' ', 'O'},
                        {'X', 'O', 'X'}}, 'X', new Integer[]{-1, -1}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {'X', ' ', 'O'},
                        {'O', ' ', 'X'}}, 'X', new Integer[]{-1, -1})
        );
    }

    @ParameterizedTest
    @MethodSource("findBranchingFieldData")
    void findBranchingField(Character[][] arrayInput, Character symbol, Integer[] expectedOutput) {
        //given

        //when
        Integer[] actualOutput = WinningStrategy.findBranchingField(arrayInput, symbol);
        //then
        Assertions.assertEquals(expectedOutput[0], actualOutput[0]);
        Assertions.assertEquals(expectedOutput[1], actualOutput[1]);
    }

    public static Stream<Arguments> isValidRowData() {
        return Stream.of(
                Arguments.of(new Character[][]{
                        {'X', 'X', 'O'},
                        {'O', ' ', ' '},
                        {'O', ' ', ' '}}, 'X', 1, false),
                Arguments.of(new Character[][]{
                        {' ', 'O', ' '},
                        {'X', ' ', ' '},
                        {'O', 'X', 'X'}}, 'X', 1, true),
                Arguments.of(new Character[][]{
                        {' ', 'O', ' '},
                        {'X', 'X', ' '},
                        {'O', 'X', ' '}}, 'O', 0, true),
                Arguments.of(new Character[][]{
                        {' ', 'O', ' '},
                        {'X', 'O', ' '},
                        {'O', 'X', ' '}}, 'O', 2, false),
                Arguments.of(new Character[][]{
                        {' ', 'X', 'O'},
                        {'X', 'O', 'O'},
                        {'O', ' ', 'X'}}, 'O', 2, false),
                Arguments.of(new Character[][]{
                        {' ', 'X', 'O'},
                        {'X', 'O', 'O'},
                        {'O', ' ', '0'}}, 'O', 0, false)
        );
    }

    @ParameterizedTest
    @MethodSource("isValidRowData")
    void isValidRow(Character[][] arrayInput, Character symbol, Integer row, Boolean expectedOutput) {
        //given

        //when
        Boolean actualOutput = WinningStrategy.isValidRow(arrayInput, symbol, row);
        //then
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    public static Stream<Arguments> isValidColumnData() {
        return Stream.of(
                Arguments.of(new Character[][]{
                        {'X', 'X', 'O'},
                        {'O', ' ', ' '},
                        {'O', ' ', ' '}}, 'X', 1, true),
                Arguments.of(new Character[][]{
                        {' ', 'O', ' '},
                        {'X', ' ', ' '},
                        {'O', 'X', 'X'}}, 'X', 1, false),
                Arguments.of(new Character[][]{
                        {' ', 'O', ' '},
                        {'X', 'X', ' '},
                        {'O', 'X', ' '}}, 'O', 0, false),
                Arguments.of(new Character[][]{
                        {' ', 'O', ' '},
                        {'X', 'O', 'O'},
                        {'O', 'X', ' '}}, 'O', 2, true),
                Arguments.of(new Character[][]{
                        {' ', 'X', 'O'},
                        {'X', 'O', ' '},
                        {'O', ' ', 'X'}}, 'O', 2, false),
                Arguments.of(new Character[][]{
                        {'O', 'X', 'O'},
                        {'X', 'O', 'O'},
                        {' ', ' ', '0'}}, 'O', 0, false)
        );
    }

    @ParameterizedTest
    @MethodSource("isValidColumnData")
    void isValidColumn(Character[][] arrayInput, Character symbol, Integer column, Boolean expectedOutput) {
        //given

        //when
        Boolean actualOutput = WinningStrategy.isValidColumn(arrayInput, symbol, column);
        //then
        Assertions.assertEquals(expectedOutput, actualOutput);
    }


    public static Stream<Arguments> isValidFirstDiagonalData() {
        return Stream.of(
                Arguments.of(new Character[][]{
                        {'X', 'X', 'O'},
                        {'O', ' ', ' '},
                        {'O', ' ', ' '}}, 'X', true),
                Arguments.of(new Character[][]{
                        {' ', 'O', ' '},
                        {'X', ' ', ' '},
                        {'O', 'X', 'X'}}, 'X', true),
                Arguments.of(new Character[][]{
                        {' ', 'O', ' '},
                        {'X', 'X', ' '},
                        {'O', 'X', ' '}}, 'X', true),
                Arguments.of(new Character[][]{
                        {' ', 'O', ' '},
                        {'X', 'O', ' '},
                        {'O', 'X', ' '}}, 'O', true),
                Arguments.of(new Character[][]{
                        {' ', 'X', 'O'},
                        {'X', 'O', 'O'},
                        {'O', ' ', 'X'}}, 'O', false),
                Arguments.of(new Character[][]{
                        {' ', 'X', 'O'},
                        {'X', 'O', 'O'},
                        {'O', ' ', '0'}}, 'O', false)
        );
    }

    @ParameterizedTest
    @MethodSource("isValidFirstDiagonalData")
    void isValidFirstDiagonal(Character[][] arrayInput, Character symbol, Boolean expectedOutput) {
        //given

        //when
        Boolean actualOutput = WinningStrategy.isValidFirstDiagonal(arrayInput, symbol);
        //then
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    public static Stream<Arguments> isValidSecondDiagonalData() {
        return Stream.of(
                Arguments.of(new Character[][]{
                        {'X', 'X', 'O'},
                        {'O', ' ', ' '},
                        {' ', ' ', ' '}}, 'O', true),
                Arguments.of(new Character[][]{
                        {' ', 'O', ' '},
                        {'X', ' ', ' '},
                        {'O', 'X', 'X'}}, 'O', true),
                Arguments.of(new Character[][]{
                        {' ', 'O', ' '},
                        {'X', 'X', ' '},
                        {' ', 'X', ' '}}, 'X', true),
                Arguments.of(new Character[][]{
                        {' ', 'X', 'O'},
                        {'X', 'O', 'O'},
                        {'O', ' ', 'X'}}, 'O', false),
                Arguments.of(new Character[][]{
                        {' ', 'X', 'O'},
                        {'X', ' ', 'O'},
                        {'O', ' ', '0'}}, 'O', false)
        );
    }

    @ParameterizedTest
    @MethodSource("isValidSecondDiagonalData")
    void isValidSecondDiagonal(Character[][] arrayInput, Character symbol, Boolean expectedOutput) {
        //given

        //when
        Boolean actualOutput = WinningStrategy.isValidSecondDiagonal(arrayInput, symbol);
        //then
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    public static Stream<Arguments> checkOptionTwoInRowOrColumnData() {
        return Stream.of(
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {' ', 'O', 'X'},
                        {'X', ' ', 'O'}}, 'X', new Integer[]{1, 0}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {'O', 'X', 'O'},
                        {'X', ' ', 'X'}}, 'X', new Integer[]{0, 1}),
                Arguments.of(new Character[][]{
                        {' ', 'X', ' '},
                        {'O', ' ', 'O'},
                        {'X', ' ', ' '}}, 'O', new Integer[]{0, 2}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'X'},
                        {'O', ' ', ' '},
                        {'X', ' ', 'X'}}, 'O', new Integer[]{1, 1}),
                Arguments.of(new Character[][]{
                        {'O', ' ', 'X'},
                        {'O', 'X', ' '},
                        {' ', 'O', ' '}}, 'O', new Integer[]{2, 0}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {'O', ' ', 'O'},
                        {'X', 'O', 'X'}}, 'X', new Integer[]{-1, -1}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {'X', ' ', 'O'},
                        {'O', ' ', 'X'}}, 'X', new Integer[]{-1, -1})
        );
    }

    @ParameterizedTest
    @MethodSource("checkOptionTwoInRowOrColumnData")
    void checkOptionTwoInRowOrColumn(Character[][] arrayInput, Character symbol, Integer[] expectedOutput) {
        //given

        //when
        Integer[] actualOutput = WinningStrategy.checkOptionTwoInRowOrColumn(arrayInput, symbol);
        //then
        Assertions.assertEquals(expectedOutput[0], actualOutput[0]);
        Assertions.assertEquals(expectedOutput[1], actualOutput[1]);
    }

    public static Stream<Arguments> checkOptionTwoInDiagonalData() {
        return Stream.of(
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {' ', ' ', 'X'},
                        {'X', ' ', 'O'}}, 'O', new Integer[]{1, 1}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {' ', 'X', 'O'},
                        {'X', 'O', ' '}}, 'X', new Integer[]{2, 2}),
                Arguments.of(new Character[][]{
                        {'O', ' ', ' '},
                        {'O', ' ', 'O'},
                        {'X', ' ', 'X'}}, 'X', new Integer[]{1, 1}),
                Arguments.of(new Character[][]{
                        {' ', ' ', ' '},
                        {'O', 'O', ' '},
                        {' ', ' ', 'X'}}, 'O', new Integer[]{2, 0}),
                Arguments.of(new Character[][]{
                        {'O', ' ', ' '},
                        {'O', ' ', ' '},
                        {'X', ' ', ' '}}, 'O', new Integer[]{1, 1}),
                Arguments.of(new Character[][]{
                        {'X', ' ', 'O'},
                        {'O', ' ', 'O'},
                        {'X', 'O', 'X'}}, 'X', new Integer[]{-1, -1}),
                Arguments.of(new Character[][]{
                        {' ', ' ', 'O'},
                        {'X', 'X', 'O'},
                        {'O', ' ', 'X'}}, 'X', new Integer[]{-1, -1})
        );
    }

    @ParameterizedTest
    @MethodSource("checkOptionTwoInDiagonalData")
    void checkOptionTwoInDiagonal(Character[][] arrayInput, Character symbol, Integer[] expectedOutput) {
        //given

        //when
        Integer[] actualOutput = WinningStrategy.checkOptionTwoInDiagonal(arrayInput, symbol);
        //then
        Assertions.assertEquals(expectedOutput[0], actualOutput[0]);
        Assertions.assertEquals(expectedOutput[1], actualOutput[1]);
    }
}
