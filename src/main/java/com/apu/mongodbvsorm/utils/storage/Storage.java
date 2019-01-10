package com.apu.mongodbvsorm.utils.storage;

import java.io.IOException;

public interface Storage<T> {

    public void setFileName(String fileName);
    void add(T data) throws IOException;
    boolean find(T data) throws IOException;
    
}
