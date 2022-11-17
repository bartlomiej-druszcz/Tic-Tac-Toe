package org.example.player;

import java.io.IOException;
import java.util.HashMap;

public interface DAOInterface<T> {

    void create() throws IOException;

    void update();

    HashMap<String, Statistic> getAll();

    HashMap<String, String> getAllLogin();

    void readAllPlayer();
}
