/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm.menu;

import com.apu.mongodbvsorm.utils.storage.TemporaryStorage;
import com.apu.mongodbvsorm.entities.NotebookEntity;
import com.apu.mongodbvsorm.menu.add.AddParameterMenuState;
import com.apu.mongodbvsorm.menu.add.AddSavingMenuState;

/**
 *
 * @author apu
 */
public class AddMenuState extends MenuState {
    
    private static MenuState instance;

    private AddMenuState() {
    }

    @Override
    public String handle() {
        int menuItem = 
                this.getChoosenValue();        
        String retValue = MenuState.RET_OK;
        if(MenuState.getPreviousState().getClass().equals(MainMenuState.class)) {
            TemporaryStorage.setEntityToSave(new NotebookEntity());
        }
        switch(menuItem) {
            case 0:
                    MenuState.setCurrentState(MainMenuState.getInstance());
                    break;
            case 1:
                    MenuState.setPreviousState(this);
                    MenuState.setCurrentState(AddParameterMenuState.getInstance());
                    break;
            case 3:
                    MenuState.setPreviousState(this);
                    MenuState.setCurrentState(AddSavingMenuState.getInstance());
                    break;
            default:
                    System.out.println("Hasn't realized yet.");
                    break;
        }        
        return retValue;
    }

    @Override
    public String getMenuHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n");
        sb.append("Add menu. Choose action:").append("\r\n");
        sb.append("0 - Return to main menu,").append("\r\n");
        sb.append("1 - Add parameter,").append("\r\n");
        sb.append("2 - Add recipient,").append("\r\n");
        sb.append("3 - Save entity to notebook");
        return sb.toString();
    }
    
    public static MenuState getInstance() {
        if(instance == null)
            instance = new AddMenuState();
        return instance;
    }
    
}
