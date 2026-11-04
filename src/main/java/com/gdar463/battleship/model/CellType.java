package com.gdar463.battleship.model;

import java.util.Objects;

/**
 * Rappresenta il tipo di ogni cella
 */
public enum CellType {
    ACQUA("A"),
    NAVE("N"),
    COLPITA("C"),
    MANCATO("M");

    /**
     * Indica la lettera usata nella rappresentazione a csv
     */
    private final String letter;

    /**
     * Costruttore privato per inizializzare letter
     * @param c carattere rappresentato nel csv
     */
    CellType(String c) {
        this.letter = c;
    }

    /**
     * Trasforma la lettera nel corretto CellType
     * @param c carattere rappresentato nel csv
     * @return CellType corrispondente al carattere
     * @throws RuntimeException se c non corrisponde ad alcun CellType
     */
    public static CellType fromLetter(String c) throws RuntimeException {
        for (CellType type: CellType.values()) {
            if (Objects.equals(type.letter, c)) {
                return type;
            }
        }
        throw new RuntimeException("Carattere '" + c + "' non trovato in CellType");
    }

    /**
     * Getter per la lettera corrispondente al tipo
     * @return carattere corrispondente al tipo
     */
    public String getLetter() {
        return letter;
    }
}
