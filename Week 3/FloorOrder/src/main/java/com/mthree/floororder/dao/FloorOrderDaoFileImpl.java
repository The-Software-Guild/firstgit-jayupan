/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.floororder.dao;

import com.mthree.floororder.dto.Order;
import com.mthree.floororder.dto.Product;
import com.mthree.floororder.dto.Tax;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Josef
 */
public class FloorOrderDaoFileImpl implements FloorOrderDao {
    private Map<Integer, Order> Orders = new HashMap<>();
    private List<Product> Products = new ArrayList<Product>();
    private List<Tax> Taxes = new ArrayList<Tax>();
    public static final String DELIMITER = ",";
    private final File ORDER_FOLDER;
    private final File DATA_FOLDER;
    private final File BACKUP_FOLDER;
    private int orderNum;
     
    public FloorOrderDaoFileImpl() throws FloorOrderPersistenceException
    {
        ORDER_FOLDER = new File("Orders");
        DATA_FOLDER = new File("Data");
        BACKUP_FOLDER = new File("Backup");
        loadMachine();
        
        //sorts the map so we can use the lastKey method to get the highest val for our orderNum
        TreeMap<Integer, Order> sorted = new TreeMap<>();
        sorted.putAll(Orders);
        orderNum = sorted.lastKey()+1;
    }
    
    @Override
    public List<Order> getOrders()
    {
        return new ArrayList<>(Orders.values());
    }
    
    @Override
    public List<Product> getProducts()
    {
        return Products;
    }
    
    @Override
    public List<Tax> getTaxes()
    {
        return Taxes;
    }
    @Override
    public List<Order> getOrdersByDate(LocalDate ld)
    {
        List<Order> orderList = this.getOrders();
        List<Order> returnList = new ArrayList<>();
        orderList.stream().filter(order -> (order.getDate().equals(ld))).forEachOrdered(order -> {
            returnList.add(order);
        });
        return returnList;
    }

    @Override
    public Order addOrder(Order order) {
        Order newOrder = Orders.put(order.getOrderNum(), order);
        orderNum++;
        return newOrder;
    }
    
