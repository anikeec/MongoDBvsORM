/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm.menu;

import com.apu.mongodbvsorm.menu.delete.DeleteByParameterMenuState;

/**
 *
 * @author apu
 */
public class DeleteMenuState extends MenuState {
    
    private static MenuState instance;

    private DeleteMenuState() {
    }

    @Override
    public String handle() {
        int menuItem = 
                this.getChoosenValue();        
        String retValue = MenuState.RET_OK;
        switch(menuItem) {
            case 0:
                    MenuState.setCurrentState(MainMenuState.getInstance());
                    break;
            case 2:
                    MenuState.setPreviousState(this);
                    MenuState.setCurrentState(DeleteByParameterMenuState.getInstance());
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
        sb.append("Delete menu. Choose action:").append("\r\n");
        sb.append("0 - Return to main menu,").append("\r\n");
        sb.append("1 - Delete by id,").append("\r\n");
        sb.append("2 - Delete by parameter,").append("\r\n");
        sb.append("3 - Delete by recipient,");
        return sb.toString();
    }
    
    public static MenuState getInstance() {
        if(instance == null)
            instance = new DeleteMenuState();
        return instance;
    }
    
}
