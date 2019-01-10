package com.apu.mongodbvsorm.utils.storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SettingsStorage {

    private static final String PROPERTIES_FILE_PATH = "application.properties";
    
    public static Properties loadPropertiesFromFile() 
                throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream(PROPERTIES_FILE_PATH)) {
            properties.load(in);               
        }
        return properties;
    }
    
    public static void savePropertiesToFile(Properties properties) throws IOException {
        try (FileOutputStream propFile = 
                new FileOutputStream(PROPERTIES_FILE_PATH)) {
            properties.store(propFile, null);
        }
    }
    
}
