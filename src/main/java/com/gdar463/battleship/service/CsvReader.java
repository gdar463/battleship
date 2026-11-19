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
     * Legge il campo di battaglia in formato csv e ritorna il campo di battaglia
     * @param contents campo di battaglia in formato csv
     * @return la matrice rappresentante del campo di battaglia
     */
    public static List<Cell[]> readCsv(String[] contents) {
        ArrayList<Cell[]> matrix = new ArrayList<>();

        for (String entire_row: contents) {
            String[] row = entire_row.split(",");
            Cell[] rowCells = new Cell[row.length];
            for (int i = 0; i < row.length; i++) {
                rowCells[i] = new Cell(row[i]);
            }
            matrix.add(rowCells);
        }

        return matrix;
    }
}
