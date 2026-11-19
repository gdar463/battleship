package com.gdar463.battleship.database;

import com.gdar463.battleship.model.DbObject;
import com.gdar463.battleship.model.Game;
import com.gdar463.battleship.model.User;

public enum BucketType {
    USER("user", User.class),
    GAME("game", Game.class);

    private final String path;
    private final Class<? extends DbObject<?>> clazz;
    private int nextId = 0;

    BucketType(String path, Class<? extends DbObject<?>> clazz) {
        this.path = path;
        this.clazz = clazz;
    }

    public String getPath() {
        return path;
    }
    public Class<? extends DbObject<?>> getClazz()  { return clazz;}
    public int getNextId() {
        return nextId;
    }
    public void setNextId(int nextId) {
        this.nextId = nextId;
    }
    public void increaseId() {
        this.nextId++;
    }
}
