/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm;

import com.apu.mongodbvsorm.utils.storage.DatabaseStorage;
import com.apu.mongodbvsorm.menu.MainMenuState;
import com.apu.mongodbvsorm.menu.MenuState;
import com.apu.mongodbvsorm.utils.Logger;
import com.apu.mongodbvsorm.utils.Settings;
import org.apache.commons.lang.exception.ExceptionUtils;

/**
 *
 * @author apu
 */
public class Main {
    
    private static Logger LOGGER = Logger.getInstance();
    
    public static void main(String[] args) {
        DatabaseStorage db = new DatabaseStorage();
        try {
            String dbHost = Settings.loadSettingFromFile("database.host");
            String dbPortStr = Settings.loadSettingFromFile("database.port");        
            String dbName = Settings.loadSettingFromFile("database.name");
            int dbPort = Integer.parseInt(dbPortStr);
            
            db.connect(dbHost, dbPort, dbName);
            
            String ret = null;
            MenuState state = MainMenuState.getInstance();
            MenuState.setCurrentState(state);
            MenuState.setPreviousState(state);
            while(true) {
                ret = MenuState.getCurrentState().handle();
                if(ret != null) {
                    if(ret.equals(MenuState.RET_EXIT))
                        return;
                }
            }
        } catch (Exception ex) {
            LOGGER.error(Main.class, ExceptionUtils.getStackTrace(ex));
        } finally {
            try {
                db.disconnect();
            } catch(Exception ex) {
                LOGGER.error(Main.class, ExceptionUtils.getStackTrace(ex));
            }
        }     
    }
    
}
