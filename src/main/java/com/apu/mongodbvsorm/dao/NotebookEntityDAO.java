/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm.dao;

import com.apu.mongodbvsorm.entities.NotebookEntity;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

/**
 *
 * @author apu
 */
public class NotebookEntityDAO extends BasicDAO<NotebookEntity, String> {
    
    private static BasicDAO instance;
    
    public static void init(Datastore ds) {
        instance = new NotebookEntityDAO(ds);
    }
    
    public static BasicDAO getInstance() {
        if(instance == null)
            throw new IllegalStateException("Instance has't not initialized yet.");
        return instance;
    }

    public NotebookEntityDAO(Datastore ds) {
        super(ds);
    }
    
}
