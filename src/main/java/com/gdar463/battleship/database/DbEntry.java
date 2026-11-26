package com.gdar463.battleship.database;

public class DbEntry<T extends DbObject<?>> {
    T obj;
    String id;

    public DbEntry(String id, T obj) {
        this.id = id;
        this.obj = obj;
    }

    public String getId() {
        return id;
    }

    public T getObj() {
        return obj;
    }
}
