package com.apu.mongodbvsorm.utils.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileStorage<T> {
    
    private String storageFileName = "storage.txt";
    
    public void setFileName(String fileName) {
        this.storageFileName = fileName;
    }

    public void add(T data) throws IOException {
        if(!(data instanceof String)) return;
        
        try (FileWriter writer = new FileWriter(storageFileName, true); 
                BufferedWriter bufferWriter = new BufferedWriter(writer)) {
            bufferWriter.write((String)data + "\r\n");
        }             
    }

    public boolean find(T data) throws IOException {
        if(!(data instanceof String)) return false;
        try (BufferedReader reader = 
                new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(
                                        new File(storageFileName))))) {
            String str;
            while((str = reader.readLine()) != null) {
                if(str.equals((String)data))
                        return true;
            }
        }
        return false;
    }

}
