package com.gdar463.battleship.logging;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.FileHandler;

public class Logger {
    // Sfrutto un sigleton per unificare il file di log
    public static final Logger INSTANCE = new Logger("batteship.log");

    java.util.logging.Logger logger = java.util.logging.Logger.getLogger("Battleship");
    FileHandler handle;

    /**
     * Costruttore privato a partire dal path del file di log
     * @param path percorso per file di log
     */
    private Logger(String path) {
        // Configuro il Logger ad usare il file
        try {
            this.handle = new FileHandler(path);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.err.println(Arrays.toString(e.getStackTrace()));
            System.exit(1);
        }
        logger.addHandler(this.handle);

        // Disabilito la standard output di default del Logger
        logger.setUseParentHandlers(false);
    }

    /**
     * Logga messaggio a livello FINE
     * @param msg messaggio
     */
    public void debug(String msg) {
        logger.fine(msg);
    }
    /**
     * Logga messaggio a livello INFO e allo standard output
     * @param msg messaggio
     */
    public void info(String msg) {
        logger.info(msg);
        System.out.println(msg);
    }
    /**
     * Logga messaggio a livello SEVERE e allo standard error
     * @param msg messaggio
     */
    public void error(String msg) {
        logger.severe(msg);
        System.err.println(msg);
    }
}
