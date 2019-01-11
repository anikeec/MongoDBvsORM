/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm.menu.add;

import com.apu.mongodbvsorm.entities.Parameter;
import com.apu.mongodbvsorm.menu.AddMenuState;
import com.apu.mongodbvsorm.menu.MenuState;

/**
 *
 * @author apu
 */
public class AddParameterMenuState extends MenuState {
    
    private static MenuState instance;

    private AddParameterMenuState() {
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
        Parameter tempParameter = new Parameter();
        tempParameter.setName(inputText);
        AddMenuState.setTempParameter(tempParameter);
//        MenuState.setPreviousState(this);
        MenuState.setCurrentState(AddParameterValueMenuState.getInstance());
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
            instance = new AddParameterMenuState();
        return instance;
    }
    
}
