package com.gdar463.battleship.database;

public abstract class DbObject<T> {
    protected String id;

    abstract public T fromCsv(String file, String id);

    abstract public String toCsv();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
