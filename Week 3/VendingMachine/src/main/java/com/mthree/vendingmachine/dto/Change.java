/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Josef
 */
public enum Change {
    PENNY (new BigDecimal(0.01).setScale(2, RoundingMode.HALF_UP)),
    NICKEL (new BigDecimal(0.05).setScale(2, RoundingMode.HALF_UP)), 
    DIME (new BigDecimal(0.10).setScale(2, RoundingMode.HALF_UP)), 
    QUARTER (new BigDecimal(0.25).setScale(2, RoundingMode.HALF_UP));
    
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
