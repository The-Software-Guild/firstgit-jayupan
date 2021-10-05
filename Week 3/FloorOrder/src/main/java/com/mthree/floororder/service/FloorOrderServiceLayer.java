/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.floororder.service;

import com.mthree.floororder.dao.FloorOrderPersistenceException;
import com.mthree.floororder.dto.Order;
import com.mthree.floororder.dto.Product;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Josef
 */
public interface FloorOrderServiceLayer {
    public List<Order> getOrders();
    public List<Order> getOrdersByDate(LocalDate ld) throws InvalidDateException;
    public Order addOrder(Order order);
    public Order calculate(Order order)throws InvalidStateException, InvalidNameException, 
            NotFutureDateException, InvalidAreaException, InvalidNameException, InvalidProductException,
            NotAreaLimitException;
    public Order getOrder(LocalDate ld, int orderNum);
    public Order editOrder(Order order);
    public Order removeOrder(Order order);
    public void export() throws FloorOrderPersistenceException;
    public List<Product> getProducts();
}
