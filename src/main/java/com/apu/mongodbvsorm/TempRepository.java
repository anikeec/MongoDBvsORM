/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm;

import com.apu.mongodbvsorm.entities.NotebookEntity;
import com.apu.mongodbvsorm.entities.Parameter;

/**
 *
 * @author apu
 */
public class TempRepository {
    
    private static Parameter tempParameter;
    private static NotebookEntity entityToSave;
    
    public static Parameter getTempParameter() {
        return tempParameter;
    }

    public static void setTempParameter(Parameter tempParameter) {
        TempRepository.tempParameter = tempParameter;
    }

    public static NotebookEntity getEntityToSave() {
        return entityToSave;
    }

    public static void setEntityToSave(NotebookEntity entityToSave) {
        TempRepository.entityToSave = entityToSave;
    }
    
}
