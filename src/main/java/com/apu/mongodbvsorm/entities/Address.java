package com.apu.mongodbvsorm.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * The Class Address.
 * annotations from 
 * http://morphiaorg.github.io/morphia/1.3/guides/annotations/
 */
@Entity
public class Address
{

    /** The address id. */
    @Id
    private Integer addressId;

    /** The street. */
    private String street;

    /** The city. */
    private String city;

    /** The country. */
    private String country;

    /**
     * Instantiates a new address.
     */
    public Address()
    {

    }
    
    

    /**
     * Instantiates a new address.
     * 
     * @param addressId
     *            the address id
     * @param street
     *            the street
     * @param city
     *            the city
     * @param country
     *            the country
     */
    public Address(Integer addressId, String street, String city, String country)
    {
        this.addressId = addressId;
        this.street = street;
        this.city = city;
        this.country = country;
    }

    /**
     * Gets the address id.
     * 
     * @return the address id
     */
    public Integer getAddressId()
    {
        return addressId;
    }

    /**
     * Sets the address id.
     * 
     * @param addressId
     *            the new address id
     */
    public void setAddressId(Integer addressId)
    {
        this.addressId = addressId;
    }

    /**
     * Gets the street.
     * 
     * @return the street
     */
    public String getStreet()
    {
        return street;
    }

    /**
     * Sets the street.
     * 
     * @param street
     *            the new street
     */
    public void setStreet(String street)
    {
        this.street = street;
    }

    /**
     * Gets the city.
     * 
     * @return the city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Sets the city.
     * 
     * @param city
     *            the new city
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * Gets the country.
     * 
     * @return the country
     */
    public String getCountry()
    {
        return country;
    }

    /**
     * Sets the country.
     * 
     * @param country
     *            the new country
     */
    public void setCountry(String country)
    {
        this.country = country;
    }

}
