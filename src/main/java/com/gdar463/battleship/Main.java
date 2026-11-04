package com.gdar463.battleship;

import com.gdar463.battleship.logging.Logger;
import com.gdar463.battleship.model.Field;
import com.gdar463.battleship.service.CellSunker;
import com.gdar463.battleship.service.FieldPrinter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Creo uno scanner per leggere dalla console
            Scanner stdin = new Scanner(System.in);

            String path;
            // Ottengo il percorso al file csv dagli argomenti passati al programma, se presenti,
            // se no chiedo all'utente di inserirlo
            if (args.length == 1) {
                Logger.INSTANCE.debug("Usando path da args");
                path = args[0];
            } else {
                Logger.INSTANCE.debug("Usando path da console");
                System.out.print("Inserisci il path al csv del campo di battaglia: ");
                path = stdin.next();
            }
            Logger.INSTANCE.info("Usando file '" + path + "'");
            System.out.println();

            // Creo il campo
            Field field = new Field(path);

            // Stampo il campo pre-attacco
            System.out.println("=== Campo pre-attacco");
            System.out.println(FieldPrinter.fieldToString(field));

            // Ottengo la cella da colpire
            System.out.print("Indica la coordinata x della cella da colpire: ");
            int x = stdin.nextInt();
            System.out.print("Indica la coordinata y della cella da colpire: ");
            int y = stdin.nextInt();
            CellSunker.sunkCell(field, x - 1, y - 1);
            System.out.println();

            System.out.println("=== Campo post-attacco");
            // Stampo il campo dopo-attacco
            System.out.println(FieldPrinter.fieldToString(field));
        } catch (Exception e) {
            Logger.INSTANCE.error(e.getMessage());
        }
    }
}