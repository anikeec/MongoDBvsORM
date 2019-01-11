/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm.menu.add;

import com.apu.mongodbvsorm.TempRepository;
import com.apu.mongodbvsorm.dao.NotebookEntityDAO;
import com.apu.mongodbvsorm.entities.NotebookEntity;
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
        NotebookEntity tempEntity = TempRepository.getEntityToSave();
        if(tempEntity == null)
            throw new NullPointerException("tempEntity hasn't initialized yet.");
        try {
            NotebookEntityDAO.getInstance().save(tempEntity);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        TempRepository.setTempParameter(null);
        TempRepository.setEntityToSave(null);
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
