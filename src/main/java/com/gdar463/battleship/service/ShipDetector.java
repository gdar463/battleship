package com.gdar463.battleship.service;

import com.gdar463.battleship.logging.Logger;
import com.gdar463.battleship.model.Cell;
import com.gdar463.battleship.model.CellType;
import com.gdar463.battleship.model.Ship;

import java.util.ArrayList;
import java.util.List;

/**
 * Servizio dedicato a trovare le navi presenti in un campo di battaglia
 */
public class ShipDetector {
    /**
     * Trova le navi presenti nel campo di battaglia e le ritorna
     * @param field campo di battaglia
     * @return lista di navi presenti nel field
     */
    public static List<Ship> getShips(List<Cell[]> field) {
        ArrayList<Ship> out = new ArrayList<>();
        Logger.INSTANCE.debug("Trovando navi in campo...");

        // Scorro ogni riga
        for (int i = 0; i < field.size(); i++) {
            Cell[] currentRow = field.get(i);
            // Scorro ogni colonna
            for (int j = 0; j < currentRow.length; j++) {
                // Controllo se la cella appartiene già ad una nave, in caso continuo
                if (currentRow[j].getShip() != null) {
                    continue;
                }
                // Controllo se la cella è una nave
                if (isShip(currentRow[j])) {
                    ArrayList<Cell> cells = new ArrayList<>();
                    cells.add(currentRow[j]);

                    // Controllo se la nave è più lunga di 1 e orrizontale...
                    if ((j + 1) < currentRow.length && isShip(currentRow[j + 1])) {
                        j++;
                        // Aggiungo alla futura nave ogni cella che ne rappresenta una
                        do {
                            cells.add(currentRow[j]);
                            j++;
                        } while (j < currentRow.length && isShip(currentRow[j]));
                        // ... oppure se è verticale
                    } else if ((i + 1) < field.size() && isShip(field.get(i + 1)[j])) {
                        // Salvo la i prima di modificarla per evitare di saltare parti di riga
                        int prevI = i;
                        i++;
                        // Aggiungo alla futura nave ogni cella che ne rappresenta una
                        do {
                            cells.add(field.get(i)[j]);
                            i++;
                        } while (i < field.size() && isShip(field.get(i)[j]));
                        i = prevI;
                    }

                    Ship ship = new Ship(cells);
                    out.add(ship);
                    // Per ogni cella setto la nave di appartenza
                    for (Cell cell : cells) {
                        cell.setShip(ship);
                    }
                }
            }
        }
        Logger.INSTANCE.debug("Trovato " + out.size() + " navi");
        return out;
    }

    /**
     * Metodo d'utilità che controlla se la cella corrisponde ad una nave, anche se già colpita
     * @param cell cella da controllare
     * @return vero se la cella è una nave, se no falso
     */
    private static boolean isShip(Cell cell) {
        return cell.getType() == CellType.NAVE || cell.getType() == CellType.COLPITA;
    }
}
