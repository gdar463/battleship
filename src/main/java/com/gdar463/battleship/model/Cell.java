package com.gdar463.battleship.model;

/**
 * Rappresenta la singola cella del campo
 */
public class Cell {
    CellType type;
    /**
     * Riferimento alla nave di appartenza, null se non rappresenta una nave
     * TODO: refactor per eliminare doppia reference
     */
    Ship ship;

    /**
     * Costruttore in base al carattere
     * @param c carattere rappresentato nel csv
     */
    public Cell(String c) {
        this.type = CellType.fromLetter(c);
    }

    /**
     * Ottiene la lettera corrispondente al tipo della cella
     * @return il carrattere corrispondente al tipo
     */
    public String getLetter() {
        return type.getLetter();
    }

    /**
     * Ottiene il tipo della cella
     * @return il tipo della cella
     */
    public CellType getType() {
        return type;
    }
    /**
     * Setta il tipo della cella
     * @param type il nuovo tipo della cella
     */
    public void setType(CellType type) {
        this.type = type;
    }
    /**
     * Ottiene la nave di appartenza, se esiste
     * @return nave di appartenza
     */
    public Ship getShip() {
        return ship;
    }
    /**
     * Setta la nave di appartenza, se esiste
     * @param ship nave di appartenza
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
