/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm.menu.find;

import com.apu.mongodbvsorm.TempRepository;
import com.apu.mongodbvsorm.dao.NotebookEntityDAO;
import com.apu.mongodbvsorm.entities.NotebookEntity;
import com.apu.mongodbvsorm.menu.MainMenuState;
import com.apu.mongodbvsorm.menu.MenuState;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author apu
 */
public class FindProcessMenuState  extends MenuState {

    private static MenuState instance;

    private FindProcessMenuState() {
    }
    
    @Override
    public String handle() {
        NotebookEntity tempEntity = TempRepository.getEntityToSave();
        if(tempEntity == null)
            throw new NullPointerException("tempEntity hasn't initialized yet.");
        
        BasicDAO dao = NotebookEntityDAO.getInstance();
        
        Query<NotebookEntity> query = dao.createQuery();
        Map<String, String> parameters = tempEntity.getParameters();
        if(!parameters.isEmpty()) {
            Iterator<Map.Entry<String, String>> iterator = 
                            parameters.entrySet().iterator();
            while(iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String key = entry.getKey();
                String value = entry.getValue();
                if((key != null)&&(value != null))
                    query = query.field(key).equal(value);
                else if((key != null)&&(value == null))          
                    query = query.field("parameters." + key).exists();
            }
        }
        List<NotebookEntity> results;
        try {
            results = dao.find(query).asList();
            System.out.println("Search results:");
            for(NotebookEntity entity:results) {
                System.out.println(entity.toString());
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        TempRepository.setTempParameter(null);
        TempRepository.setEntityToSave(null);
        
        System.out.println();
        MenuState.setCurrentState(MainMenuState.getInstance());
        return MenuState.RET_OK;
    }

    @Override
    public String getMenuHeader() {
        return "";
    }
    
    public static MenuState getInstance() {
        if(instance == null)
            instance = new FindProcessMenuState();
        return instance;
    }
    
}
