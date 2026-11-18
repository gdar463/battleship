package com.gdar463.battleship.model;

public interface CsvSerializable<T> {
    T fromCsv(String file);
    String toCsv();
}
