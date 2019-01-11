/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm.menu.add;

import com.apu.mongodbvsorm.utils.storage.TemporaryStorage;
import com.apu.mongodbvsorm.entities.Parameter;
import com.apu.mongodbvsorm.menu.AddMenuState;
import com.apu.mongodbvsorm.menu.MenuState;

/**
 *
 * @author apu
 */
public class AddParameterValueMenuState extends MenuState {
    
    private static MenuState instance;

    private AddParameterValueMenuState() {
    }
    
    @Override
    public String handle() {
        System.out.println(getMenuHeader());    //print menu text 
        String inputText;
        while(true) {                           //get input data
            inputText = this.getInputText();
            if(inputText.equals("")) {
                System.out.println("Please enter parameter value:");
                continue;
            } else {
                break;
            }
        }
        Parameter tempParameter = TemporaryStorage.getTempParameter();
        if(tempParameter == null)
            throw new NullPointerException("tempParameter hasn't initialized yet.");
        tempParameter.setValue(inputText);
        System.out.println("Created: " + tempParameter.toString());
        TemporaryStorage.getEntityToSave().getParameters()
                .put(tempParameter.getName(), tempParameter.getValue());
//        MenuState.setPreviousState(this);
        MenuState.setCurrentState(AddMenuState.getInstance());
        return MenuState.RET_OK;
    }

    @Override
    public String getMenuHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("Enter parameter value:");
        return sb.toString();
    }
    
    public static MenuState getInstance() {
        if(instance == null)
            instance = new AddParameterValueMenuState();
        return instance;
    }
    
}
