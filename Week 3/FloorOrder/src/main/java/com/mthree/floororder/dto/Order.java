/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.floororder.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Josef
 */
public class Order {
    private String name, state, productType;
    private LocalDate date;
    private int orderNumber = 0; //default val
    private BigDecimal taxRate, area, costPerSqrFt, laborCostPerSqrFt, materialCost, laborCost, taxCost, total; 
    
    public Order()
    {
        
    }
    
    public Order(LocalDate date, String name, String state, String productType, BigDecimal area)
    {
        this.date = date;
        this.name = name;
        this.state = state;
        this.productType = productType;
        this.area = area;
    }
    
    //accessor methods
    public LocalDate getDate()
    {
        return date;
    }
    public String getName()
    {
        return name;
    }
    public String getState()
    {
        return state;
    }
    public String getProductType()
    {
        return productType;
    }
    public int getOrderNum()
    {
        return orderNumber;
    }
    public BigDecimal getTaxRate()
    {
        return taxRate;
    }
    public BigDecimal getArea()
    {
        return area;
    }
    public BigDecimal getCostPerSqrFt()
    {
        return costPerSqrFt;
    }
    public BigDecimal getLaborCostPerSqrFt()
    {
        return laborCostPerSqrFt;
    }
    public BigDecimal getMaterialCost()
    {
        return materialCost;
    }
    public BigDecimal getLaborCost()
    {
        return laborCost;
    }
    public BigDecimal getTaxCost()
    {
        return taxCost;
    }
    public BigDecimal getTotal()
    {
        return total;
    }
    
    
    
    
    //mutator methods
    public void setDate(LocalDate date)
    {
        this.date = date;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setState(String state)
    {
        this.state = state;
    }
    public void setProductType(String productType)
    {
        this.productType = productType;
    }
    public void setOrderNum(int orderNumber)
    {
        this.orderNumber = orderNumber;
    }
    public void setTaxRate(BigDecimal taxRate)
    {
        this.taxRate = taxRate;
    }
    public void setArea(BigDecimal area)
    {
        this.area = area;
    }
    public void setCostPerSqrFt(BigDecimal costPerSqrFt)
    {
        this.costPerSqrFt = costPerSqrFt;
    }
    public void setLaborCostPerSqrFt(BigDecimal laborCostPerSqrFt)
    {
        this.laborCostPerSqrFt = laborCostPerSqrFt;
    }
    public void setMaterialCost(BigDecimal materialCost)
    {
        this.materialCost = materialCost;
    }
    public void setLaborCost(BigDecimal laborCost)
    {
        this.laborCost = laborCost;
    }
    public void setTaxCost(BigDecimal taxCost)
    {
        this.taxCost = taxCost;
    }
    public void setTotal(BigDecimal total)
    {
        this.total = total;
    } 
}
