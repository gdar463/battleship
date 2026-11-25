package com.gdar463.battleship.database;

public abstract class DbObject<T> {
    protected int id = -1;

    abstract public T fromCsv(String file, int id);
    abstract public String toCsv();

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
