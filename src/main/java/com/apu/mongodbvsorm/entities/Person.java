package com.apu.mongodbvsorm.entities;

import java.util.List;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

/**
 * The Class Person.
 */
@Entity
public class Person
{

    /** The person id. */
    @Id
    private String personId;

    /** The person name. */
    private String personName;

    /** The age. */
    private int age;

    /** The addresses. */
    @Reference
    private List<Address> addresses;

    /**
     * Gets the addresses.
     * 
     * @return the addresses
     */
    public List<Address> getAddresses()
    {
        return addresses;
    }

    /**
     * Sets the addresses.
     * 
     * @param addresses
     *            the new addresses
     */
    public void setAddresses(List<Address> addresses)
    {
        this.addresses = addresses;
    }

    /**
     * Gets the person id.
     * 
     * @return the person id
     */
    public String getPersonId()
    {
        return personId;
    }

    /**
     * Sets the person id.
     * 
     * @param personId
     *            the new person id
     */
    public void setPersonId(String personId)
    {
        this.personId = personId;
    }

    /**
     * Gets the person name.
     * 
     * @return the person name
     */
    public String getPersonName()
    {
        return personName;
    }

    /**
     * Sets the person name.
     * 
     * @param personName
     *            the new person name
     */
    public void setPersonName(String personName)
    {
        this.personName = personName;
    }

    /**
     * Gets the age.
     * 
     * @return the age
     */
    public int getAge()
    {
        return age;
    }

    /**
     * Sets the age.
     * 
     * @param age
     *            the new age
     */
    public void setAge(int age)
    {
        this.age = age;
    }

}
