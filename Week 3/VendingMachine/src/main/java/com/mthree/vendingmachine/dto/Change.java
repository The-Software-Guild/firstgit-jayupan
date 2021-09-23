/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author Josef
 */
public enum Change {
    PENNY (new BigDecimal(0.01)),
    NICKEL (new BigDecimal(0.05)), 
    DIME (new BigDecimal(0.10)), 
    QUARTER (new BigDecimal(0.25));
    
    private BigDecimal val;
    
    Change(BigDecimal val)
    {
        this.val = val;
    }
    
    public BigDecimal getVal()
    {
        return val;
    }
}
