/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.ui;

import com.mthree.vendingmachine.dto.Change;
import com.mthree.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
/**
 *
 * @author Josef
 */
public class VendingMachineView {
    private UserIO io;
    
    public VendingMachineView(UserIO io)
    {
        this.io = io;
    }
  
    
    public int printMenuAndGetSelection(List<Item> itemList, BigDecimal funds) {
        int count = 1;
        for (Item currentItem : itemList) {
            String itemInfo = String.format("%s. %s: $%s, %s left",
                  count,
                  currentItem.getName(),
                  currentItem.getCost(),
                  currentItem.getNum());
            io.print(itemInfo);
            count++;
        }
        
        io.print(count + ". Add funds. Current funds: $" + funds.toString());
        count++;
        io.print(count + ". Exit");
        return io.readInt("Please select from the above choices.", 1, count+1);
    }
    
    public void calculateChange(BigDecimal newFunds)
    {
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int pennies = 0; 
        newFunds = newFunds.setScale(2, RoundingMode.HALF_UP);
        while(newFunds.compareTo(BigDecimal.ZERO) > 0)
        {
            if(newFunds.compareTo(Change.QUARTER.getVal()) > 0 || newFunds.equals(Change.QUARTER.getVal()))
            {
                newFunds = newFunds.subtract(Change.QUARTER.getVal());
                quarters++;
            }
            else if(newFunds.compareTo(Change.DIME.getVal()) > 0 || newFunds.equals(Change.DIME.getVal()))
            {
                newFunds = newFunds.subtract(Change.DIME.getVal());
                dimes++;
            }
            else if(newFunds.compareTo(Change.NICKEL.getVal()) > 0 || newFunds.equals(Change.NICKEL.getVal()))
            {
                newFunds = newFunds.subtract(Change.NICKEL.getVal());
                nickels++;
            }
            else //pennies
            {
                newFunds = newFunds.subtract(Change.PENNY.getVal());
                pennies++;
            }
            newFunds = newFunds.setScale(2, RoundingMode.HALF_UP );
        }
        io.print("Quarters: " + quarters);
        io.print("Dimes: " + dimes);
        io.print("Nickels: " + nickels);
        io.print("Pennies: " + pennies);
    }
    
    public BigDecimal promptFunds()
    {
        return new BigDecimal(io.readDouble("Add funds amount.")).setScale(2, RoundingMode.HALF_UP);
    }
    
    public void displayBuyItemBanner (String itemName)
    {
        io.print("=== Buying " + itemName + " ===");
    }
    
    public void displayAddFundsBanner()
    {
        io.print("=== Adding Funds ===");
    }
    
    public void displayAddFundsSuccessBanner()
    {
        io.readString(
            "Funds successfully added.  Please hit enter to continue");
    }
    
    public void displayExitBanner() 
    {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    
}
