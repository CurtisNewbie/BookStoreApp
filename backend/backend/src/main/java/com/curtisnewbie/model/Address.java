package com.curtisnewbie.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class Address {

    @NotNull
    private String firstLine;
    private String secondLine;

    @NotNull
    private String city;

    @NotNull
    private String county;

    @NotNull
    private String postCode;

    /**
     * @return first line address
     */
    public String getFirstLine() {
        return firstLine;
    }

    /**
     * @param firstLine the first line address to set
     */
    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
    }

    /**
     * @return the second line address
     */
    public String getSecondLine() {
        return secondLine;
    }

    /**
     * @param secondLine the second line address to set
     */
    public void setSecondLine(String secondLine) {
        this.secondLine = secondLine;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the county
     */
    public String getCounty() {
        return county;
    }

    /**
     * @param county the county to set
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     * @return the postCode
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * @param postCode the postCode to set
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}