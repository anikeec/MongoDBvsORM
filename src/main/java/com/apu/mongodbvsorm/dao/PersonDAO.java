/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm.dao;

import com.apu.mongodbvsorm.entities.Person;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

/**
 *
 * @author apu
 */
public class PersonDAO extends BasicDAO<Person, String> {
    
    public PersonDAO(Class<Person> entityClass, MongoClient mongoClient, Morphia morphia, String dbName) {
        super(entityClass, mongoClient, morphia, "AdressBookDB");
    }
    
}
