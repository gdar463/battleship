package com.gdar463.battleship.model;

import java.util.List;

/**
 * Rappresenta una nave
 */
public class Ship {
    List<Cell> cells;

    /**
     * Costruttore a partire dalle celle
     * @param cells le celle che rappresentano la nave
     */
    public Ship(List<Cell> cells) {
        this.cells = cells;
    }

    /**
     * Controlla se tutte le celle sono state colpite
     * @return vero se la nave è affondata, se no falso
     */
    public boolean isSunk() {
        for (Cell cell: cells) {
            if (cell.getType() != CellType.COLPITA) {
                return false;
            }
        }
        return true;
    }
}
