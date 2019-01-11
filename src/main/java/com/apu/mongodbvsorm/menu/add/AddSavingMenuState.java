/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm.menu.add;

import com.apu.mongodbvsorm.dao.NotebookEntityDAO;
import com.apu.mongodbvsorm.entities.NotebookEntity;
import com.apu.mongodbvsorm.menu.AddMenuState;
import com.apu.mongodbvsorm.menu.MainMenuState;
import com.apu.mongodbvsorm.menu.MenuState;

/**
 *
 * @author apu
 */
public class AddSavingMenuState extends MenuState {

    private static MenuState instance;

    private AddSavingMenuState() {
    }
    
    @Override
    public String handle() {
        NotebookEntity tempEntity = AddMenuState.getEntityToSave();
        tempEntity.setEntityId(2);
        NotebookEntityDAO.getInstance().save(tempEntity);
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
