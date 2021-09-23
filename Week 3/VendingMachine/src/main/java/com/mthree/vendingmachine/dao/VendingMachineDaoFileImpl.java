/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.dao;

import com.mthree.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Josef
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {
    private Map<String, Item> Items = new HashMap<>();
    public static final String ITEM_FILE = "item.txt";
    public static final String DELIMITER = "::";
    
    @Override
    public List<Item> getAllItems() throws VendingMachineDaoException
    {
         loadMachine();
         return new ArrayList<Item>(Items.values());
    }
    
    @Override
    public Item editItem(String itemId, Item item) throws VendingMachineDaoException
    {
        loadMachine();
        item.setNum(item.getNum()-1);
        Item editedItem = Items.replace(itemId, item);
        writeMachine();
        return editedItem;
    }
    
    private Item unmarshallItem(String itemAsText){
        // itemAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // 1::Coke::0.99::5
        
        String[] itemTokens = itemAsText.split(DELIMITER);
        
        Item itemFromFile = new Item();
        
        itemFromFile.setId(itemTokens[0]);

        itemFromFile.setName(itemTokens[1]);

        itemFromFile.setCost(itemTokens[2]);
        
        itemFromFile.setNum(Integer.parseInt(itemTokens[3]));
        
        return itemFromFile;
    }
    
    private void loadMachine() throws VendingMachineDaoException
    {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ITEM_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachineDaoException(
                    "-_- Could not load item data into memory.", e);
        }
        String currentLine;
        Item currentItem;
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);

            Items.put(currentItem.getId(), currentItem);
        }
        // close scanner
        scanner.close();
    }

    private String marshallItem(Item aItem)
    {
        String itemAsText = aItem.getId() + DELIMITER;
        
        itemAsText += aItem.getName() + DELIMITER;

        itemAsText += aItem.getCost() + DELIMITER;

        itemAsText += aItem.getNum();
                
        return itemAsText;
    }
    
    private void writeMachine() throws VendingMachineDaoException 
    {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ITEM_FILE));
        } catch (IOException e) {
            throw new VendingMachineDaoException(
                    "Could not save item data.", e);
        }

        String itemAsText;
        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {
            // turn a Student into a String
            itemAsText = marshallItem(currentItem);
            // write the Student object to the file
            out.println(itemAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}
