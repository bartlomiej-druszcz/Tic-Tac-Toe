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

public class PlayerDAO implements DAOInterface {
    private HashMap<String, Statistic> listPlayerStatistic = new HashMap<>();
    private HashMap<String, String> listPlayerData = new HashMap<>();


    @Override
    public void create() throws IOException {
        PlayerRegister playerRegister = new PlayerRegister();
        listPlayerStatistic.put(playerRegister.getLogin(), playerRegister.getStatistic());
        listPlayerData.put(playerRegister.getLogin(), playerRegister.getPassword());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("player.json")) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<String, Statistic> getAll() {
        return listPlayerStatistic;
    }

    @Override
    public HashMap<String, String> getAllLogin() {
        return listPlayerData;
    }


    public static String getLogin() {
        System.out.println("Sing in: ");
        System.out.print(" Enter your login  => ");
        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();
        return login;

    }

    public static String getPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(" Enter your password  => ");
        String password = scanner.nextLine();
        return password;
    }



    @Override
    public void readAllPlayer() {
        System.out.println("Player [login] -> Statistic ");
        Gson gson = new Gson();
        try (Reader reader = new FileReader("player.json")) {
            PlayerDAO staff = gson.fromJson(reader, this.getClass());
            for (Map.Entry<String, Statistic> entry : staff.listPlayerStatistic.entrySet()) {
                System.out.println(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void update() {
        String login = getLogin();
        String password = getPassword();

        for (Map.Entry<String, String> entry : listPlayerData.entrySet()) {
            if (entry.getKey().equals(login) && entry.getValue().equals(password)) {
                Scanner scanner = new Scanner(System.in);
                String newPassword = scanner.nextLine();
                System.out.print("Enter new password =>");
                listPlayerData.put(login, newPassword);
                break;
            }
        }
    }
}













