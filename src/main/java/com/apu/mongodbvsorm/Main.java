/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm;

import com.apu.mongodbvsorm.dao.NotebookEntityDAO;
import com.apu.mongodbvsorm.entities.Address;
import com.apu.mongodbvsorm.entities.Person;
import com.apu.mongodbvsorm.menu.MainMenuState;
import com.apu.mongodbvsorm.menu.MenuState;
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
        
//        final Morphia morphia = new Morphia();
//        final Datastore datastore = morphia.createDatastore(new MongoClient(), "morphia");
//        morphia.mapPackage("com.apu.mongodbvsorm.entities");
////        morphia.map(Address.class);
//        morphia.mapPackageFromClass(Address.class);
//        Address addresss1 = new Address(1, "Popudrenko", "Chernigiv", "Ukraine");
//        datastore.save(addresss1);
//        
//        List<Address> addresses = datastore.find(Address.class).asList();
//        System.out.println("");
        
        Main.init();

        String ret = null;
        MenuState state = MainMenuState.getInstance();
        MenuState.setCurrentState(state);
        MenuState.setPreviousState(state);
        while(true) {
            ret = state.getCurrentState().handle();
            if(ret != null) {
                if(ret.equals(MenuState.RET_EXIT))
                    return;
            }
        }
        
    }
    
    private static void init() {
        final Morphia morphia = new Morphia();
        morphia.mapPackage("com.apu.mongodbvsorm.entities");
        final Datastore datastore = morphia.createDatastore(new MongoClient(), "notebookDB");        
        NotebookEntityDAO.init(datastore);
    }
    
}
