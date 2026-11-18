package com.gdar463.battleship.database;

import com.gdar463.battleship.model.CsvSerializable;
import com.gdar463.battleship.model.Game;
import com.gdar463.battleship.model.User;

public enum BucketType {
    USER("user", User.class),
    GAME("game", Game.class);

    private final String path;
    private final Class<? extends CsvSerializable<?>> clazz;
    private int count = 0;

    BucketType(String path, Class<? extends CsvSerializable<?>> clazz) {
        this.path = path;
        this.clazz = clazz;
    }

    public String getPath() {
        return path;
    }
    public Class<? extends CsvSerializable<?>> getClazz()  { return clazz;}
    public int getCount() {
        return count;
    }
    public void increaseCount() {
        this.count++;
    }
}
