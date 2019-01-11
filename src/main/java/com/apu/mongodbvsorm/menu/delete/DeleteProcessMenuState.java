/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm.menu.delete;

import com.apu.mongodbvsorm.utils.storage.TemporaryStorage;
import com.apu.mongodbvsorm.dao.NotebookEntityDAO;
import com.apu.mongodbvsorm.entities.NotebookEntity;
import com.apu.mongodbvsorm.menu.MainMenuState;
import com.apu.mongodbvsorm.menu.MenuState;
import com.apu.mongodbvsorm.menu.add.AddSavingMenuState;
import com.apu.mongodbvsorm.utils.Logger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author apu
 */
public class DeleteProcessMenuState  extends MenuState {

    private static Logger LOGGER = Logger.getInstance();
    private static MenuState instance;

    private DeleteProcessMenuState() {
    }
    
    @Override
    public String handle() {
        NotebookEntity tempEntity = TemporaryStorage.getEntityToSave();
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
            LOGGER.info(DeleteProcessMenuState.class, "Find for delete:");
            System.out.println("Find for delete:");
            for(NotebookEntity entity:results) {
                dao.deleteById(entity.getEntityId());
                String result = entity.toString() + " - deleted.";
                LOGGER.info(DeleteProcessMenuState.class, result);
                System.out.println(result);
            }
        } catch (Exception ex) {
            LOGGER.error(AddSavingMenuState.class, ExceptionUtils.getStackTrace(ex));
        }
        TemporaryStorage.setTempParameter(null);
        TemporaryStorage.setEntityToSave(null);
        
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
            instance = new DeleteProcessMenuState();
        return instance;
    }
    
}
