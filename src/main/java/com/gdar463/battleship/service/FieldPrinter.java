package com.gdar463.battleship.service;

import com.gdar463.battleship.model.Cell;
import com.gdar463.battleship.model.Field;

/**
 * Servizio dedicato ad ottenere una formattazione user-friendly del campo di battaglia
 */
public class FieldPrinter {
    /**
     * Converte il campo di battaglia ad una stringa formattata dell'intero campo
     * @param field campo di battaglia
     * @return rappresentazione formattata del campo
     */
    public static String fieldToString(Field field) {
        // Creo tutte le informazioni che mi servono per la formattazione
        StringBuilder builder = new StringBuilder();
        int cols = field.getCells().size();
        int rows = field.getCells().getFirst().length;
        int colsDigits = Math.ceilDiv(cols, 10);
        int rowsDigits = Math.ceilDiv(rows, 10);
        String colsFormat = "%0" + colsDigits + "d ";
        String rowsFormat = "%0" + rowsDigits + "d";

        // Angolo vuoto in alto a sinistra
        builder.repeat(" ", rowsDigits + 1);
        // Numero di colonna
        for (int i = 0; i < cols; i++) {
            builder.append(colsFormat.formatted(i + 1));
        }
        builder.append("\n");

        // Per ogni riga...
        for (int i = 0; i < rows; i++) {
            // Numero di riga
            builder.append(rowsFormat.formatted(i + 1));
            Cell[] row = field.getCells().get(i);
            // e ogni cella assicurandomi di mantermi in linea con il numero delle colonne
            for (int j = 0; j < rows; j++) {
                builder.repeat(" ", colsDigits).append(row[j].getLetter());
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
