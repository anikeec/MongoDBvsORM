/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm.utils.storage;

import com.apu.mongodbvsorm.dao.NotebookEntityDAO;
import com.apu.mongodbvsorm.utils.Logger;
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
public class DatabaseStorage {
    
    private static Logger LOGGER = Logger.getInstance();
    
    private MongoClient mongoClient;
    
    public void connect(String dbHost, int dbPort, String dbName) {
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
        mongoClient = new MongoClient(address, mongoOptions);        
        final Morphia morphia = new Morphia();
        morphia.mapPackage("com.apu.mongodbvsorm.entities");
        final Datastore datastore = morphia.createDatastore(mongoClient, dbName);        
        NotebookEntityDAO.init(datastore);
        LOGGER.debug(DatabaseStorage.class, "Database connected - " + dbHost + " : " + dbPort);
    }
    
    public void disconnect() {
        if(mongoClient != null)
            mongoClient.close();
    }
    
}
