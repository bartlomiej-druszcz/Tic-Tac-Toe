package org.example.player;

import java.util.Map;

public interface DAOInterface<T, U> {

     void create();

    Map<T, U> read();

    void update();

    void delete(U u);
}
