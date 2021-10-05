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
public class Product {
    //ProductType,CostPerSquareFoot,LaborCostPerSquareFoot
    private String productType;
    private BigDecimal costPerSqrFt, laborCostPerSqrFt;
    
    //getters
    public String getProductType()
    {
        return productType;
    }
    
    public BigDecimal getCost()
    {
        return costPerSqrFt;
    }
    
    public BigDecimal getLabor()
    {
        return laborCostPerSqrFt;
    }
  
    //setters
    public void setProductType(String productType)
    {
        this.productType = productType;
    }
    
    public void setCost(BigDecimal costPerSqrFt)
    {
        this.costPerSqrFt = costPerSqrFt;
    }
    
    public void setLabor(BigDecimal laborCostPerSqrFt)
    {
       this.laborCostPerSqrFt = laborCostPerSqrFt;
    }
    
}
