/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm.utils;

import com.apu.mongodbvsorm.utils.logger.LogItem;
import com.apu.mongodbvsorm.utils.logger.LogType;
import com.apu.mongodbvsorm.utils.logger.LoggingThread;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Anikeichyk Pavel
 * @email  pasha_anik@ukr.net
 * 
 */
public class Logger {    
    private static Logger instance;
    
    private static final int LOGGING_QUEUE_SIZE = 1000;
    private BlockingQueue<LogItem> logQueue;
    
    private Logger() {
        this.init();
    }
    
    public static Logger getInstance() {
        if(instance == null)    
            instance = new Logger();
        return instance;
    }
    
    private void init() {
        logQueue = new ArrayBlockingQueue<>(LOGGING_QUEUE_SIZE);
        new LoggingThread(logQueue).start();
    }
    
    public synchronized void info(Class className, String str) {
        putToLog(new LogItem(LogType.INFO, "(" + className.getName() + ") - " + str));
    }
    
    public synchronized void debug(Class className, String str) {
        putToLog(new LogItem(LogType.DEBUG, "(" + className.getName() + ") - " + editString(str)));
    }
    
    public synchronized void warn(Class className, String str) {
        putToLog(new LogItem(LogType.WARN, "(" + className.getName() + ") - " + str));
    }
    
    public synchronized void error(Class className, String str) {
        putToLog(new LogItem(LogType.ERROR, "(" + className.getName() + ") - " + str));
    }
    
    public synchronized void trace(Class className, String str) {
        putToLog(new LogItem(LogType.TRACE, "(" + className.getName() + ") - " + str));
    }
    
    private void putToLog(LogItem logItem) {
        try {
            logQueue.put(logItem);
        } catch (InterruptedException ex) {            
        }
    }
    
    private String editString(String str) {
        int index = 0;
        int amount = 0;
        while(true) {
            index = str.indexOf("\r\n", index + 1);
            if(index == (-1)) break;
            amount++;
        }
        if(amount<2) {
            return str.replaceAll("\r\n", "");
        }
        return str;
    }
    
}
