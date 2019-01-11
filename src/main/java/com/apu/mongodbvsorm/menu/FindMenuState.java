/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm.menu;

import com.apu.mongodbvsorm.TempRepository;
import com.apu.mongodbvsorm.entities.NotebookEntity;
import com.apu.mongodbvsorm.menu.find.FindByParameterMenuState;

/**
 *
 * @author apu
 */
public class FindMenuState extends MenuState {
    
    private static MenuState instance;

    private FindMenuState() {
    }

    @Override
    public String handle() {
        int menuItem = 
                this.getChoosenValue();        
        String retValue = MenuState.RET_OK;
//        if(MenuState.getPreviousState().getClass().equals(MainMenuState.class)) {
//            TempRepository.setEntityToSave(new NotebookEntity());
//        }
        switch(menuItem) {
            case 0:
                    MenuState.setCurrentState(MainMenuState.getInstance());
                    break;
            case 1:
                    MenuState.setPreviousState(this);
                    MenuState.setCurrentState(FindByParameterMenuState.getInstance());
                    break;
            default:
                    break;
        }        
        return retValue;
    }

    @Override
    public String getMenuHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("Find menu. Choose action:").append("\r\n");
        sb.append("0 - Return to main menu,").append("\r\n");
        sb.append("1 - Find by parameter,").append("\r\n");
        sb.append("2 - Find by recipient,").append("\r\n");
        sb.append("3 - Find by day,").append("\r\n");
        sb.append("4 - Find by month,").append("\r\n");
        sb.append("5 - Find by year");
        return sb.toString();
    }
    
    public static MenuState getInstance() {
        if(instance == null)
            instance = new FindMenuState();
        return instance;
    }
    
}
