package com.gdar463.battleship.model;

import com.gdar463.battleship.service.CsvReader;
import com.gdar463.battleship.service.ShipDetector;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Rappresenta il campo di battaglia, ovvero le celle e le navi
 */
public class Field {
    int x, y;
    List<Cell[]> cells;
    List<Ship> ships;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }
//    /**
//     * Costruttore a partire dal percorso del file csv
//     * @param path percorso al file csv
//     */
//    public Field(List<Cell[]> cells) {
//        this.cells = cells;
//    }
//    /**
//     * Costruttore a partire dalle le liste già pronte
//     * @param cells lista di celle
//     * @param ships lista di navi
//     */
//    public Field(List<Cell[]> cells, List<Ship> ships) {
//        this.cells = cells;
//        this.ships = ships;
//    }


    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    /**
     * Ottiene le celle
     * @return lista di celle
     */
    public List<Cell[]> getCells() {
        return cells;
    }
    /**
     * Ottiene le navi
     * @return lista di navi
     */
    public List<Ship> getShips() {
        return ships;
    }
    public void setCells(List<Cell[]> cells) {
        this.cells = cells;
    }
    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }
}
