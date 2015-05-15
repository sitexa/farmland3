package com.sitexa.site.data;

/**
 * City entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class City implements java.io.Serializable {

    // Fields

    private String id;
    private String state;
    private String city;
    private String code;
    private String rome;
    private String code2;

    // Constructors

    /**
     * default constructor
     */
    public City() {
    }

    /**
     * minimal constructor
     */
    public City(String id, String state, String city, String code, String rome) {
        this.id = id;
        this.state = state;
        this.city = city;
        this.code = code;
        this.rome = rome;
    }

    /**
     * full constructor
     */
    public City(String id, String state, String city, String code, String rome,
                String code2) {
        this.id = id;
        this.state = state;
        this.city = city;
        this.code = code;
        this.rome = rome;
        this.code2 = code2;
    }

    // Property accessors

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRome() {
        return this.rome;
    }

    public void setRome(String rome) {
        this.rome = rome;
    }

    public String getCode2() {
        return this.code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
	}

}