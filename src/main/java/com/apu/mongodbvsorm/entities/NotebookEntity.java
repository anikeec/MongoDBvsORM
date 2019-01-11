/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.mongodbvsorm.entities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 *
 * @author apu
 */
@Entity
public class NotebookEntity {
    
    @Id
    private ObjectId entityId;    
    private String message;    
    private String recipient;
    private Integer date;
    private Integer month;
    private Integer year;
    private Map<String, String> parameters = new HashMap<>();

    public NotebookEntity() {
    }

    public ObjectId getEntityId() {
        return entityId;
    }

    public void setEntityId(ObjectId entityId) {
        this.entityId = entityId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = 
                            parameters.entrySet().iterator();
        while(iterator.hasNext()) {
            Entry<String, String> entry = iterator.next();
            sb.append("{")
                    .append(entry.getKey())
                    .append(" : ")
                    .append(entry.getValue())
                    .append("} ");
        }
        return "NotebookEntity{" + "entityId=" + entityId + 
                ", message=" + message + 
                ", parameters : " + sb.toString() + "}";
    }

}
