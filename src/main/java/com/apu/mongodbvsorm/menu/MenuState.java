/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author apu
 */
public abstract class MenuState {
    
    public static String RET_OK = "OK";
    public static String RET_EXIT = "EXIT";
    private static MenuState currentState;
    private static MenuState previousState;
    
    public abstract String handle();
    public abstract String getMenuHeader();

    public static MenuState getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(MenuState currentState) {
        MenuState.currentState = currentState;
    }

    public static MenuState getPreviousState() {
        return previousState;
    }

    public static void setPreviousState(MenuState previousState) {
        MenuState.previousState = previousState;
    }
    
    public String getInputText() {
        String retValue = null;
        BufferedReader breader = 
                new BufferedReader(new InputStreamReader(System.in));
        try {
            retValue = breader.readLine();
        } catch (IOException ex) {            
        }
        return retValue;
    }
    
    int getChoosenValue() {
        int retValue = 0;        
        System.out.println(getMenuHeader());    //print menu text        
        while(true) {                           //get input data
            String inputText = this.getInputText();
            if(inputText.equals("")) {
                System.out.println("Please choose menu item.");
                continue;
            }
            try {
                retValue = Integer.parseInt(inputText);
            } catch (NumberFormatException ex) {
                System.out.println("Error. Please choose menu item.");
                continue;
            }
            return retValue;
        }
    }
    
}
