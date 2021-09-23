/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.dto;

/**
 *
 * @author Josef
 */
public class Item {
    private String id, name, cost;
    private int  num;
    
    public Item ()
    {
    }
    
    public String getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getCost()
    {
        return cost;
    }
    
    public int getNum()
    {
        return num;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setCost(String cost)
    {
        this.cost = cost;
    }
    
    public void setNum(int num)  
    {
        this.num = num;
    }
    
}
