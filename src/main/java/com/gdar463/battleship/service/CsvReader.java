package com.gdar463.battleship.service;

import com.gdar463.battleship.logging.Logger;
import com.gdar463.battleship.model.Cell;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Servizio dedicato alla lettura del campo di battaglia da file csv
 */
public class CsvReader {
    /**
     * Legge il file csv indicato dal path e ritorna il campo di battaglia
     * @param path percorso al file csv
     * @return la matrice rappresentante del campo di battaglia
     * @throws FileNotFoundException se path non corrisponde ad alcun file
     */
    public static List<Cell[]> readCsv(String path) throws FileNotFoundException {
        ArrayList<Cell[]> matrix = new ArrayList<>();

        try {
            Logger.INSTANCE.debug("Tentativo ad aprire il file '" + path + "'");
            // Apro il file
            Scanner scanner = new Scanner(new File(path));

            Logger.INSTANCE.debug("Lettura campo da file...");
            // Leggo ogni riga
            while (scanner.hasNext()) {
                // Divido le singole celle...
                String[] row = scanner.nextLine().split(",");
                Cell[] rowCells = new Cell[row.length];
                // ... e creo le celle
                for (int i = 0; i < row.length; i++) {
                    rowCells[i] = new Cell(row[i]);
                }
                matrix.add(rowCells);
            }

            Logger.INSTANCE.debug("Finito di leggere file");
            // Chiudo il file quando ho finito di lavorarci sopra
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File '" + path + "' non trovato.");
        }

        return matrix;
    }
}
