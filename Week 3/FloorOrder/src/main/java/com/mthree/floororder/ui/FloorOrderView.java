/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.floororder.ui;

import com.mthree.floororder.dto.Order;
import com.mthree.floororder.dto.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Josef
 */
public class FloorOrderView {
    private UserIO io;
    
    public FloorOrderView(UserIO io)
    {
        this.io = io;
    }
    
    public int printMenuAndGetSelection()
    {
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("   * ");
        io.print("   * <<Flooring Program>>");
        io.print("   * 1. Display Orders");
        io.print("   * 2. Add an Order");
        io.print("   * 3. Edit an Order");
        io.print("   * 4. Remove an Order");
        io.print("   * 5. Export All Data");
        io.print("   * 6. Quit");
        io.print("   * ");
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        return io.readInt("Enter choice.");
    }
    
    public void displayOrder(List<Order> orderList) //has to display multiple at once if # with same date > 1
    {
        for(Order order : orderList)
        {
            io.print("####################DISPLAYING ORDER####################"); //20 #'s
            io.print("   # ");
            io.print("   # Number: " + order.getOrderNum());
            io.print("   # Name: " + order.getName());
            io.print("   # State: " + order.getState());
            io.print("   # Product: " + order.getProductType());
            io.print("   # Total: $" + order.getTotal());
            io.print("   # ");
            io.print("########################################################");
        }
        displayGetOrderSuccess();
    }
    
    public LocalDate getDate()
    {
        String ld = io.readString("Please enter date in MMDDYYYY format.");
        return LocalDate.parse(ld,DateTimeFormatter.ofPattern("MMddyyyy"));
    }
    
    public Order getOrder(List<Product> products)
    {
        LocalDate date;
        String name, state, productType;
        BigDecimal area; 
        
        date = getDate();
        name = io.readString("Please enter a name for the order.");
        state = io.readString("Please enter state of order.");
        io.print("Available products:");
        products.forEach(product -> {
            io.print(product.getProductType());
        });
        productType = io.readString("Please enter the type of product.");
        area = new BigDecimal(io.readString("Please enter the desired area.")).setScale(2, RoundingMode.HALF_UP);
        
        return new Order(date, name, state, productType, area);
    }
    
    public void displayOrder(Order order)
    {
            io.print("####################DISPLAYING ORDER####################"); //20 #'s
            io.print("   # ");
            io.print("   # Number: " + order.getOrderNum());
            io.print("   # Name: " + order.getName());
            io.print("   # State: " + order.getState());
            io.print("   # Product: " + order.getProductType());
            io.print("   # Total: $" + order.getTotal());
            io.print("   # ");
            io.print("########################################################");
            displayGetOrderSuccess();

    }
    
    public boolean addOrder(Order order)
    {
        boolean addOrder = false;
        String input = io.readString("Would you like to add order? (Y/N)");
        switch(input)
        {
            case "Y":
                addOrder = true;
                break;
            case "N":
                addOrder = false;
                break;
            default: 
                displayUnknownCommandBanner();
                break;
        }
        return addOrder;
    }
    
    public int getOrderNum()
    {
        return io.readInt("Please enter order number.");
    }
    
    public Order editOrder(Order order)
    {
        order.setName(io.readStringEdit("Enter new customer name (" + order.getName() + "):", order.getName()));
        order.setState(io.readStringEdit("Enter new customer state (" + order.getState() +"):", order.getState()));
        order.setProductType(io.readStringEdit("Enter new customer product (" + order.getProductType() +"):", order.getProductType()));
        order.setArea(new BigDecimal(io.readStringEdit("Enter new customer area (" + order.getArea() +"):", order.getArea().toString())));
        return order;
    }
    
    public boolean removeOrder()
    {
        boolean removeOrder = false;
        String input = io.readString("Are you sure you would you like to remove order? (Y/N)");
        switch(input)
        {
            case "Y":
                removeOrder = true;
                break;
            case "N":
                removeOrder = false;
                break;
            default:
                displayUnknownCommandBanner();
                break;
        }
        return removeOrder;
    }
    
    public boolean displayExport()
    {
        boolean export = false;
        String input = io.readString("Would you like to export orders?\nCANNOT be undone. (Y/N)");
        switch(input)
        {
            case "Y":
                export = true;
                break;
            case "N":
                export = false;
                break;
            default:
                displayUnknownCommandBanner();
                break;
        }
        return export; 
    }
    
    public void displayGetOrderSuccess()
    {
        io.readString(
            "Order successfully displayed. Please hit enter to continue.");
    }
    
    public void displayAddOrderSuccess()
    {
        io.readString(
            "Order successfully added. Please hit enter to continue.");
    }
    
    public void displayAddOrderCancel()
    {
        io.readString(
            "Order not added. Please hit enter to continue.");
    }
    
     public void displayEditOrderSuccess()
    {
        io.readString(
            "Order successfully editted. Please hit enter to continue.");
    }
    
    public void displayEditOrderCancel()
    {
        io.readString(
            "Order not editted. Please hit enter to continue.");
    }
    
    public void displayRemoveOrderSuccess()
    {
        io.readString(
            "Order successfully removed. Please hit enter to continue.");
    }
    
    public void displayRemoveOrderCancel()
    {
        io.readString(
            "Order not removed. Please hit enter to continue.");
    }
    
    public void displayExportSuccess()
    {
        io.readString(
            "Export successful. Please hit enter to continue.");
    }
    
    public void displayExportCancel()
    {
        io.readString(
            "Export cancelled. Please hit enter to continue.");
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
    
    public void displayExitPrompt() {
        io.print("Before you leave...");
    }
    
}
