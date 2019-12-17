package com.musica.dl;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogError {
    private static Logger logger;
    private static FileHandler file;
    private static SimpleFormatter format;

    static {
        try {
            logger = Logger.getLogger("Errores");
            format = new SimpleFormatter();
            file = new FileHandler("ArchivoErrores.log");
            file.setFormatter(format);
            logger.addHandler(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        LogError.logger = logger;
    }
}
