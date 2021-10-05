/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 *
 * @author Josef
 */
public class Item {
    private String id, name, cost;
    private int num;
    
    public Item (Item item)
    {
        id = item.getId();
        name = item.getName();
        cost = item.getCost().toString();
        num = item.getNum();
    }
    
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
    
    public BigDecimal getCost()
    {
        BigDecimal newCost = new BigDecimal(cost).setScale(2, RoundingMode.HALF_UP);
        return newCost;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.cost);
        hash = 29 * hash + this.num;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.num != other.num) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.cost, other.cost)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString()
    {
        return "Item{" + "id=" + id + ", name=" + name + ", cost=" + cost.toString() + ", num=" + num + "}";
    }
    
}
