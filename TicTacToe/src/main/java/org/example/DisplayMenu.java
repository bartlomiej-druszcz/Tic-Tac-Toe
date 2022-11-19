package org.example;

import java.util.Scanner;

public class DisplayMenu {
    public static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println();
    }
    public static void printMenuStart() {
        clearConsole();
        System.out.println("===========MENU============");
        System.out.println("1. Register");
        System.out.println("2. Sign in");
        System.out.println("3. Play now");
        System.out.println("4. Exit");
        System.out.println("===========================");
        System.out.println();
    }

    public static void printMenuPlayer() {
        clearConsole();
        System.out.println("=======PLAYER MENU============");
        System.out.println("1. Play with computer");
        System.out.println("2. Play with user");
        System.out.println("3. Show Statistic");
        System.out.println("4. Change Password");
        System.out.println("5. Exit");
        System.out.println("==============================");
        System.out.println();
    }

    public static String chooseLevel() {
        clearConsole();
        System.out.println("======LEVEL=======");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        System.out.println("==================");
        System.out.print("\nSelect level -> ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!(input.equals("1") || input.equals("2") || input.equals("3"))) {
            System.out.print("\nSelect level -> ");
            input = scanner.nextLine();
        }
        return input;
    }

    public static void pressEnter(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
