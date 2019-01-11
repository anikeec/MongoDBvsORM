/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm.menu;

/**
 *
 * @author apu
 */
public class MainMenuState extends MenuState {
    
    private static MenuState instance;

    private MainMenuState() {
    }
    
    @Override
    public String handle() {        
        int menuItem = 
                this.getChoosenValue();        
        String retValue = MenuState.RET_OK;
        switch(menuItem) {
            case 0:
                    retValue = MenuState.RET_EXIT;
            case 1:
                    MenuState.setPreviousState(this);
                    MenuState.setCurrentState(AddMenuState.getInstance());
                    break;
            case 3:
                    MenuState.setPreviousState(this);
                    MenuState.setCurrentState(FindMenuState.getInstance());
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
        sb.append("Main menu. Choose action:").append("\r\n");
        sb.append("0 - Exit,").append("\r\n");
        sb.append("1 - Add to database,").append("\r\n");
        sb.append("2 - Change into database,").append("\r\n");
        sb.append("3 - Find into database,").append("\r\n");
        sb.append("4 - Delete from database");
        return sb.toString();
    }
    
    public static MenuState getInstance() {
        if(instance == null)
            instance = new MainMenuState();
        return instance;
    }
    
}
