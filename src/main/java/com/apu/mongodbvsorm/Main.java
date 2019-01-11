/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm;

import com.apu.mongodbvsorm.dao.NotebookEntityDAO;
import com.apu.mongodbvsorm.menu.MainMenuState;
import com.apu.mongodbvsorm.menu.MenuState;
import com.apu.mongodbvsorm.utils.Logger;
import com.apu.mongodbvsorm.utils.Settings;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import java.io.IOException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author apu
 */
public class Main {
    
    private static Logger LOGGER = Logger.getInstance();
    
    public static void main(String[] args) {
        try {
            Main.init();
            String ret = null;
            MenuState state = MainMenuState.getInstance();
            MenuState.setCurrentState(state);
            MenuState.setPreviousState(state);
            while(true) {
                ret = MenuState.getCurrentState().handle();
                if(ret != null) {
                    if(ret.equals(MenuState.RET_EXIT))
                        return;
                }
            }
        } catch (Exception ex) {
            LOGGER.error(Main.class, ExceptionUtils.getStackTrace(ex));
        }      
    }
    
    private static void init() throws IOException {
        
        String dbHost = Settings.loadSettingFromFile("database.host");
        String dbPortStr = Settings.loadSettingFromFile("database.port");        
        String dbName = Settings.loadSettingFromFile("database.name");
        int dbPort = Integer.parseInt(dbPortStr);
        
        MongoClientOptions mongoOptions = MongoClientOptions.builder()
                // Wait 1m for a query to finish, https://jira.mongodb.org/browse/JAVA-1076
            .socketTimeout(60000) 
                // Try the initial connection for 15s, http://blog.mongolab.com/2013/10/do-you-want-a-timeout/
            .connectTimeout(15000) 
                // Keep idle connections for 10m, so we discard failed connections quickly
            .maxConnectionIdleTime(600000) 
                // Read from the primary, if not available use a secondary
            .readPreference(ReadPreference.primaryPreferred()) 
            .build();
        ServerAddress address = new ServerAddress(dbHost, dbPort);
        try (MongoClient mongoClient = new MongoClient(address, mongoOptions)) {        
            final Morphia morphia = new Morphia();
            morphia.mapPackage("com.apu.mongodbvsorm.entities");
            final Datastore datastore = morphia.createDatastore(mongoClient, dbName);        
            NotebookEntityDAO.init(datastore);
        }
        LOGGER.debug(Main.class, "Database connected - " + dbHost + " : " + dbPort);
    }
    
}
