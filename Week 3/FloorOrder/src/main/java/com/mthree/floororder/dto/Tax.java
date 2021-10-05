/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.floororder.dto;

import java.math.BigDecimal;

/**
 *
 * @author Josef
 */
public class Tax {
    //State,StateName,TaxRate
    private String state, stateName;
    BigDecimal taxRate;
    
    //setters
    public void setState(String state)
    {
        this.state = state;
    }
    
    public void setStateName(String stateName)
    {
        this.stateName = stateName;
    }
    
    public void setTaxRate(BigDecimal taxRate)
    {
        this.taxRate = taxRate;
    }
    
    //getters
    public String getState()
    {
        return state;
    }
    
    public String getStateName()
    {
        return stateName;
    }
    
    public BigDecimal getTaxRate()
    {
        return taxRate;
    }
}
