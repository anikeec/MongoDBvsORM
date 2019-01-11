/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm.utils.logger;

import com.apu.mongodbvsorm.utils.storage.FileStorage;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import org.apache.commons.lang.exception.ExceptionUtils;

/**
 *
 * @author apu
 */
public class LoggingThread extends Thread {

    FileStorage<String> logStorage = new FileStorage<>();
    
    private final BlockingQueue<LogItem> logQueue;

    public LoggingThread(BlockingQueue<LogItem> logQueue) {
        this.logQueue = logQueue;
        this.init();
    }
    
    private void init() {
        logStorage.setFileName("log.txt");
        this.setDaemon(true);
        this.setName("LoggingThread");
    }
    
    @Override
    public void run() {
        while(Thread.currentThread().isInterrupted() == false) {
            try {
                LogItem logItem = logQueue.take();
                switch(logItem.getType()) {
                    case DEBUG:
                                logStorage.add("DEBUG: " + logItem.getValue());
                                break;
                    case INFO:
                                logStorage.add("INFO: " + logItem.getValue());
                                break;
                    case WARN:
                                logStorage.add("WARN: " + logItem.getValue());
                                break;
                    case ERROR:
                                logStorage.add("ERROR: " + logItem.getValue());
                                break;
                    case TRACE:
                                logStorage.add("TRACE: " + logItem.getValue());
                                break;
                    default:
                                break;
                }
            } catch (InterruptedException | IOException ex) {
            }
        }
    }
    
    
}
