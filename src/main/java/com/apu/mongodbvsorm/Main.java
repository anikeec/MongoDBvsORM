/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm;

import com.apu.mongodbvsorm.dao.NotebookEntityDAO;
import com.apu.mongodbvsorm.menu.MainMenuState;
import com.apu.mongodbvsorm.menu.MenuState;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author apu
 */
public class Main {
    
    public static final String DB_HOST = "127.0.0.1";
    public static final int DB_PORT = 27017;
    public static final String DB_NAME = "NotebookDB";
    
    public static void main(String[] args) {
       
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
        
    }
    
    private static void init() {
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
        MongoClient mongoClient;
        mongoClient = new MongoClient(new ServerAddress(DB_HOST, DB_PORT), mongoOptions);
        
        final Morphia morphia = new Morphia();
        morphia.mapPackage("com.apu.mongodbvsorm.entities");
        final Datastore datastore = morphia.createDatastore(new MongoClient(), "notebookDB");        
        NotebookEntityDAO.init(datastore);
    }
    
}
