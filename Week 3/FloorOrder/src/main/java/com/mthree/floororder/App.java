/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.floororder;

import com.mthree.floororder.controller.FloorOrderController;
import com.mthree.floororder.dao.FloorOrderAuditDao;
import com.mthree.floororder.dao.FloorOrderAuditDaoImpl;
import com.mthree.floororder.dao.FloorOrderDao;
import com.mthree.floororder.dao.FloorOrderDaoFileImpl;
import com.mthree.floororder.dao.FloorOrderPersistenceException;
import com.mthree.floororder.service.FloorOrderServiceLayer;
import com.mthree.floororder.service.FloorOrderServiceLayerImpl;
import com.mthree.floororder.ui.FloorOrderView;
import com.mthree.floororder.ui.UserIO;
import com.mthree.floororder.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Josef
 */
public class App {
    public static void main(String[] args)
    {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        FloorOrderController controller = appContext.getBean("controller", FloorOrderController.class);
        controller.run();
    }
}
