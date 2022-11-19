package org.example.player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PlayerDAO implements DAOInterface<String, PlayerRegister> {
    private Map<String, PlayerRegister> listPlayerRegister = new HashMap<>();

    @Override
    public void create() {
        Scanner scannerString = new Scanner(System.in);
        System.out.print(" Enter your login  => ");
        String login = scannerString.nextLine();

        System.out.print(" Enter your password  => ");
        String password = scannerString.nextLine();

        System.out.print(" Choose your sign X or O  => ");
        String figure = scannerString.nextLine();
        while (!figure.equals("X") && !figure.equals("O")) {
            System.out.print(" Wrong choice, try again X or O  => ");
            figure = scannerString.nextLine();
        }
        listPlayerRegister.put(login, new PlayerRegister(login, password, figure, new Statistic()));
        update();
    }

    @Override
    public Map<String, PlayerRegister> read() {
        Gson gson = new Gson();
        PlayerDAO playerDAO;
        try (Reader reader = new FileReader("player.json")) {
            playerDAO = gson.fromJson(reader, this.getClass());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new HashMap<>();
        }
        return playerDAO.listPlayerRegister;
    }

    @Override
    public void update() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("player.json")) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(PlayerRegister playerRegister) {
        listPlayerRegister.remove(playerRegister.getLogin());
        update();
    }

    public static String getLogin() {
        System.out.print(" Enter your login  => ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String getPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(" Enter your password  => ");
        return scanner.nextLine();
    }

    public void setListPlayerRegister(Map<String, PlayerRegister> listPlayerRegister) {
        this.listPlayerRegister = listPlayerRegister;
    }

    public Map<String, PlayerRegister> getListPlayerRegister() {
        return listPlayerRegister;
    }
}
