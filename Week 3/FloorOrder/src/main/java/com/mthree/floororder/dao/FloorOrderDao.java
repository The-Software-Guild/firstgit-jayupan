/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.floororder.dao;

import com.mthree.floororder.dto.Order;
import com.mthree.floororder.dto.Product;
import com.mthree.floororder.dto.Tax;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Josef
 */
public interface FloorOrderDao {
    public List<Order> getOrders();
    public List<Tax> getTaxes();
    public List<Product> getProducts();
    public List<Order> getOrdersByDate(LocalDate ld);
    public Order addOrder(Order order);
    public Order calculate(Order order);
    public Order getOrder(LocalDate ld, int orderNum);
    public Order editOrder(Order order);
    public Order removeOrder(Order order);
    public void export() throws FloorOrderPersistenceException;
    
    
            
}
