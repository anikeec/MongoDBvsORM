/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm.menu.add;

import com.apu.mongodbvsorm.utils.storage.TemporaryStorage;
import com.apu.mongodbvsorm.dao.NotebookEntityDAO;
import com.apu.mongodbvsorm.entities.NotebookEntity;
import com.apu.mongodbvsorm.menu.MainMenuState;
import com.apu.mongodbvsorm.menu.MenuState;
import com.apu.mongodbvsorm.utils.Logger;
import org.apache.commons.lang.exception.ExceptionUtils;

/**
 *
 * @author apu
 */
public class AddSavingMenuState extends MenuState {

    private static Logger LOGGER = Logger.getInstance();
    private static MenuState instance;

    private AddSavingMenuState() {
    }
    
    @Override
    public String handle() {
        NotebookEntity tempEntity = TemporaryStorage.getEntityToSave();
        if(tempEntity == null)
            throw new NullPointerException("tempEntity hasn't initialized yet.");
        try {
            NotebookEntityDAO.getInstance().save(tempEntity);
        } catch (Exception ex) {
            LOGGER.error(AddSavingMenuState.class, ExceptionUtils.getStackTrace(ex));
        }
        TemporaryStorage.setTempParameter(null);
        TemporaryStorage.setEntityToSave(null);
        System.out.println("Entity has saved:");
        System.out.println(tempEntity.toString());
        System.out.println("");
        MenuState.setCurrentState(MainMenuState.getInstance());
        return MenuState.RET_OK;
    }

    @Override
    public String getMenuHeader() {
        return "";
    }
    
    public static MenuState getInstance() {
        if(instance == null)
            instance = new AddSavingMenuState();
        return instance;
    }
    
}
