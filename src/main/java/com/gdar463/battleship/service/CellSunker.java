package com.gdar463.battleship.service;

import com.gdar463.battleship.logging.Logger;
import com.gdar463.battleship.model.Cell;
import com.gdar463.battleship.model.CellType;
import com.gdar463.battleship.model.Field;
import com.gdar463.battleship.model.Ship;

import java.util.List;

/**
 * Servizio dedicato a colpire le celle e, se necessario, affondare le navi
 */
public class CellSunker {
    /**
     * Affonda la cella richiesta
     * @param field campo di battaglia
     * @param x     riga della cella, a partire da 0
     * @param y     colonna della cella, a partire da 0
     * @throws RuntimeException se la cella richiesta era già stata colpita
     */
    public static void sunkCell(Field field, int x, int y) throws RuntimeException {
        // Ottengo la cella richiesta
        Cell cell = field.getCells().get(y)[x];

        // Controllo se era acqua
        if (cell.getType() == CellType.ACQUA) {
            cell.setType(CellType.MANCATO);
            Logger.INSTANCE.info("Mancato");
            // Controllo se era una nave
        } else if (cell.getType() == CellType.NAVE) {
            cell.setType(CellType.COLPITA);
            Logger.INSTANCE.info("Colpita!");

            // Controllo se la nave è stata affondata
            if (cell.getShip().isSunk()) {
                Logger.INSTANCE.info("Nave affondata!");

                // Scorro tutte le navi e controllo se ce ne sono ancora in piedi
                List<Ship> ships = field.getShips();
                for (Ship ship : ships) {
                    if (!ship.isSunk()) {
                        return;
                    }
                }
                // Se tutte sono affondate notifico l'utente
                Logger.INSTANCE.info("Vittoria. Tutte le navi sono state affondate!");
            }
            // Se la cella è già stata colpita (COLPITA o MANCATO), tiro un eccezione
        } else {
            throw new RuntimeException("Cella già colpita");
        }
    }
}
