package by.sem3.lab6;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {
    private FileWriter logger;

    public Logger() throws LoggerException {
        try {
            this.logger = new FileWriter("out/logfile.txt");
        } catch (IOException e) {
            throw new LoggerException(e);
        }
    }

    public Logger(String logpath) throws LoggerException {
        try {
            this.logger = new FileWriter(logpath);
        } catch (IOException e) {
            throw new LoggerException(e);
        }
    }

    public void write(String log) throws LoggerException {
        try {
            logger.write(new Date() + "\t" + log + "\n");
        } catch (IOException e) {
            throw new LoggerException(e);
        }
    }

    public void flush() throws LoggerException {
        try {
            logger.flush();
        } catch (IOException e) {
            throw new LoggerException(e);
        }
    }

    public void close() throws LoggerException {
        try {
            logger.close();
        } catch (IOException e) {
            throw new LoggerException(e);
        }
    }
}