    @Override
    public Order calculate(Order order){
        Products.stream().filter(product -> (order.getProductType().equals(product.getProductType()))).map(product -> {
            order.setCostPerSqrFt(product.getCost());
            return product;
        }).forEachOrdered(product -> {
            order.setLaborCostPerSqrFt(product.getLabor());
        });
        Taxes.stream().filter(tax -> (order.getState().equals(tax.getState()))).forEachOrdered(tax -> {
            order.setTaxRate(tax.getTaxRate());
        });
        order.setMaterialCost((order.getArea().multiply(order.getCostPerSqrFt())).setScale(2, RoundingMode.HALF_UP));
        order.setLaborCost((order.getArea().multiply(order.getLaborCostPerSqrFt())).setScale(2, RoundingMode.HALF_UP));
        BigDecimal materialAndLaborCost = order.getMaterialCost().add(order.getLaborCost()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal taxRateDec = order.getTaxRate().divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
        
        order.setTaxCost(materialAndLaborCost.multiply(taxRateDec).setScale(2, RoundingMode.HALF_UP));
        order.setTotal(materialAndLaborCost.add(order.getTaxCost()).setScale(2, RoundingMode.HALF_UP));
        
        if(order.getOrderNum() == 0) order.setOrderNum(orderNum); //sets the order to the incremented value WHEN adding
        return order;
    }
    
    @Override
    public Order getOrder(LocalDate ld, int orderNum) //probably not being used, delete later for cleaning
    {
        return Orders.get(orderNum);
    }

    @Override
    public Order editOrder(Order order)
    {
        return Orders.replace(order.getOrderNum(), order);
    }

    @Override
    public Order removeOrder(Order order) {
        return Orders.remove(order.getOrderNum());
    }
    
    @Override
    public void export() throws FloorOrderPersistenceException
    {
        writeMachine();
    }
  
    private Order unmarshallOrder(String orderAsText){
        // orderAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total
        
        String[] orderTokens = orderAsText.split(DELIMITER);
        
        Order orderFromFile = new Order();
        
        orderFromFile.setOrderNum(Integer.parseInt(orderTokens[0]));

        orderFromFile.setName(orderTokens[1]);

        orderFromFile.setState(orderTokens[2]);
        
        orderFromFile.setTaxRate(new BigDecimal(orderTokens[3]));
        
        orderFromFile.setProductType(orderTokens[4]);
        
        orderFromFile.setArea(new BigDecimal(orderTokens[5]));
        
        orderFromFile.setCostPerSqrFt(new BigDecimal(orderTokens[6]));
        
        orderFromFile.setLaborCostPerSqrFt(new BigDecimal(orderTokens[7]));
        
        orderFromFile.setMaterialCost(new BigDecimal(orderTokens[8]));
        
        orderFromFile.setLaborCost(new BigDecimal(orderTokens[9]));
        
        orderFromFile.setTaxRate(new BigDecimal(orderTokens[10]));
        
        orderFromFile.setTotal(new BigDecimal(orderTokens[11]));
                
        return orderFromFile;
    }
    
    private Product unmarshallProduct(String productAsText){
        // productAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // ProductType,CostPerSquareFoot,LaborCostPerSquareFoot
        
        String[] productTokens = productAsText.split(DELIMITER);
        
        Product productFromFile = new Product();
        
        productFromFile.setProductType(productTokens[0]);

        productFromFile.setCost(new BigDecimal(productTokens[1]));

        productFromFile.setLabor(new BigDecimal(productTokens[2]));
                
        return productFromFile;
    }
    
    private Tax unmarshallTax(String taxAsText){
        // taxAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // State,StateName,TaxRate
        
        String[] taxTokens = taxAsText.split(DELIMITER);
        
        Tax taxFromFile = new Tax();
        
        taxFromFile.setState(taxTokens[0]);

        taxFromFile.setStateName(taxTokens[1]);

        taxFromFile.setTaxRate(new BigDecimal(taxTokens[2]));
                
        return taxFromFile;
    }
    
    
    
    private void loadMachine() throws FloorOrderPersistenceException
    {
        Scanner scanner;
        for(final File fileEntry : ORDER_FOLDER.listFiles())
        {
            try {
                // Create Scanner for reading the file
                scanner = new Scanner(
                        new BufferedReader(
                                new FileReader(fileEntry)));
            } catch (FileNotFoundException e) {
                throw new FloorOrderPersistenceException(
                        "-_- Could not load order data into memory.", e);
            }
            String currentLine;
            Order currentOrder;
            scanner.nextLine(); //each text file has a sample text as its first line; thus this is needed for parsing correctly...
            while (scanner.hasNextLine()) {
                // get the next line in the file
                currentLine = scanner.nextLine();
                currentOrder = unmarshallOrder(currentLine);
                parseDate(currentOrder, fileEntry); //since the date is found "outside" the file, this is needed
                Orders.put(currentOrder.getOrderNum(),currentOrder);
            }
            // close scanner
            scanner.close();
        }
        
        //now reading from the data folder (products and taxes)
        for(final File fileEntry : DATA_FOLDER.listFiles())
        {
            try {
                // Create Scanner for reading the file
                scanner = new Scanner(
                        new BufferedReader(
                                new FileReader(fileEntry)));
            } catch (FileNotFoundException e) {
                throw new FloorOrderPersistenceException(
                        "-_- Could not load order data into memory.", e);
            }
            
            //let us read from the products file first
            if(fileEntry.getName().equals("Products.txt"))
            {
                String currentLine;
                Product currentProduct;
                scanner.nextLine(); //each text file has a sample text as its first line; thus this is needed for parsing correctly...
                while (scanner.hasNextLine()) {
                    // get the next line in the file
                    currentLine = scanner.nextLine();
                    currentProduct = unmarshallProduct(currentLine);
                    Products.add(currentProduct);
                }
                // close scanner
                scanner.close();
            }
            if(fileEntry.getName().equals("Taxes.txt"))
            {
                String currentLine;
                Tax currentTax;
                scanner.nextLine(); //each text file has a sample text as its first line; thus this is needed for parsing correctly...
                while (scanner.hasNextLine()) {
                    // get the next line in the file
                    currentLine = scanner.nextLine();
                    currentTax = unmarshallTax(currentLine);
                    Taxes.add(currentTax);
                }
                // close scanner
                scanner.close();
            }
        }
    }

    private String marshallOrder(Order aOrder)
    {
        // OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total
        String orderAsText = aOrder.getOrderNum() + DELIMITER;
        
        orderAsText += aOrder.getName() + DELIMITER;

        orderAsText += aOrder.getState() + DELIMITER;

        orderAsText += aOrder.getTaxRate()+ DELIMITER;
        
        orderAsText += aOrder.getProductType()+ DELIMITER;
        
        orderAsText += aOrder.getArea() + DELIMITER;
        
        orderAsText += aOrder.getCostPerSqrFt() + DELIMITER;
        
        orderAsText += aOrder.getLaborCostPerSqrFt() + DELIMITER;
        
        orderAsText += aOrder.getMaterialCost() + DELIMITER;
        
        orderAsText += aOrder.getLaborCost() + DELIMITER;
        
        orderAsText += aOrder.getTaxRate() + DELIMITER; 
        
        orderAsText += aOrder.getTotal();
       
        return orderAsText;
    }
    
    private void writeMachine() throws FloorOrderPersistenceException 
    {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        
        PrintWriter out;
        File file = new File(BACKUP_FOLDER, "DataExport.txt");
        try {
            out = new PrintWriter(new FileWriter(file));
        } catch (IOException e) {
            throw new FloorOrderPersistenceException(
                "Could not save order data.", e);
        }
        String orderAsText;
        out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total"); //top of file header
        List<Order> orderList = this.getOrders();
        for (Order currentOrder : orderList) {
            // turn a Order into a String
            orderAsText = marshallOrder(currentOrder);
            // write the Student object to the file
            out.println(orderAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        out.close();
    }
    
    private void parseDate(Order currentOrder, File fileEntry)
    {
        String date = fileEntry.getName().substring(fileEntry.getName().indexOf("_")+1, fileEntry.getName().indexOf("."));
        currentOrder.setDate(LocalDate.parse(date, DateTimeFormatter.ofPattern("MMddyyyy")));
    }
    
}
