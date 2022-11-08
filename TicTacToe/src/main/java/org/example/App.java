package org.example;

import java.util.Arrays;

public class App {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[35m";

    public static void main(String args[]) {
        char[][] a = new char[][]{{'a', 'a'},
                {'a', 'a'}};
        char[][] b = new char[][]{{'b', 'b'},
                {'b', 'b'}};

        char[][][][] c = new char[][][][]{{a, b}, {b, a}};

        for (char[][][] chars : c) {
            for (char[][] aChar : chars) {
                System.out.print(Arrays.deepToString(aChar) + " ");
            }
            System.out.println();
        }

        System.out.println(" _________" + "  _________ " + " _________");
        System.out.println("|         |" + "|   ___   |" + "|         |");
        System.out.println("|  \\   /  |" + "|  /   \\  |" + "|  \\   /  |");
        System.out.println("|   \\ /   |" + "|  |   |  |" + "|   \\ /   |");
        System.out.println("|   / \\   |" + "|  |   |  |" + "|   / \\   |");
        System.out.println("|  /   \\  |" + "|  \\___/  |" + "|  /   \\  |");
        System.out.println("|_________|" + "|_________|" + "|_________|");

//        System.out.println();

        System.out.println(ANSI_YELLOW + " _________ " + " _________ " + " _________ ");
        System.out.println("|         |" + "|         |" + "|         |");
        System.out.println("|         |" + "|         |" + "|         |");
        System.out.println("|         |" + "|         |" + "|         |");
        System.out.println("|         |" + "|         |" + "|         |");
        System.out.println("|         |" + "|         |" + "|         |");
        System.out.println("|_________|" + "|_________|" + "|_________|" + ANSI_RESET);

//        System.out.println();

        System.out.println(" _________ ");
        System.out.println("|   ___   |");
        System.out.println("|  /   \\  |");
        System.out.println("|  |   |  |");
        System.out.println("|  |   |  |");
        System.out.println("|  \\___/  |");
        System.out.println("|_________|");


        System.out.println("");
        System.out.println("\u001B[35m" + "Bartek" + ANSI_RESET);
        System.out.println("");
    }
}
