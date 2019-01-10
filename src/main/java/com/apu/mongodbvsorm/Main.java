/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm;

import com.apu.mongodbvsorm.entities.Address;
import com.apu.mongodbvsorm.entities.Person;
import com.apu.mongodbvsorm.utils.Logger;
import com.mongodb.MongoClient;
import java.util.List;
import javax.management.Query;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author apu
 */
public class Main {
    
    public static void main(String[] args) {
        
        final Morphia morphia = new Morphia();
        final Datastore datastore = morphia.createDatastore(new MongoClient(), "morphia");
        morphia.mapPackage("com.apu.mongodbvsorm.entities");
//        morphia.map(Address.class);
        morphia.mapPackageFromClass(Address.class);
        Address addresss1 = new Address(1, "Popudrenko", "Chernigiv", "Ukraine");
        datastore.save(addresss1);
        
        List<Address> addresses = datastore.find(Address.class).asList();
        System.out.println("");
    }
    
}
