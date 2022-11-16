package org.example.player;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

public interface DAOInterface<T> {

    void create() throws IOException;
    void update();
    HashMap<String, Statistic> getAll();
    HashMap<String, String> getAllLogin();


    void readAllPlayer();






}
