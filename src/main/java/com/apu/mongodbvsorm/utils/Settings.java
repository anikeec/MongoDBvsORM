package com.apu.mongodbvsorm.utils;

import com.apu.mongodbvsorm.utils.storage.SettingsStorage;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    
    public static String loadSettingFromFile(String property) throws IOException {

        Properties props = SettingsStorage.loadPropertiesFromFile();
        String retValue = null;
        if(props.containsKey(property)) 
            retValue = props.getProperty(property);
        return retValue;
        
    }
    
}
