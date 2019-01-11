/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm.menu.delete;

import com.apu.mongodbvsorm.menu.find.*;
import com.apu.mongodbvsorm.utils.storage.TemporaryStorage;
import com.apu.mongodbvsorm.entities.NotebookEntity;
import com.apu.mongodbvsorm.menu.MenuState;

/**
 *
 * @author apu
 */
public class DeleteByParameterMenuState extends MenuState {
    
    private static MenuState instance;

    private DeleteByParameterMenuState() {
    }
    
    @Override
    public String handle() {
        System.out.println(getMenuHeader());    //print menu text 
        String inputText;
        while(true) {                           //get input data
            inputText = this.getInputText();
            if(inputText.equals("")) {
                System.out.println("Please enter parameter name:");
                continue;
            } else {
                break;
            }
        }        
        NotebookEntity tempEntity = new NotebookEntity();
        tempEntity.getParameters().put(inputText, null);
        TemporaryStorage.setEntityToSave(tempEntity);
        MenuState.setCurrentState(DeleteProcessMenuState.getInstance());
        return MenuState.RET_OK;
    }

    @Override
    public String getMenuHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("Enter parameter name:");
        return sb.toString();
    }
    
    public static MenuState getInstance() {
        if(instance == null)
            instance = new DeleteByParameterMenuState();
        return instance;
    }
    
}
